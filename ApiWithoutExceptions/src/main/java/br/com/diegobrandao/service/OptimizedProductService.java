package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.InventoryStatus;
import br.com.diegobrandao.domain.PriceInfo;
import br.com.diegobrandao.domain.Product;
import br.com.diegobrandao.domain.Result;
import br.com.diegobrandao.domain.dto.ProductDTO;
import br.com.diegobrandao.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptimizedProductService {
    private static final Logger log = LoggerFactory.getLogger(OptimizedProductService.class);

    private final ProductRepository repository;
    private final PriceService priceService;
    private final InventoryService inventoryService;

    @Autowired
    public OptimizedProductService(
            ProductRepository repository,
            PriceService priceService,
            InventoryService inventoryService) {
        this.repository = repository;
        this.priceService = priceService;
        this.inventoryService = inventoryService;
    }

    public Result<ProductDTO> onlyProduct(String id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .map(Result::success)
                .orElse(Result.error("Produto não encontrado"));
    }

    public Result<ProductDTO> productWithPrice(String id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            return Result.error("Produto não encontrado");
        }

        Product product = productOpt.get();

        // Obter preço de forma segura
        Optional<PriceInfo> priceOpt = safeGetPrice(id);
        if (priceOpt.isPresent()) {
            product.setPrice(priceOpt.get());
        }

        return Result.success(convertToDTO(product));
    }

    public Result<ProductDTO> productWithInventory(String id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            return Result.error("Produto não encontrado");
        }

        Product product = productOpt.get();

        Result<InventoryStatus> invResult = inventoryService.findInventoryStatus(id);
        if (invResult.isSuccess() && invResult.getValue() != null) {
            product.setInventoryStatus(invResult.getValue());
        }

        return Result.success(convertToDTO(product));
    }

    public Result<ProductDTO> fullProduct(String id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isEmpty()) {
            return Result.error("Produto não encontrado");
        }

        Product product = productOpt.get();

        Optional<PriceInfo> priceOpt = safeGetPrice(id);
        if (priceOpt.isPresent()) {
            product.setPrice(priceOpt.get());
        }

        Result<InventoryStatus> invResult = inventoryService.findInventoryStatus(id);
        if (invResult.isSuccess() && invResult.getValue() != null) {
            product.setInventoryStatus(invResult.getValue());
        }

        return Result.success(convertToDTO(product));
    }

    private Optional<PriceInfo> safeGetPrice(String id) {
        try {
            return priceService.findPriceInfo(id);
        } catch (Exception e) {
            log.error("Erro ao buscar preço para produto {}", id, e);
            return Optional.empty();
        }
    }

    private ProductDTO convertToDTO(Product product) {
        double price = Optional.ofNullable(product.getPrice())
                .map(PriceInfo::getFinalPrice)
                .orElse(0.0);

        int quantity = Optional.ofNullable(product.getInventoryStatus())
                .map(InventoryStatus::getQuantity)
                .orElse(0);

        boolean inStock = Optional.ofNullable(product.getInventoryStatus())
                .map(InventoryStatus::isInStock)
                .orElse(false);

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                price,
                quantity,
                inStock
        );
    }
}
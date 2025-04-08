package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.InventoryStatus;
import br.com.diegobrandao.domain.PriceInfo;
import br.com.diegobrandao.domain.Product;
import br.com.diegobrandao.domain.dto.ProductDTO;
import br.com.diegobrandao.exceptions.InventoryServiceException;
import br.com.diegobrandao.exceptions.PriceServiceException;
import br.com.diegobrandao.exceptions.ProductNotFoundException;
import br.com.diegobrandao.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceWithException{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceWithException.class);

    private final ProductRepository repository;
    private final PriceService priceService;
    private final InventoryService inventoryService;

    @Autowired
    public ProductServiceWithException(
            ProductRepository repository,
            PriceService priceService,
            InventoryService inventoryService) {
        this.repository = repository;
        this.priceService = priceService;
        this.inventoryService = inventoryService;
    }

    public ProductDTO findProduct(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo ou vazio");
        }

        Product product = getProduct(productId);

        try {
            PriceInfo priceInfo = priceService.getPriceInfo(productId);
            product.setPrice(priceInfo);
        } catch (PriceServiceException e) {
            log.warn("Preço não disponível para produto {}: {}", productId, e.getMessage());
        }

        try {
            InventoryStatus inventoryStatus = inventoryService.getInventoryStatus(productId);
            product.setInventoryStatus(inventoryStatus);
        } catch (InventoryServiceException e) {
            log.warn("Inventário não disponível para produto {}: {}", productId, e.getMessage());
        }

        return convertToDTO(product);
    }

    public ProductDTO onlyProduct(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo ou vazio");
        }

        Product product = getProduct(productId);
        return convertToDTO(product);
    }

    public ProductDTO productWithPrice(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo ou vazio");
        }

        Product product = getProduct(productId);

        try {
            PriceInfo priceInfo = priceService.getPriceInfo(productId);
            product.setPrice(priceInfo);
        } catch (PriceServiceException e) {
            log.warn("Preço não disponível para produto {}: {}", productId, e.getMessage());
        }

        return convertToDTO(product);
    }

    public ProductDTO productWithInventory(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo ou vazio");
        }

        Product product = getProduct(productId);

        try {
            InventoryStatus inventoryStatus = inventoryService.getInventoryStatus(productId);
            product.setInventoryStatus(inventoryStatus);
        } catch (InventoryServiceException e) {
            log.warn("Inventário não disponível para produto {}: {}", productId, e.getMessage());
        }

        return convertToDTO(product);
    }

    private Product getProduct(String productId) {
        Product product = repository.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("Produto não encontrado: " + productId);
        }
        return product;
    }

    private ProductDTO convertToDTO(Product product) {
        double price = 0.0;
        if (product.getPrice() != null) {
            price = product.getPrice().getFinalPrice();
        }

        int quantity = 0;
        boolean inStock = false;
        if (product.getInventoryStatus() != null) {
            quantity = product.getInventoryStatus().getQuantity();
            inStock = product.getInventoryStatus().isInStock();
        }

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
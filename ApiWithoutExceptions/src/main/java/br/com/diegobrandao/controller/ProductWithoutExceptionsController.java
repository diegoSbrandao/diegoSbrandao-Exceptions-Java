package br.com.diegobrandao.controller;

import br.com.diegobrandao.domain.Result;
import br.com.diegobrandao.domain.dto.ProductDTO;
import br.com.diegobrandao.service.OptimizedProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products-no-exceptions")
public class ProductWithoutExceptionsController {

    private final OptimizedProductService service;

    public ProductWithoutExceptionsController(OptimizedProductService service) {
        this.service = service;
    }

    @GetMapping("/basic/{id}")
    public ResponseEntity<ProductDTO> onlyProduct(@PathVariable String id) {
        Result<ProductDTO> result = service.onlyProduct(id);
        return toResponse(result);
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<ProductDTO> productWithPrice(@PathVariable String id) {
        Result<ProductDTO> result = service.productWithPrice(id);
        return toResponse(result);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<ProductDTO> productWithInventory(@PathVariable String id) {
        Result<ProductDTO> result = service.productWithInventory(id);
        return toResponse(result);
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<ProductDTO> fullProduct(@PathVariable String id) {
        Result<ProductDTO> result = service.fullProduct(id);
        return toResponse(result);
    }

    private ResponseEntity<ProductDTO> toResponse(Result<ProductDTO> result) {
        if (result == null || !result.isSuccess()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result.getValue());
    }
}
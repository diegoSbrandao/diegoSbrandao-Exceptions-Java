package br.com.diegobrandao.controller;

import br.com.diegobrandao.domain.dto.ProductDTO;
import br.com.diegobrandao.exceptions.ProductNotFoundException;
import br.com.diegobrandao.exceptions.ResourceNotFoundException;
import br.com.diegobrandao.exceptions.ServiceException;
import br.com.diegobrandao.service.ProductServiceWithException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products-with-exceptions")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductServiceWithException service;

    @Autowired
    public ProductController(ProductServiceWithException service) {
        this.service = service;
    }

    @GetMapping("/with-exceptions/{id}")
    public ResponseEntity<?> getWithExceptions(@PathVariable String id) {
        try {
            ProductDTO dto = service.findProduct(id);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no serviço de produto");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado");
        }
    }

    @GetMapping("/basic/{id}")
    public ResponseEntity<?> onlyProduct(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.onlyProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produto");
        }
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<?> productWithPrice(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.productWithPrice(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produto com preço");
        }
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> productWithInventory(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.productWithInventory(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produto com inventário");
        }
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<?> fullProduct(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.findProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produto completo");
        }
    }
}
package br.com.diegobrandao.repository;

import br.com.diegobrandao.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(String id);
}


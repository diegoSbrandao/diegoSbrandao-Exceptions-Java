package br.com.diegobrandao.repository;

import br.com.diegobrandao.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    public InMemoryProductRepository() {
        // Inicializar com alguns produtos de exemplo
        for (int i = 1; i <= 100; i++) {
            String id = String.valueOf(i);
            products.put(id, new Product(id, "Produto " + i, "Descrição do produto " + i));
        }
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product save(Product product) {
        return Optional.ofNullable(product)
                .filter(p -> p.getId() != null && !p.getId().isEmpty())
                .map(p -> {
                    products.put(p.getId(), p);
                    return p;
                })
                .orElse(null);
    }

    @Override
    public void deleteById(String id) {
        Optional.ofNullable(id)
                .filter(products::containsKey)
                .ifPresent(products::remove);
    }
}


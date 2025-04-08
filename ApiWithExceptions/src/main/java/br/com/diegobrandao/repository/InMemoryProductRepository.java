package br.com.diegobrandao.repository;

import br.com.diegobrandao.domain.Product;
import br.com.diegobrandao.exceptions.ProductNotFoundException;
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
    public Product findById(String id) {
        try {
            return products.get(id);
        } catch (RuntimeException e) {
            throw new ProductNotFoundException("Erro ao buscar produto", e);
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return new ArrayList<>(products.values());
        } catch (RuntimeException e) {
            throw new ProductNotFoundException("Erro ao buscar produtos", e);
        }
    }

    @Override
    public Product save(Product product) {
        try {
            if (product.getId() == null || product.getId().isEmpty()) {
                throw new IllegalArgumentException("ID do produto não pode ser nulo ou vazio");
            }
            products.put(product.getId(), product);
            return product;
        } catch (IllegalArgumentException e) {
            throw new ProductNotFoundException("Erro ao salvar produto", e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            if (!products.containsKey(id)) {
                throw new ProductNotFoundException("Produto não encontrado: " + id);
            }
            products.remove(id);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Erro ao deletar produto", e);
        }
    }
}


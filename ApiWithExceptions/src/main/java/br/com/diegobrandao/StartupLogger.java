package br.com.diegobrandao;

import br.com.diegobrandao.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger implements CommandLineRunner {

    private final ProductRepository repository;

    public StartupLogger(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Quantidade de produtos carregados em memória: " + repository.findAll().size());
    }
}

package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.InventoryStatus;
import br.com.diegobrandao.exceptions.InventoryServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InventoryServiceImpl implements InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);
    private final Random random = new Random();

    @Override
    public InventoryStatus getInventoryStatus(String productId) {
        try {

            // Simular erro ocasional (1% de chance)
            if (random.nextDouble() < 0.01) {
                throw new RuntimeException("Erro simulado no serviço de inventário");
            }

            int quantity = random.nextInt(100);
            String warehouseId = "WH-" + (random.nextInt(5) + 1);
            boolean backorderAvailable = random.nextBoolean();

            return new InventoryStatus(quantity, warehouseId, backorderAvailable);

        } catch (RuntimeException e) {
            throw new InventoryServiceException("Erro ao buscar status de inventário", e);
        }
    }

}




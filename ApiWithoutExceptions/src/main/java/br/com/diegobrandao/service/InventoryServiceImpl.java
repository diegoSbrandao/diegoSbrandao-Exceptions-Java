package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.InventoryStatus;
import br.com.diegobrandao.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);
    private final Random random = new Random();

    @Override
    public Result<InventoryStatus> findInventoryStatus(String productId) {

            // Simular erro ocasional (1% de chance)
            if (random.nextDouble() < 0.01) {
                return Result.error("Erro ao acessar serviço de inventário para produto: " + productId);
            }

            int quantity = random.nextInt(100);
            String warehouseId = "WH-" + (random.nextInt(5) + 1);
            boolean backorderAvailable = random.nextBoolean();

            return Result.success(new InventoryStatus(quantity, warehouseId, backorderAvailable));
        }
    }


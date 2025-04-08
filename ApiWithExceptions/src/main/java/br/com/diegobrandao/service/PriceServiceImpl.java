package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.PriceInfo;
import br.com.diegobrandao.exceptions.PriceServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class PriceServiceImpl implements PriceService {
    private static final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);
    private final Random random = new Random();

    @Override
    public PriceInfo getPriceInfo(String productId) {
        try {
            // Simular erro ocasional (10% de chance)
            if (random.nextDouble() < 0.10) {
                throw new RuntimeException("Erro simulado no serviço de preços");
            }

            // Simular produto não encontrado (5% de chance)
            if (random.nextDouble() < 0.05) {
                throw new PriceServiceException("Preço não encontrado para: " + productId);
            }

            double basePrice = 100 + (random.nextDouble() * 900);
            double discount = random.nextDouble() * 30;

            return new PriceInfo(basePrice, discount, "BRL");
        } catch (PriceServiceException e) {
            log.error("Erro ao buscar preço para o produto {}: {}", productId, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao processar preço para o produto {}: {}", productId, e.getMessage());
            throw new PriceServiceException("Falha ao processar informações de preço", e);
        }
    }
}
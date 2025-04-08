package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.PriceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
public class PriceServiceImpl implements PriceService {
    private static final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);
    private final Random random = new Random();


    @Override
    public Optional<PriceInfo> findPriceInfo(String productId) {

            // Simular erro ocasional (10% de chance)
            if (random.nextDouble() < 0.10) {
                log.error("Erro ao acessar serviço de preços para produto: {}", productId);
                return Optional.empty();
            }

            // Simular produto não encontrado (5% de chance)
            if (random.nextDouble() < 0.05) {
                log.debug("Preço não encontrado para: {}", productId);
                return Optional.empty();
            }

            double basePrice = 100 + (random.nextDouble() * 900);
            double discount = random.nextDouble() * 30;
            return Optional.of(new PriceInfo(basePrice, discount, "BRL"));

        }
    }

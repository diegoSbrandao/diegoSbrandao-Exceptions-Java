package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.PriceInfo;
import br.com.diegobrandao.exceptions.PriceServiceException;

import java.util.Optional;

public interface PriceService {

    PriceInfo getPriceInfo(String productId) throws PriceServiceException;

}

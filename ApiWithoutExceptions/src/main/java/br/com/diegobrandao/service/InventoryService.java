package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.InventoryStatus;
import br.com.diegobrandao.domain.Result;

public interface InventoryService {

    Result<InventoryStatus> findInventoryStatus(String productId);
}

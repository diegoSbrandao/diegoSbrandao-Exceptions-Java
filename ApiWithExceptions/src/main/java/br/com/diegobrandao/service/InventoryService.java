package br.com.diegobrandao.service;

import br.com.diegobrandao.domain.InventoryStatus;

public interface InventoryService {

    InventoryStatus getInventoryStatus(String productId);

}

package br.com.diegobrandao.domain;

public class InventoryStatus {
    private int quantity;
    private String warehouseId;
    private boolean backorderAvailable;

    public InventoryStatus(int quantity, String warehouseId, boolean backorderAvailable) {
        this.quantity = quantity;
        this.warehouseId = warehouseId;
        this.backorderAvailable = backorderAvailable;
    }

    public boolean isInStock() {
        return quantity > 0;
    }

    public int getQuantity() { return quantity; }
    public String getWarehouseId() { return warehouseId; }
    public boolean isBackorderAvailable() { return backorderAvailable; }
}

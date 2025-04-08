package br.com.diegobrandao.domain;

public class Product {
    private String id;
    private String name;
    private String description;
    private PriceInfo price;
    private InventoryStatus inventoryStatus;

    public Product(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public PriceInfo getPrice() { return price; }
    public void setPrice(PriceInfo price) { this.price = price; }

    public InventoryStatus getInventoryStatus() { return inventoryStatus; }
    public void setInventoryStatus(InventoryStatus inventoryStatus) { this.inventoryStatus = inventoryStatus; }
}


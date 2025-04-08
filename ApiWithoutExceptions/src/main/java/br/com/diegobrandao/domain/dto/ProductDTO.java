package br.com.diegobrandao.domain.dto;

public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private int availableQuantity;
    private boolean inStock;

    public ProductDTO(String id, String name, String description,
                      double price, int availableQuantity, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.inStock = inStock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getAvailableQuantity() { return availableQuantity; }
    public boolean isInStock() { return inStock; }
}

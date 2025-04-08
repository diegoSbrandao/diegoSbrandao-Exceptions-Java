package br.com.diegobrandao.domain;

public class PriceInfo {
    private double basePrice;
    private double discount;
    private String currencyCode;

    public PriceInfo(double basePrice, double discount, String currencyCode) {
        this.basePrice = basePrice;
        this.discount = discount;
        this.currencyCode = currencyCode;
    }

    public double getFinalPrice() {
        return basePrice - (basePrice * discount / 100);
    }

    public double getBasePrice() { return basePrice; }
    public double getDiscount() { return discount; }
    public String getCurrencyCode() { return currencyCode; }
}

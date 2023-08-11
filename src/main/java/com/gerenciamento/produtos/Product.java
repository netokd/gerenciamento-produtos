package com.gerenciamento.produtos;

public class Product {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public Product(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
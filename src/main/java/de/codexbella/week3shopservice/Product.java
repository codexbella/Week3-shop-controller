package de.codexbella.week3shopservice;

public class Product {
    private String name = "";
    private int productID;

    public Product(String name, int id) {
        this.name = name;
        this.productID = id;
    }

    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
    }

    @Override
    public String toString() {
        return "product name: "+name+", product id: "+ productID;
    }
}

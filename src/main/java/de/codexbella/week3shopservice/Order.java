package de.codexbella.week3shopservice;

import java.util.List;

public class Order {
    private int orderID;
    private List<Product> products;

    public Order(int id, List<Product> products) {
        this.orderID = id;
        this.products = products;
    }

    public String toString() {
        return "Order no. "+orderID+", "+products;
    }

    public int getOrderID() {
        return orderID;
    }
}

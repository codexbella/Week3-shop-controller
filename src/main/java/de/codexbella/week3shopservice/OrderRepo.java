package de.codexbella.week3shopservice;

import java.util.List;

public class OrderRepo {
    private List<Order> orderList;//TODO change to Hashmap

    public OrderRepo(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Order getOrder(int id) {
        List<Order> orderList = this.orderList;
        for (int i = 0; i < orderList.size(); i++) {//TODO replace with for each
            Order currentOrder = orderList.get(i);
            if (currentOrder.getOrderID() == id) {
                return currentOrder;
            }
        }
        throw new RuntimeException("No such order in order repo.");
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public boolean add(List<Product> productsForOrder) {
        int newOrderID = orderList.get(orderList.size()-1).getOrderID() + 1;
        Order newOrder = new Order(newOrderID, productsForOrder);
        return orderList.add(newOrder);
    }
}

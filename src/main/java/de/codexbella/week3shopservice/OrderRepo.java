package de.codexbella.week3shopservice;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class OrderRepo {
    private List<Order> orderList;//TODO change to Hashmap

    public OrderRepo(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Order getOrder(int id) {
        List<Order> orderList = this.orderList;
        //Order currentOrder = orderList.stream().filter(order -> order.getOrderID() == id).toList().get(0);
        //oder (mit Optional):
        Optional<Order> currentOrderOptional = orderList.stream()
                .filter(order -> order.getOrderID() == id)
                .findFirst();
        if (currentOrderOptional.isPresent()) {
            return currentOrderOptional.get();
        }
        throw new RuntimeException("No such order in order repo.");
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public boolean add(List<Product> productsForOrder) {
        if (orderList.isEmpty() == true) {
            return orderList.add(new Order(10000, productsForOrder));
        } else {
            int newOrderID = orderList.get(orderList.size()-1).getOrderID() + 1;
            return orderList.add(new Order(newOrderID, productsForOrder));
        }
    }
}

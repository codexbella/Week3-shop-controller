package de.codexbella.week3shopservice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public String getProductName(int id) {
        return productRepo.getProductName(id);
    }

    public List<Product> getProductList() {
        return productRepo.getProductList();
    }

    public Order getOrder(int id) {
        return orderRepo.getOrder(id);
    }

    public List<Order> getOrders() {
        return orderRepo.getOrders();
    }

    public Product getProduct(int id) {
        return productRepo.getProduct(id);
    }

    public boolean placeOrder(List<Product> productsForOrder) {
        return orderRepo.add(productsForOrder);
    }

    public boolean initializeProductList() {
        return productRepo.initializeProductList();
    }
}

package de.codexbella.week3shopservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO Punkte aus Aufgabe abtesten und gegebenenfalls korrigieren
@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return shopService.getProductList();
    }
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return shopService.getOrders();
    }
    @GetMapping("/{id}")
    public Product getProductbyID(@PathVariable int id) {
        return shopService.getProduct(id);
    }
    @GetMapping("/initialize")
    public boolean initializeProductList() {
        return shopService.initializeProductList();
    }

    @PostMapping
    public boolean placeOrder(@RequestBody List<Product> productsToOrder) {
        return shopService.placeOrder(productsToOrder);
    }
}

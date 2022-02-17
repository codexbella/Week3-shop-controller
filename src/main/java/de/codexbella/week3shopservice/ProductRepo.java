package de.codexbella.week3shopservice;

import org.springframework.stereotype.Repository;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepo {

    private List<Product> productList;

    public ProductRepo(List<Product> productList) {
        this.productList = productList;
    }

    public String getProductName(int id) {
        List<Product> productList = this.productList;
        for (Product p : productList) {
            if (p.getProductID() == id) {
                return p.getName();
            }
        }
        return "Product not part of inventory.";
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProduct(int id) {
        List<Product> productList = this.productList;
        Optional<Product> currentProductOptional = productList.stream()
                .filter(product -> product.getProductID() == id)
                .findFirst();
        return currentProductOptional.orElseThrow(() -> new InvalidParameterException("Product not available."));
    }

    public boolean initializeProductList() {
        return productList.add(new Product("Tauchsäge", 70010010))
                && productList.add(new Product("Führungsschiene für Tauchsäge", 70010011))
                && productList.add(new Product("Winkelschleifer", 70010020))
                && productList.add(new Product("Multitool", 70010030))
                && productList.add(new Product("Druckluftschrauber", 70010040))
                && productList.add(new Product("Absaugmobil", 70010000))
                && productList.add(new Product("Kompressor", 70010001));    }
}

package de.codexbella.week3shopservice;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepo {

    private List<Product> productList;

    public ProductRepo(List<Product> productList) {
        this.productList = productList;
    }

    public String getProductName(int id) {
        List<Product> productList = this.productList;
        for (int i = 0; i < productList.size(); i++) { //TODO replace with for each
            Product currentProduct = productList.get(i);
            if (currentProduct.getProductID() == id) {
                return currentProduct.getName();
            }
        }
        return "Product not part of inventory.";
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProduct(int id) {
        List<Product> productList = this.productList;
            for (int i = 0; i < productList.size(); i++) {
                Product currentProduct = productList.get(i);
                if (currentProduct.getProductID() == id) {
                    return currentProduct;
                }
            }
            throw new RuntimeException("Product not available.");
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

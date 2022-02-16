package de.codexbella.week3shopservice;

import java.util.List;

public class ProductRepo {
    private List<Product> productList;//TODO change to Hashmap

    public ProductRepo(List<Product> productList) {
        this.productList = productList;
    }
    //TODO noch ein Konstruktor ohne Übergabe der productList und add-Methode

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
}

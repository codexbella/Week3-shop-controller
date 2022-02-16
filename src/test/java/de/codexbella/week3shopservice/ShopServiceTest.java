package de.codexbella.week3shopservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class ShopServiceTest {
    @Test
    void shouldReturnASingleProductByIDWithMock() {
        ProductRepo mockProductRepo = Mockito.mock(ProductRepo.class);
        OrderRepo mockOrderRepo = Mockito.mock(OrderRepo.class);

        ShopService testShopService = new ShopService(mockProductRepo, mockOrderRepo);
        when(mockProductRepo.getProductName(70010010)).thenReturn("Tauchsäge");

        Assertions.assertEquals("Tauchsäge", testShopService.getProductName(70010010));
    }
    @Test
    void shouldReturnASingleProductByID() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        List<Product> testProductList = new ArrayList<>();
        testProductList.add(testProduct1);
        ProductRepo testProductRepo = new ProductRepo(testProductList);

        ShopService testShopService = new ShopService(testProductRepo, new OrderRepo(List.of()));

        Assertions.assertEquals("Tauchsäge", testShopService.getProductName(70010010));
    }

    @Test
    void shouldReturnNotPartOfInventory() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        List<Product> testProductList = new ArrayList<>();
        testProductList.add(testProduct1);
        ProductRepo testProductRepo = new ProductRepo(testProductList);

        ShopService testShopService = new ShopService(testProductRepo, new OrderRepo(List.of()));

        Assertions.assertEquals("Product not part of inventory.", testShopService.getProductName(70010000));
    }
    @Test
    void shouldReturnAllProductsWithMock() {
        ProductRepo mockProductRepo = Mockito.mock(ProductRepo.class);
        OrderRepo mockOrderRepo = Mockito.mock(OrderRepo.class);

        ShopService testShopService = new ShopService(mockProductRepo, mockOrderRepo);

        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        List<Product> testProductList = List.of(testProduct1, testProduct2, testProduct3);
        when(testShopService.getProductList()).thenReturn(testProductList);

        Assertions.assertEquals(testProductList, testShopService.getProductList());
    }
    @Test
    void shouldReturnAllProducts() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        ShopService testShopService = new ShopService(testProductRepo, new OrderRepo(List.of()));

        String expected = "[product name: Tauchsäge, product id: 70010010, product name: Führungsschiene für Tauchsäge, product id: 70010011, product name: Winkelschleifer, product id: 70010020, product name: Multitool, product id: 70010030, product name: Druckluftschrauber, product id: 70010040, product name: Absaugmobil, product id: 70010000]";
        Assertions.assertEquals(expected, testShopService.getProductList().toString());
    }
    @Test
    void shouldReturnASingleOrderByOrderID() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);
        Product testProduct7 = new Product("Kompressor", 70010001);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);
        testProductList.add(testProduct7);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        List<Product> productsForOrder1 = new ArrayList<>();
        productsForOrder1.add(testProductRepo.getProduct(70010010));
        productsForOrder1.add(testProductRepo.getProduct(70010020));
        productsForOrder1.add(testProductRepo.getProduct(70010040));

        List<Product> productsForOrder2 = new ArrayList<>();
        productsForOrder2.add(testProductRepo.getProduct(70010030));

        List<Product> productsForOrder3 = new ArrayList<>();
        productsForOrder3.add(testProductRepo.getProduct(70010010));
        productsForOrder3.add(testProductRepo.getProduct(70010000));

        List<Product> productsForOrder4 = new ArrayList<>();
        productsForOrder4.add(testProductRepo.getProduct(70010011));
        productsForOrder4.add(testProductRepo.getProduct(70010020));
        productsForOrder4.add(testProductRepo.getProduct(70010040));
        productsForOrder4.add(testProductRepo.getProduct(70010000));
        productsForOrder4.add(testProductRepo.getProduct(70010001));

        Order testOrder1 = new Order(1001, productsForOrder1);
        Order testOrder2 = new Order(1002, productsForOrder2);
        Order testOrder3 = new Order(1003, productsForOrder3);
        Order testOrder4 = new Order(1004, productsForOrder4);

        List<Order> testOrderList = new ArrayList<>();
        testOrderList.add(testOrder1);
        testOrderList.add(testOrder2);
        testOrderList.add(testOrder3);
        testOrderList.add(testOrder4);

        OrderRepo testOrderRepo = new OrderRepo(testOrderList);

        ShopService testShopService = new ShopService(testProductRepo, testOrderRepo);

        String expected1 = "Order no. 1001, [product name: Tauchsäge, product id: 70010010, " +
                "product name: Winkelschleifer, product id: 70010020, " +
                "product name: Druckluftschrauber, product id: 70010040]";
        String expected2 = "Order no. 1002, [product name: Multitool, product id: 70010030]";

        Assertions.assertEquals(expected1, testShopService.getOrder(1001).toString());
        Assertions.assertEquals(expected2, testShopService.getOrder(1002).toString());
    }
    @Test
    void shouldReturnAllOrders() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);
        Product testProduct7 = new Product("Kompressor", 70010001);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        List<Product> productsForOrder1 = new ArrayList<>();
        productsForOrder1.add(testProductRepo.getProduct(70010010));

        List<Product> productsForOrder2 = new ArrayList<>();
        productsForOrder2.add(testProductRepo.getProduct(70010011));

        List<Product> productsForOrder3 = new ArrayList<>();
        productsForOrder3.add(testProductRepo.getProduct(70010020));
        productsForOrder3.add(testProductRepo.getProduct(70010030));

        Order testOrder1 = new Order(1001, productsForOrder1);
        Order testOrder2 = new Order(1002, productsForOrder2);
        Order testOrder3 = new Order(1003, productsForOrder3);

        List<Order> testOrderList = new ArrayList<>();
        testOrderList.add(testOrder1);
        testOrderList.add(testOrder2);
        testOrderList.add(testOrder3);

        OrderRepo testOrderRepo = new OrderRepo(testOrderList);

        ShopService testShopService = new ShopService(testProductRepo, testOrderRepo);

        String expected = "[Order no. 1001, [product name: Tauchsäge, product id: 70010010], " +
                "Order no. 1002, [product name: Führungsschiene für Tauchsäge, product id: 70010011], " +
                "Order no. 1003, [product name: Winkelschleifer, product id: 70010020, product name: Multitool, product id: 70010030]]";

        Assertions.assertEquals(expected, testShopService.getOrders().toString());
    }
    @Test
    void placingAnOrder() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);
        Product testProduct7 = new Product("Kompressor", 70010001);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);
        testProductList.add(testProduct7);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        List<Product> productsForOrder1 = new ArrayList<>();
        productsForOrder1.add(testProductRepo.getProduct(70010010));
        productsForOrder1.add(testProductRepo.getProduct(70010020));
        productsForOrder1.add(testProductRepo.getProduct(70010040));

        List<Product> productsForOrder2 = new ArrayList<>();
        productsForOrder2.add(testProductRepo.getProduct(70010030));

        List<Product> productsForOrder3 = new ArrayList<>();
        productsForOrder3.add(testProductRepo.getProduct(70010010));
        productsForOrder3.add(testProductRepo.getProduct(70010000));

        List<Order> testOrderList = new ArrayList<>();
        testOrderList.add(new Order(1001, productsForOrder1));
        testOrderList.add(new Order(1002, productsForOrder2));
        testOrderList.add(new Order(1003, productsForOrder3));

        OrderRepo testOrderRepo = new OrderRepo(testOrderList);

        List<Product> productsForOrder4 = new ArrayList<>();
        productsForOrder4.add(testProductRepo.getProduct(70010001));

        ShopService testShopService = new ShopService(testProductRepo, testOrderRepo);

        testShopService.placeOrder(productsForOrder4);

        String expected = "Order no. 1004, [product name: Kompressor, product id: 70010001]";

        Assertions.assertEquals(expected, testShopService.getOrder(1004).toString());
    }
    @Test
    void placingAnOrderOfUnknownProductCausesRuntimeException() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        List<Product> productsForOrder1 = new ArrayList<>();
        productsForOrder1.add(testProductRepo.getProduct(70010010));
        productsForOrder1.add(testProductRepo.getProduct(70010020));
        productsForOrder1.add(testProductRepo.getProduct(70010040));

        List<Product> productsForOrder2 = new ArrayList<>();
        productsForOrder2.add(testProductRepo.getProduct(70010030));

        List<Product> productsForOrder3 = new ArrayList<>();
        productsForOrder3.add(testProductRepo.getProduct(70010010));
        productsForOrder3.add(testProductRepo.getProduct(70010000));

        Order testOrder1 = new Order(1001, productsForOrder1);
        Order testOrder2 = new Order(1002, productsForOrder2);
        Order testOrder3 = new Order(1003, productsForOrder3);

        List<Order> testOrderList = new ArrayList<>();
        testOrderList.add(testOrder1);
        testOrderList.add(testOrder2);
        testOrderList.add(testOrder3);

        OrderRepo testOrderRepo = new OrderRepo(testOrderList);

        List<Product> productsForOrder4 = new ArrayList<>();

        ShopService testShopService = new ShopService(testProductRepo, testOrderRepo);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    productsForOrder4.add(testShopService.getProduct(70010001));
                }
        );//TODO mit e.getMessage()
    }
    @Test
    void gettingOfUnknownProductCausesRuntimeException() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);
        Product testProduct7 = new Product("Kompressor", 70010001);

        List<Product> testProductList = new ArrayList<>();
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        ShopService testShopService = new ShopService(testProductRepo, new OrderRepo(List.of()));

        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    testShopService.getProduct(70010010);
                }
        );//TODO mit e.getMessage()
    }
    @Test
    void gettingOfUnknownOrderCausesRuntimeException() {
        Product testProduct1 = new Product("Tauchsäge", 70010010);
        Product testProduct2 = new Product("Führungsschiene für Tauchsäge", 70010011);
        Product testProduct3 = new Product("Winkelschleifer", 70010020);
        Product testProduct4 = new Product("Multitool", 70010030);
        Product testProduct5 = new Product("Druckluftschrauber", 70010040);
        Product testProduct6 = new Product("Absaugmobil", 70010000);
        Product testProduct7 = new Product("Kompressor", 70010001);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        testProductList.add(testProduct3);
        testProductList.add(testProduct4);
        testProductList.add(testProduct5);
        testProductList.add(testProduct6);
        testProductList.add(testProduct7);

        ProductRepo testProductRepo = new ProductRepo(testProductList);

        List<Product> productsForOrder1 = new ArrayList<>();
        productsForOrder1.add(testProductRepo.getProduct(70010010));
        productsForOrder1.add(testProductRepo.getProduct(70010020));
        productsForOrder1.add(testProductRepo.getProduct(70010040));

        List<Product> productsForOrder3 = new ArrayList<>();
        productsForOrder3.add(testProductRepo.getProduct(70010010));
        productsForOrder3.add(testProductRepo.getProduct(70010000));

        List<Product> productsForOrder4 = new ArrayList<>();
        productsForOrder4.add(testProductRepo.getProduct(70010011));
        productsForOrder4.add(testProductRepo.getProduct(70010020));
        productsForOrder4.add(testProductRepo.getProduct(70010040));
        productsForOrder4.add(testProductRepo.getProduct(70010000));
        productsForOrder4.add(testProductRepo.getProduct(70010001));

        Order testOrder1 = new Order(1001, productsForOrder1);
        Order testOrder3 = new Order(1003, productsForOrder3);
        Order testOrder4 = new Order(1004, productsForOrder4);

        List<Order> testOrderList = new ArrayList<>();
        testOrderList.add(testOrder1);
        testOrderList.add(testOrder3);
        testOrderList.add(testOrder4);

        OrderRepo testOrderRepo = new OrderRepo(testOrderList);

        ShopService testShopService = new ShopService(testProductRepo, testOrderRepo);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    testShopService.getOrder(1002);
                }
        );//TODO mit e.getMessage()
    }
}
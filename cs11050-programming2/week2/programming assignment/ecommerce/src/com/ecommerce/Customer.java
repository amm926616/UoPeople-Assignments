package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerID;
    private String name;
    private List<Product> shoppingCart;

    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        shoppingCart.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeFromCart(Product product) {
        if (shoppingCart.remove(product)) {
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println(product.getName() + " not found in cart.");
        }
    }

    public double calculateTotalCost() {
        return shoppingCart.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public String getName() {
        return name;
    }
}

package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.util.List;

public class Order {
    private String orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;

    public Order(String orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = customer.getShoppingCart();
        this.orderTotal = customer.calculateTotalCost();
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Products:\n");
        for (Product product : products) {
            summary.append("- ").append(product.getName()).append(" ($").append(product.getPrice()).append(")\n");
        }
        summary.append("Order Total: $").append(orderTotal);
        return summary.toString();
    }
}

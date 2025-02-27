package com;

import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create sample products
        List<Product> products = new ArrayList<>();
        products.add(new Product("P001", "Laptop", 899.99));
        products.add(new Product("P002", "Smartphone", 499.99));
        products.add(new Product("P003", "Headphones", 59.99));
        products.add(new Product("P004", "Smartwatch", 199.99));

        // Create a customer
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Store!");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer("C001", customerName);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nMENU:");
            System.out.println("1. Browse Products");
            System.out.println("2. View Shopping Cart");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Place Order");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Display available products
                    System.out.println("\nAvailable Products:");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    // Display shopping cart
                    System.out.println("\nYour Shopping Cart:");
                    List<Product> cart = customer.getShoppingCart();
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        for (Product product : cart) {
                            System.out.println(product);
                        }
                        System.out.println("Total Cost: $" + customer.calculateTotalCost());
                    }
                    break;

                case 3:
                    // Add product to cart
                    System.out.println("\nEnter the Product ID to add to your cart: ");
                    String productIDToAdd = scanner.nextLine();
                    Product productToAdd = findProductById(products, productIDToAdd);
                    if (productToAdd != null) {
                        customer.addToCart(productToAdd);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    // Remove product from cart
                    System.out.println("\nEnter the Product ID to remove from your cart: ");
                    String productIDToRemove = scanner.nextLine();
                    Product productToRemove = findProductById(customer.getShoppingCart(), productIDToRemove);
                    if (productToRemove != null) {
                        customer.removeFromCart(productToRemove);
                    } else {
                        System.out.println("Product not found in your cart.");
                    }
                    break;

                case 5:
                    // Place order
                    if (customer.getShoppingCart().isEmpty()) {
                        System.out.println("Your cart is empty. Add items before placing an order.");
                    } else {
                        Order order = new Order("O001", customer);
                        System.out.println("\nOrder Placed Successfully!");
                        System.out.println(order.generateOrderSummary());
                        isRunning = false; // Exit after placing an order
                    }
                    break;

                case 6:
                    // Exit
                    System.out.println("Thank you for visiting the Online Store. Goodbye!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // Helper method to find a product by ID
    private static Product findProductById(List<Product> products, String productID) {
        for (Product product : products) {
            if (product.getProductID().equalsIgnoreCase(productID)) {
                return product;
            }
        }
        return null;
    }
}

package com.uopeople.amm;

import java.util.Scanner;

public class LibraryCatalogApp {
    public static void main(String[] args) {
        GenericCatalog<LibraryItem> catalog = new GenericCatalog<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Catalog Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Display Catalog");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Item ID: ");
                    String itemID = scanner.nextLine();
                    LibraryItem item = new LibraryItem(title, author, itemID);
                    catalog.addItem(item);
                    break;

                case 2:
                    System.out.print("Enter Item ID to remove: ");
                    String removeID = scanner.nextLine();
                    LibraryItem removeItem = null;
                    for (LibraryItem li : catalog.getCatalog()) {
                        if (li.getItemID().equals(removeID)) {
                            removeItem = li;
                            break;
                        }
                    }
                    if (removeItem != null) {
                        catalog.removeItem(removeItem);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    catalog.displayCatalog();
                    break;

                case 4:
                    System.out.println("Exiting the program.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

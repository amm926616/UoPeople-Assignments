package com.uopeople.amm;

import java.util.ArrayList;
import java.util.List;

public class GenericCatalog<T> {
    private List<T> catalog;

    public GenericCatalog() {
        this.setCatalog(new ArrayList<>());
    }

    public void addItem(T item) {
        getCatalog().add(item);
        System.out.println("Item added: " + item);
    }

    public void removeItem(T item) {
        if (getCatalog().contains(item)) {
            getCatalog().remove(item);
            System.out.println("Item removed: " + item);
        } else {
            System.out.println("Error: Item not found in the catalog.");
        }
    }

    public void displayCatalog() {
        System.out.println("Catalog Items:");
        for (T item : getCatalog()) {
            System.out.println(item);
        }
    }

	public List<T> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<T> catalog) {
		this.catalog = catalog;
	}
}

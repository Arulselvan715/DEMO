
package com.rest.Repository;

import com.rest.Models.MenuItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuItemRepository {

    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemRepository() {
        menuItems.add(new MenuItem(1, "Burger", 120.00));
        menuItems.add(new MenuItem(2, "Pizza", 250.00));
        menuItems.add(new MenuItem(3, "Fried Rice", 150.00));
        menuItems.add(new MenuItem(4, "Coffee", 40.00));
    }

    public void add(MenuItem item) {
        if (findById(item.getId()).isPresent()) {
            throw new IllegalArgumentException("Duplicate menu item ID");
        }
        menuItems.add(item);
    }

    public List<MenuItem> findAll() {
        return Collections.unmodifiableList(
                new ArrayList<>(menuItems));
    }

    public Optional<MenuItem> findById(int id) {
        return menuItems.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }
}

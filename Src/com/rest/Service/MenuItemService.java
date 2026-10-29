
package com.rest.Service;

import com.rest.Models.MenuItem;
import com.rest.Repository.MenuItemRepository;
import java.util.List;

public class MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public MenuItem getMenuItemById(int id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Menu item not found: " + id
                    )
                );
    }

    public void addMenuItem(int id, String name, double price) {
        repository.add(new MenuItem(id, name, price));
    }

    public void updateAvailability(
            int id, MenuItem.Availability availability) {
        getMenuItemById(id).setAvailability(availability);
    }
}

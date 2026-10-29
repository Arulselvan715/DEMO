
package com.rest.Models;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    public enum Status {
        PLACED,
        COMPLETED,
        CANCELLED
    }

    private final int id;
    private final int tableId;
    private final Map<Integer, Integer> items;
    private Status status;

    public Order(int id, int tableId) {
        if (id <= 0 || tableId <= 0) {
            throw new IllegalArgumentException(
                    "Order ID and table ID must be positive");
        }

        this.id = id;
        this.tableId = tableId;
        this.items = new LinkedHashMap<>();
        this.status = Status.PLACED;
    }

    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public Map<Integer, Integer> getItems() {
        return Collections.unmodifiableMap(
                new LinkedHashMap<>(items));
    }

    public Status getStatus() {
        return status;
    }

    public void addItem(int menuItemId, int quantity) {
        if (menuItemId <= 0 || quantity <= 0) {
            throw new IllegalArgumentException(
                    "Item ID and quantity must be positive");
        }

        items.merge(menuItemId, quantity, Integer::sum);
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
        this.status = status;
    }
}

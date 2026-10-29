
package com.rest.Repository;

import com.rest.Models.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        if (findById(order.getId()).isPresent()) {
            throw new IllegalArgumentException("Duplicate order ID");
        }
        orders.add(order);
    }

    public List<Order> findAll() {
        return Collections.unmodifiableList(
                new ArrayList<>(orders));
    }

    public Optional<Order> findById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }
}

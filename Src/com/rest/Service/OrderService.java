
package com.rest.Service;

import com.rest.Models.MenuItem;
import com.rest.Models.Order;
import com.rest.Repository.MenuItemRepository;
import com.rest.Repository.OrderRepository;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuRepository;
    private int nextOrderId = 1;

    public OrderService(MenuItemRepository menuRepository) {
        this.orderRepository = new OrderRepository();
        this.menuRepository = menuRepository;
    }

    public Order createOrder(int tableId) {
        Order order = new Order(nextOrderId++, tableId);
        orderRepository.add(order);
        return order;
    }

    public void addItemToOrder(
            int orderId, int menuItemId, int quantity) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                    new IllegalArgumentException("Order not found"));

        if (order.getStatus() != Order.Status.PLACED) {
            throw new IllegalStateException(
                    "Cannot modify this order");
        }

        MenuItem item = menuRepository.findById(menuItemId)
                .orElseThrow(() ->
                    new IllegalArgumentException("Menu item not found"));

        if (item.getAvailability()
                != MenuItem.Availability.AVAILABLE) {
            throw new IllegalStateException(
                    "Menu item is unavailable");
        }

        order.addItem(menuItemId, quantity);
    }

    public double calculateTotal(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                    new IllegalArgumentException("Order not found"));

        double total = 0;

        for (var entry : order.getItems().entrySet()) {
            MenuItem item = menuRepository.findById(entry.getKey())
                    .orElseThrow(() ->
                        new IllegalStateException("Menu item missing"));

            total += item.getPrice() * entry.getValue();
        }

        return total;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateStatus(int orderId, Order.Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                    new IllegalArgumentException("Order not found"));

        order.setStatus(status);
    }
}

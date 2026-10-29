
package com.rest.CLI;

import com.rest.Models.Table;
import com.rest.Models.MenuItem;
import com.rest.Models.Order;
import com.rest.Service.TableService;
import com.rest.Service.MenuItemService;
import com.rest.Service.OrderService;

import java.util.Scanner;

public class TableCli {

    private final TableService tableService;
    private final MenuItemService menuService;
    private final OrderService orderService;
    private final Scanner scan = new Scanner(System.in);

    public TableCli(
            MenuItemService menuService,
            OrderService orderService) {

        this.tableService = new TableService();
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Restaurant Management System ===");
            System.out.println("1. View all tables");
            System.out.println("2. View menu items");
            System.out.println("3. Create order");
            System.out.println("4. View all orders");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            if (!scan.hasNextLine()) {
                System.out.println("Input unavailable.");
                return;
            }

            String input = scan.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        viewTables();
                        break;

                    case 2:
                        viewMenu();
                        break;

                    case 3:
                        createOrder();
                        break;

                    case 4:
                        viewOrders();
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException |
                     IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void viewTables() {
        System.out.println("\n--- All Tables ---");

        for (Table table : tableService.getAllTables()) {
            System.out.println(
                "ID: " + table.getId()
                + ", Capacity: " + table.getCapacity()
                + ", Status: " + table.getStatus()
            );
        }
    }

    private void viewMenu() {
        System.out.println("\n--- Menu Items ---");

        for (MenuItem item : menuService.getAllMenuItems()) {
            System.out.println(item);
        }
    }

    private void createOrder() {
        System.out.print("Enter table ID: ");
        int tableId = readPositiveInt();

        System.out.println("\n--- Menu Items ---");
        viewMenu();

        Order order = orderService.createOrder(tableId);

        while (true) {
            System.out.print(
                "Enter menu item ID (0 to finish): ");
            int itemId = readNonNegativeInt();

            if (itemId == 0) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = readPositiveInt();

            orderService.addItemToOrder(
                order.getId(), itemId, quantity);

            System.out.println("Item added.");
        }

        System.out.println("\n--- Order Summary ---");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Table ID: " + order.getTableId());

        for (var entry : order.getItems().entrySet()) {
            MenuItem item =
                menuService.getMenuItemById(entry.getKey());

            System.out.println(
                item.getName() + " x " + entry.getValue()
                + " = Rs. "
                + String.format("%.2f",
                    item.getPrice() * entry.getValue())
            );
        }

        System.out.printf("Total: Rs. %.2f%n",
            orderService.calculateTotal(order.getId()));
    }

    private void viewOrders() {
        System.out.println("\n--- All Orders ---");

        for (Order order : orderService.getAllOrders()) {
            System.out.printf(
                "Order ID: %d | Table ID: %d | Status: %s"
                + " | Total: Rs. %.2f%n",
                order.getId(),
                order.getTableId(),
                order.getStatus(),
                orderService.calculateTotal(order.getId())
            );
        }
    }

    private int readPositiveInt() {
        int value = readInteger();

        if (value <= 0) {
            throw new IllegalArgumentException(
                "Value must be greater than zero.");
        }

        return value;
    }

    private int readNonNegativeInt() {
        int value = readInteger();

        if (value < 0) {
            throw new IllegalArgumentException(
                "Value cannot be negative.");
        }

        return value;
    }

    private int readInteger() {
        if (!scan.hasNextLine()) {
            throw new IllegalStateException(
                "Input unavailable.");
        }

        String input = scan.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Please enter a valid integer.");
        }
    }
}

package com.rest.Models;

public class MenuItem {

    public enum Availability {
        AVAILABLE,
        UNAVAILABLE
    }

    private final int id;
    private String name;
    private double price;
    private Availability availability;

    public MenuItem(int id, String name, double price) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (price < 0 || !Double.isFinite(price)) {
            throw new IllegalArgumentException("Invalid price");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.availability = Availability.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0 || !Double.isFinite(price)) {
            throw new IllegalArgumentException("Invalid price");
        }
        this.price = price;
    }

    public void setAvailability(Availability availability) {
        if (availability == null) {
            throw new IllegalArgumentException("Availability is required");
        }
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Price: Rs." + String.format("%.2f", price)
                + " | Status: " + availability;
    }
}
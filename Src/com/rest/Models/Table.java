
package com.rest.Models;

public class Table {

    public enum Availability {
        OCCUPIED, FREE
    }

    private final int id;
    private final int capacity;
    private Availability status;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.status = Availability.FREE;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Availability getStatus() {
        return status;
    }
}

package com.aakanksha.costestimationproject;

public class Client {
    private int id;
    private String name;

    // Constructor with id and name
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

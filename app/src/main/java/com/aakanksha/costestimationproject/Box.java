package com.aakanksha.costestimationproject;

public class Box {
    private String name;
    private double cost;

    public Box(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}

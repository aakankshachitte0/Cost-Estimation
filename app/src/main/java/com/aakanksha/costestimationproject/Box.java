package com.aakanksha.costestimationproject;

public class Box {
    public String name;
    public double length;
    public double height;
    public int flutePapers;
    public int plainPapers;
    public double paperCost;
    public double qualityFactor;
    public double finalCost;
    public int clientId;

    // 👉 Full constructor for database saving and usage
    public Box(String name, double length, double height, int flutePapers, int plainPapers,
               double paperCost, double qualityFactor, int clientId) {

        this.name = name;
        this.length = length;
        this.height = height;
        this.flutePapers = flutePapers;
        this.plainPapers = plainPapers;
        this.paperCost = paperCost;
        this.qualityFactor = qualityFactor;
        this.clientId = clientId;

        // ✅ Calculate final cost using the formula
        this.finalCost = height * length * flutePapers * plainPapers * paperCost + qualityFactor;
    }

    // 👉 Minimal constructor (used in dummy data or display-only)
    public Box(String name, double finalCost) {
        this.name = name;
        this.finalCost = finalCost;
    }

    // Getters (optional)
    public String getName() {
        return name;
    }

    public double getFinalCost() {
        return finalCost;
    }
}

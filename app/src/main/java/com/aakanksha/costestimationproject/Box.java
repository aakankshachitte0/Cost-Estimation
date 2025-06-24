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

    public Box(String name, double length, double height, int flutePapers, int plainPapers,
               double paperCost, double qualityFactor, double finalCost, int clientId) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.flutePapers = flutePapers;
        this.plainPapers = plainPapers;
        this.paperCost = paperCost;
        this.qualityFactor = qualityFactor;
        this.finalCost = finalCost;
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public double getFinalCost() {
        return finalCost;
    }
}

package com.example.HikingApp_GCS200222;

public class Hike {
    private String hikeName;
    private String hikeLocation;
    private String hikeDate;
    private boolean hasParking;
    private double hikeDistance;
    private String difficulty;
    private String description;

    public Hike(String hikeName, String hikeLocation, String hikeDate, String hikeDistance, String difficulty, String description) {
        this.hikeName = hikeName;
        this.hikeLocation = hikeLocation;
        this.hikeDate = hikeDate;
        this.hasParking = hasParking;
        this.hikeDistance = Double.parseDouble(hikeDistance);
        this.difficulty = difficulty;
        this.description = description;
    }

    public String getHikeName() {
        return hikeName;
    }

    public String getHikeLocation() {
        return hikeLocation;
    }

    public String getHikeDate() {
        return hikeDate;
    }

    public boolean hasParking() {
        return hasParking;
    }

    public String getHikeDistance() {
        return String.valueOf(hikeDistance);
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Hike Name: " + hikeName + "\nLocation: " + hikeLocation + "\nDate: " + hikeDate +
                "\nHas Parking: " + (hasParking ? "Yes" : "No") + "\nHike Distance (in km): " + hikeDistance +
                "\nDifficulty: " + difficulty + "\nDescription: " + description;
    }
}

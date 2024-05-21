package com.example.HikingApp_GCS200222;

public class RatingLevel {
    private String name;
    private String description;

    public RatingLevel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

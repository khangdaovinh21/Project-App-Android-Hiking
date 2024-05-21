package com.example.HikingApp_GCS200222;

import android.graphics.Bitmap;

public class Observation {
    private int id;
    private String location;
    private String time;
    private String eventDescription;
    private String additionalInfo;
    private String note;
    private String title;
    private Bitmap photoBitmap;

    public Observation() {
        // Default constructor required for calls to DataSnapshot.getValue(Observation.class)
    }

    public Observation(String location, String time, String eventDescription, String additionalInfo, String note, String title) {
        this.location = location;
        this.time = time;
        this.eventDescription = eventDescription;
        this.additionalInfo = additionalInfo;
        this.note = note;
        this.title = title;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPhotoBitmap() {
        return photoBitmap;
    }

    public void setPhotoBitmap(Bitmap photoBitmap) {
        this.photoBitmap = photoBitmap;
    }
}

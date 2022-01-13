package com.example.mobproglabquiz1.models;

public class JobModel {

    private int id;
    private String title;
    private String description;
    private String creator;

    public JobModel(int id, String title, String creator) {
        this.id = id;
        this.title = title;
        this.creator = creator;
    }

    public JobModel(String title, String description, String creator) {
        this.title = title;
        this.description = description;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

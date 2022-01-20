package com.example.mobproglabquiz1.models;

public class JobModel {

    private int id;
    private String title;
    private String description;
    private double price;
    private String fullname;
    private int creator_id;

    public JobModel(int id, String title, String description, double price, String fullname) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.fullname = fullname;
    }

    public JobModel(int id, String title, String fullname) {
        this.id = id;
        this.title = title;
        this.fullname = fullname;
    }

    public JobModel(String title, String description, double price, int creator_id) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creator_id = creator_id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }
}

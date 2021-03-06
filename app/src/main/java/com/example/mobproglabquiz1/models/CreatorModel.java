package com.example.mobproglabquiz1.models;

public class CreatorModel {
    private int id;
    private int user_id;
    private String biodata;
    private String education;
    private String experience;

    public CreatorModel(int user_id, String biodata, String education, String experience) {
        this.user_id = user_id;
        this.biodata = biodata;
        this.education = education;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBiodata() {
        return biodata;
    }

    public void setBiodata(String biodata) {
        this.biodata = biodata;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}

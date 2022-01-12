package com.example.mobproglabquiz1.models;

public class UserModel {

    private String id;
    private String email;
    private String fullname;
    private String password;

    public UserModel(String id, String email, String fullname, String password) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
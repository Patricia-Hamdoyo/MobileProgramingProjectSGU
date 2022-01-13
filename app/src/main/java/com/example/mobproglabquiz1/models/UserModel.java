package com.example.mobproglabquiz1.models;

public class UserModel {

    private int id;
    private String email;
    private String fullname;
    private String password;

    private String salt;

    public UserModel(int id, String email, String fullname, String password, String salt) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.salt = salt;
    }

    public UserModel(String email, String fullname, String password) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
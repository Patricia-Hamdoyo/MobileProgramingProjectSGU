package com.example.mobproglabquiz1.utils;

public class Constants {

    private static final String BACKEND_URL = "http://103.150.98.211:3000/";

    public static String getRoute(String path) {
        return BACKEND_URL + path;
    }
}

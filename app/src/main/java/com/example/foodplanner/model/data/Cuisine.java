package com.example.foodplanner.model.data;

import java.io.Serializable;

public class Cuisine implements Serializable {
    private String strArea; // Adjusted field name

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}

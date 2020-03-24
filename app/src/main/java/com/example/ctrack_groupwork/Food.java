package com.example.ctrack_groupwork;

// Creating a food object
public class Food {
    private String foodName;
    private String foodCal;

    // Creation of constructor
    public Food(String foodName, String foodCal) {
        this.foodName = foodName;
        this.foodCal = foodCal;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCal() {
        return foodCal;
    }

    public void setFoodCal(String foodCal) {
        this.foodCal = foodCal;
    }
}

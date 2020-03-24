package com.example.ctrack_groupwork;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView mListView = (ListView) findViewById(R.id.listView);

        // Creating the food objects
        Food chickenBreast = new Food("100g of Chicken Breast", "165kcal");
        Food basmati = new Food("75g of Basmati Rice", "262kcal");
        Food milk = new Food("100ml of Milk", "42cal");
        Food egg = new Food("1 Large Egg(50g)", "78kcal");
        Food apple = new Food("1 Medium Apple", "95kcal");
        Food bread = new Food("2 Slices of Whole-wheat Bread", "247kcal");
        Food lentils = new Food("100g of Lentils", "116kcal");
        Food spinach = new Food("30g of Spinach", "5kcal");
        Food banana = new Food("100g of Banana", "89kcal");

        // Creation of array list
        ArrayList<Food> foodList = new ArrayList<>();
       // Adding the food objects to the array
        foodList.add(chickenBreast);
        foodList.add(basmati);
        foodList.add(milk);
        foodList.add(egg);
        foodList.add(apple);
        foodList.add(bread);
        foodList.add(lentils);
        foodList.add(spinach);
        foodList.add(banana);

        FoodListAdapter adapter = new FoodListAdapter(this, R.layout.adapter_view_layout, foodList);
        // Passing adapter to the list view as a parameter
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_nav:
                startActivity(new Intent(FoodList.this, Calories.class));
                return true;
            case R.id.list_nav:
            Toast.makeText(this, "Currently Selected", Toast.LENGTH_SHORT).show();
            //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
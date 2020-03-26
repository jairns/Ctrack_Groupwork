package com.example.ctrack_groupwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Calories extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private ProgressBar progressBar;
    int currentCal = 0;
    int dailyCal = 0;

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        dailyCal = settings.getInt("dailyCal", dailyCal);
        currentCal = settings.getInt("currentCal", currentCal);
        textView = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar);
        updateProgress();
    }

    //Defining navigation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Defining navigation actions
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Event Bubbling - Determining which menu item was selected
        // Example of switch statement
        switch (item.getItemId()) {
            case R.id.home_nav:
                // Current activity, notify the user of this via toast
                Toast.makeText(this, "Currently Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.list_nav:
                // Start FoodList activity
                startActivity(new Intent(Calories.this, FoodList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Example of method
    public void setDailyCal(View v){
        // Build alert dialog and set a title
        AlertDialog.Builder setDailyCalDialog = new AlertDialog.Builder(Calories.this);
        setDailyCalDialog.setTitle("Set Calories");
        // Alert dialog requires number input
        final EditText setDailyCalInput = new EditText(Calories.this);
        setDailyCalInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        setDailyCalDialog.setView(setDailyCalInput);
        // Actions when confirmation button is clicked
        setDailyCalDialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Assign value to integer
                dailyCal = Integer.valueOf(setDailyCalInput.getText().toString());
                // While user input equals 0, display toast and update shared preferences
                while(dailyCal == 0) {
                    Toast.makeText(Calories.this, "Cannot set calories to 0", Toast.LENGTH_LONG).show();
                    break;
                }
                while(dailyCal != 0) {
                    updateProgress();
                    Toast.makeText(Calories.this, "Calories set", Toast.LENGTH_LONG).show();
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putInt("dailyCal", dailyCal);
                    editor.apply();
                    break;
                }
             }
        });
        // If cancel is clicked, close alert dialog
        setDailyCalDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        setDailyCalDialog.show();
    }
    //Get and update calorie function
    public void addCal(View v){
        // Create alert dialog
        AlertDialog.Builder addCalDialog = new AlertDialog.Builder(Calories.this);
        addCalDialog.setTitle("Add Calories");
        // Define required data type
        final EditText addCalInput = new EditText(Calories.this);
        addCalInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        addCalDialog.setView(addCalInput);
        // If apply is selected, add the submitted value to the user's current calorie's, call update progress and save the value
        addCalDialog.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentCal += Integer.parseInt(addCalInput.getText().toString());
                    updateProgress();
                    Toast.makeText(Calories.this, "Calories added", Toast.LENGTH_LONG).show();
                    @SuppressLint("WrongConstant") SharedPreferences settings2 = getSharedPreferences(PREFS_NAME, 1);
                    SharedPreferences.Editor editor = settings2.edit();
                    editor.putInt("currentCal", currentCal);
                    editor.apply();

            }
        });
        // If cancel is clicked, close alert dialog
        addCalDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        addCalDialog.show();
    }

    // Update progress bar and check progress function
    public void updateProgress(){
        // Convert percentage into string for display
        textView.setText(String.valueOf(currentCal));
        textView2.setText(String.valueOf(dailyCal));
        // Checks value as cannot be implemented
        if(currentCal!= 0 & dailyCal !=0) {
            // Convert into float for calculation
            float percentage = (currentCal * 100/ dailyCal);
            // Convert percentage into integer for progress bar
            int progInt = (int)percentage;
            // Set progress bar
            progressBar.setProgress(progInt);
            // if percentage is greater than 99% update text colour to red - notifying the user they have reached their daily limit
            if(percentage > 99) {
                textView.setTextColor(Color.parseColor("#fe4a49"));
                progressBar.getProgressDrawable().setColorFilter(
                        Color.parseColor("#fe4a49"), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            // if percentage is between 70 and 99 percent update text colour
            else if (percentage > 70 && percentage <= 99) {
                textView.setTextColor(Color.parseColor("#fed766"));
                progressBar.getProgressDrawable().setColorFilter(
                        Color.parseColor("#fed766"), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            // if percentage is below 70 set text text colour
            else {
                textView.setTextColor(Color.parseColor("#2ab7ca"));
                progressBar.getProgressDrawable().setColorFilter(
                        Color.parseColor("#2ab7ca"), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    }
    // Reset currentCal variable to 0, and update this in memory
    public void resetCal(View view){
        currentCal = 0;
        progressBar.setProgress(0);
        textView.setTextColor(Color.parseColor("#2ab7ca"));
        progressBar.getProgressDrawable().setColorFilter(
                Color.parseColor("#2ab7ca"), android.graphics.PorterDuff.Mode.SRC_IN);
        @SuppressLint("WrongConstant") SharedPreferences settings2 = getSharedPreferences(PREFS_NAME, 1);
        SharedPreferences.Editor editor = settings2.edit();
        editor.putInt("currentCal", currentCal);
        editor.apply();
        updateProgress();
    }
}
package com.example.ctrack_groupwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter<Food> {
    private Context mContext;
    int mResource;

    // Creates constructor for the FoodListAdapter and requires parameters
    public FoodListAdapter(Context context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    // Gets the view - attaching it to list view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Grabs the food content
        String foodName = getItem(position).getFoodName();
        String foodCal = getItem(position).getFoodCal();

        //Creates the object with the food information
        Food food = new Food(foodName, foodCal);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvFoodName = convertView.findViewById(R.id.textView1);
        TextView tvFoodCal = convertView.findViewById(R.id.textView2);

        tvFoodName.setText(foodName);
        tvFoodCal.setText(foodCal);

        return convertView;
    }
}

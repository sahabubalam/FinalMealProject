package com.example.sahabub.finalmealproject;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Meal> {

    private Activity context;
    private List<Meal>mealList;

    public CustomAdapter(Activity context, List<Meal> mealList) {
        super(context, R.layout.sample_layout, mealList);
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);
        Meal meal  = mealList.get(position);
        TextView t1 = view.findViewById(R.id.requestedlayoutId);
        TextView t2 = view.findViewById(R.id.rollnumberlayoutId);
        TextView t3 = view.findViewById(R.id.datetimelayoutId);
        TextView t4 = view.findViewById(R.id.lunchlayoutId);
        TextView t5 = view.findViewById(R.id.dinnerlayoutId);

        t1.setText("Name:"+meal.getRequestedBy());
        t2.setText("Roll:"+meal.getRoll());
        t3.setText("Datetime:"+meal.getDatetime());
        t4.setText("Lunch:"+meal.getLunch());
        t5.setText("Dinner:"+meal.getDinner());
        return view;
    }
}

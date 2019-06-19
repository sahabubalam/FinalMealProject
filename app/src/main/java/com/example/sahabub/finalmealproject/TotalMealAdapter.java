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

public class TotalMealAdapter extends ArrayAdapter<Meal> {
    private Activity context;
    private List<Meal>totalMealList;

    public TotalMealAdapter(Activity context,List<Meal> totalMealList) {
        super(context, R.layout.sampleresource, totalMealList);
        this.context = context;
        this.totalMealList = totalMealList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sampleresource,null,true);
        Meal meal = totalMealList.get(position);

        TextView t1 = view.findViewById(R.id.lunchresourceId);
        TextView t2 = view.findViewById(R.id.dinnerresourceId);
        TextView t3 = view.findViewById(R.id.totalId);

        t1.setText("Lunch:"+meal.getLunch());
        t2.setText("Dinner:"+meal.getDinner());

        return view;
    }
}

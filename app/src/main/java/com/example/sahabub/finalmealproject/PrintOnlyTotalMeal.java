package com.example.sahabub.finalmealproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PrintOnlyTotalMeal extends AppCompatActivity {
    private TextView totalMealTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_only_total_meal);

        final String total = getIntent().getExtras().getString("sendT");

        totalMealTextView=(TextView)findViewById(R.id.totalMealTextVewi_id);

        totalMealTextView.setText("Total Meal: "+total);

    }
}

package com.example.sahabub.finalmealproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MealCountActivity extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private List<Meal> totalmealList;
    private TotalMealAdapter totalMealAdapter;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_count);


        final String catchRoll = getIntent().getExtras().getString("SendRoll");
        text = findViewById(R.id.totalId);


        totalmealList = new ArrayList<>();
        totalMealAdapter = new TotalMealAdapter(MealCountActivity.this,totalmealList);

        listView = (ListView) findViewById(R.id.listViewId);
        if(!catchRoll.equals("")) {


            databaseReference = FirebaseDatabase.getInstance().getReference("MealTable");

            try {
                databaseReference.child(catchRoll).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        totalmealList.clear();
                        int total = 0;

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Meal meal = dataSnapshot1.getValue(Meal.class);
                            String c1=meal.getLunch();
                            String c2=meal.getDinner();
                           // Integer cost1 = Integer.valueOf(meal.getLunch());
                           // Integer cost2 = Integer.valueOf(meal.getDinner());
                            int cc1 = Integer.parseInt(c1);
                            int cc2 = Integer.parseInt(c2);

                            total = total + cc1 + cc2;
                            String t = Integer.toString(total);

                            Toast.makeText(MealCountActivity.this, "total meal : "+t, Toast.LENGTH_SHORT).show();
                            totalmealList.add(meal);
                        }
                       // listView.setAdapter(totalMealAdapter);
                        text.setText(total);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error" + e, Toast.LENGTH_SHORT).show();

            }
        }

    }
}

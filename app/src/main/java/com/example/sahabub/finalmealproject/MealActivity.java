package com.example.sahabub.finalmealproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.mbms.FileServiceInfo;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference,databaseReference2;
    private List<Meal> mealList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        final String catchRoll =getIntent().getExtras().getString("SendMealRollNumber") ;


        mealList = new ArrayList<>();
        customAdapter = new CustomAdapter(MealActivity.this,mealList);
        listView = (ListView) findViewById(R.id.listViewId);


        // database work........................

        if(catchRoll.equals("")){

            databaseReference2 = FirebaseDatabase.getInstance().getReference("AllMealTable");

            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mealList.clear();

                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Meal meal = dataSnapshot1.getValue(Meal.class);
                        mealList.add(meal);
                    }
                    listView.setAdapter(customAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else{
            databaseReference = FirebaseDatabase.getInstance().getReference("MealTable");

            databaseReference.child(catchRoll).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mealList.clear();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                        Meal meal = dataSnapshot1.getValue(Meal.class);
                        mealList.add(meal);
                    }
                    listView.setAdapter(customAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}

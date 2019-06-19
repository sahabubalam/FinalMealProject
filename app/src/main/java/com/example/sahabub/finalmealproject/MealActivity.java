package com.example.sahabub.finalmealproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.mbms.FileServiceInfo;
import android.widget.ListView;
import android.widget.Toast;

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
    DatabaseReference databaseReference,databaseReference2,df;
    private List<Meal> mealList;
    CustomAdapter customAdapter;

    public String t="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        final String catchRoll =getIntent().getExtras().getString("SendMealRollNumber") ;
        String flag= "";
        flag = getIntent().getExtras().getString("sendFlag");

       // Toast.makeText(this, "flag: "+flag,Toast.LENGTH_SHORT).show();

        mealList = new ArrayList<>();
        customAdapter = new CustomAdapter(MealActivity.this,mealList);
        listView = (ListView) findViewById(R.id.listViewId);


        // database work........................
        if((!catchRoll.equals("")) && flag.equals("1")){

            df = FirebaseDatabase.getInstance().getReference("MealTable");
            df.child(catchRoll).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mealList.clear();
                    int total = 0;

                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Meal meal = dataSnapshot1.getValue(Meal.class);
                        //mealList.add(meal);

                        String c1=meal.getLunch();
                        String c2=meal.getDinner();

                        int cc1 = Integer.parseInt(c1);
                        int cc2 = Integer.parseInt(c2);

                        total = total + cc1 + cc2;
                        t = Integer.toString(total);

                        //Toast.makeText(MealActivity.this, "Total meal: "+t, Toast.LENGTH_SHORT).show();
                    }
                    //listView.setAdapter(customAdapter);
                   // Toast.makeText(MealActivity.this, "Total meal: "+t, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MealActivity.this,PrintOnlyTotalMeal.class);
                    intent.putExtra("sendT",t);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else if(catchRoll.equals("") && flag.equals("2")){

            databaseReference2 = FirebaseDatabase.getInstance().getReference("AllMealTable");

            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mealList.clear();
                    int total = 0;

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

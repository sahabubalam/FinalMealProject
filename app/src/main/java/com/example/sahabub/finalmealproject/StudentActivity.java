package com.example.sahabub.finalmealproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentActivity extends AppCompatActivity {
    
    private EditText requestedbyEditText,rollEditText,dateTimeEditText,lunchEditText,dinnerEditText,studentcostEditText;
    private Button sendButton,totalcostButton;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("MealTable");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("AllMealTable");

        requestedbyEditText = (EditText) findViewById(R.id.requestId);
        rollEditText =(EditText)findViewById(R.id.rollId);
        dateTimeEditText = (EditText) findViewById(R.id.datetimeId);

        lunchEditText =(EditText) findViewById(R.id.lunchId);
        dinnerEditText =(EditText) findViewById(R.id.dinnerId);
        studentcostEditText = (EditText)findViewById(R.id.studentCostId);
        
        sendButton =(Button) findViewById(R.id.sendId);
        totalcostButton =(Button) findViewById(R.id.totalcostId);

        totalcostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll = studentcostEditText.getText().toString();
                Toast.makeText(StudentActivity.this, ""+roll, Toast.LENGTH_SHORT).show();
                try{
                    Intent intent = new Intent(StudentActivity.this,MealActivity.class);
                    intent.putExtra("SendMealRollNumber",roll);

                    int f= 1;
                    String flag = Integer.toString(f);
                    intent.putExtra("sendFlag",flag);
                    Toast.makeText(StudentActivity.this, "flag: "+flag, Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(StudentActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }
     public void insertData() {

         String requestby = requestedbyEditText.getText().toString().trim();
         String roll = rollEditText.getText().toString().trim();
         String date = dateTimeEditText.getText().toString().trim();
         String lunch = lunchEditText.getText().toString().trim();
         String dinner = dinnerEditText.getText().toString().trim();

         String key = databaseReference.push().getKey();
         Meal meal = new Meal(requestby,roll,date, lunch, dinner);
         // for meal table data input.............................
         databaseReference.child(roll).push().setValue(meal);
         // for all meal table data input..........................
         databaseReference2.push().setValue(meal);
         Toast.makeText(getApplicationContext(), "your meal is on", Toast.LENGTH_SHORT).show();
     }







}

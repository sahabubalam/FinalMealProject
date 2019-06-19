package com.example.sahabub.finalmealproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText seeMealRoll;
    private Button showMealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        seeMealRoll=(EditText)findViewById(R.id.seeMealEditText_id);
        showMealButton=(Button)findViewById(R.id.showMealButton_id);

        showMealButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.showMealButton_id){

            String roll = seeMealRoll.getText().toString();
                try{
                    Intent intent = new Intent(ManagerActivity.this,MealActivity.class);

                    int f= 2;
                    String flag = Integer.toString(f);
                    intent.putExtra("sendFlag",flag);
                  //  Toast.makeText(this, "Manager: "+flag, Toast.LENGTH_SHORT).show();

                    intent.putExtra("SendMealRollNumber",roll);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


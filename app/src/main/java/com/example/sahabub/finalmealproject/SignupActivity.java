package com.example.sahabub.finalmealproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEditText,phoneEditText,passwordEditText,usertypeEditText;
    private Button signup;
    DatabaseReference databaseReference;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        //auth = FirebaseAuth.getInstance();
       // databaseReference = FirebaseDatabase.getInstance().getReference().child("UserTable");

        nameEditText = findViewById(R.id.signinNameEditTextId);
        phoneEditText = findViewById(R.id.signinPhoneEditTextId);
        passwordEditText = findViewById(R.id.signinPasswordEditTextId);
        usertypeEditText = findViewById(R.id.signinUserEditTextId);

        signup =(Button) findViewById(R.id.signUpButtonId);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.signUpButtonId: {
                        insertData();

                    }
                }

            }
        });

    }


    private void insertData() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserTable");
        final String name = nameEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        final String phone = phoneEditText.getText().toString().trim();

        final String usertype = usertypeEditText.getText().toString().toLowerCase().trim();

        Sign sign = new Sign(name, phone, password, usertype);

        try{
            databaseReference.child(phone).push().setValue(sign);
        }catch(Exception e){
            usertypeEditText.setText(e.toString());
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }



        Toast.makeText(this, "Data save Successfully", Toast.LENGTH_SHORT).show();

        //Intent intent = new Intent(SignupActivity.this,MainActivity.class);
       // startActivity(intent);




    }




}


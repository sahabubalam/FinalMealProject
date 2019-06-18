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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText phoneEditText,passwordEditText;
    private Button login,signup;
    private FirebaseAuth auth;
    String s;
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        phoneEditText = findViewById(R.id.userPhoneNumber_id);
        passwordEditText = findViewById(R.id.userPasswordId);

        login = findViewById(R.id.loginButtonId);
        signup = findViewById(R.id.signupButtonId);

        login.setOnClickListener( this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



           if(v.getId()==R.id.signupButtonId){

                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);

            }

            if(v.getId()==R.id.loginButtonId){
                final String TakePhone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // data search...................
                final DatabaseReference databaseReferenceCheck = FirebaseDatabase.getInstance().getReference("UserTable");
                databaseReferenceCheck.child(TakePhone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Sign signclass = dataSnapshot1.getValue(Sign.class);
                            s = signclass.getUsertype();
                            if(s.equals("student")){
                                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                startActivity(intent);
                                break;
                            }if(s.equals("manager")){
                                Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                                startActivity(intent);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

               // Toast.makeText(this, "Wrong phone number!!", Toast.LENGTH_SHORT).show();


            }


    }
}

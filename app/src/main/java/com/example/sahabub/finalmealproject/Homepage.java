package com.example.sahabub.finalmealproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Homepage extends AppCompatActivity {

    Button student, manager;
    TextView tv;

    private  FirebaseAuth mAuth;
    DatabaseReference ref;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        student = findViewById(R.id.StudentBtn);
        manager = findViewById(R.id.managerbtn);
        tv = findViewById(R.id.tvdesing);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference();

        ref.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("email").getValue().toString();
                if(name.equals("student")){
                    tv.setText(name);
                    Intent intent = new Intent(getApplicationContext(),StudentActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(name.equals("manager")){
                    tv.setText(name);
                    Intent intent = new Intent(getApplicationContext(),ManagerActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // student.setOnClickListener(this);
       // manager.setOnClickListener(this);



    }

  //  @Override
   // public void onClick(View v) {

     //   switch (v.getId()){
      //      case R.id.StudentBtn:{

                // Retriving Data from Server



         //   }
      //  }

}


 /*   FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mref = firebaseDatabase.getReference();
    mref.child("jetbro").addChildEventListener(new ChildEventListener() {
@Override
public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
        UserModel usermodel = chatModel.getUserModel();
        }

@Override
public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

@Override
public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

@Override
public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

@Override
public void onCancelled(DatabaseError databaseError) {

        }
        })

        */

package com.example.aegroapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aegroapp.Customer.CustomerActivity;
import com.example.aegroapp.sahakari.SahkariActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    EditText user,pass;
    Button logi;
    String username,password,oriPass;
    Spinner role;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        logi = findViewById(R.id.login);
        role = findViewById(R.id.role);

        ArrayList<String> r = new ArrayList<>();
        r.add("Farmer");
        r.add("Sahkari Mandali");
        r.add("Customer");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,r);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(arrayAdapter);

        SharedPreferences sp = getSharedPreferences("",MODE_PRIVATE);

        logi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user.getText().toString();
                password = pass.getText().toString();
//                Toast.makeText(login.this, username, Toast.LENGTH_LONG).show();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(login.this, "Please enter valid Username and Password", Toast.LENGTH_LONG).show();
                }
                if(role.getSelectedItem().toString().equals("Farmer")){
                    DocumentReference docRef = firebaseFirestore.collection("farmer").document(username);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        document.getData();
                                    oriPass = document.getString("password");
                                } else {
//                        Log.d(TAG, "No such document");
                                }
                            } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
//                    oriPass = "12345";
                    if(password.equals(oriPass)){
                        SharedPreferences.Editor farmerDetails = sp.edit();
                        farmerDetails.putString("username",username);
                        farmerDetails.commit();

                        Intent intent = new Intent(login.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
                if(role.getSelectedItem().toString().equals("Sahkari Mandali")){
                    DocumentReference docRef = firebaseFirestore.collection("shakari_mandali").document(username);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        document.getData();
                                    oriPass = document.getString("password");
                                } else {
//                        Log.d(TAG, "No such document");
                                }
                            } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                    if(password.equals(oriPass)){
                        SharedPreferences.Editor farmerDetails = sp.edit();
                        farmerDetails.putString("username",username);
                        farmerDetails.commit();

                        Intent intent = new Intent(login.this, SahkariActivity.class);
                        startActivity(intent);
                    }
                }
                if(role.getSelectedItem().toString().equals("Customer")){
                    DocumentReference docRef = firebaseFirestore.collection("customer").document(username);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        document.getData();
                                    oriPass = document.getString("password");
                                } else {
//                        Log.d(TAG, "No such document");
                                }
                            } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                    if(password.equals(oriPass)){
                        SharedPreferences.Editor farmerDetails = sp.edit();
                        farmerDetails.putString("username",username);
                        farmerDetails.commit();

                        Intent intent = new Intent(login.this, CustomerActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
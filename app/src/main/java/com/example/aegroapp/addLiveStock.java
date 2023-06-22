package com.example.aegroapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class addLiveStock extends AppCompatActivity {
    EditText name,type,capacity,details;
    Button add;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    SharedPreferences sp;
    String fname,fnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_live_stock);

        name = findViewById(R.id.liveStockname);
        type = findViewById(R.id.type);
        capacity = findViewById(R.id.milkcapacity);
        details = findViewById(R.id.detaillivestock);
        add = findViewById(R.id.AddLiveStockbutton);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currDate = sdf.format(new Date());

        sp = getSharedPreferences("", Context.MODE_PRIVATE);
        String id = sp.getString("username",null);

        DocumentReference docRef = firebaseFirestore.collection("farmer").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        document.getData();
                        fname = document.getString("name");
                        fnumber = document.getString("number");
                    } else {
//                        Log.d(TAG, "No such document");
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        name.setText(id);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.getText().toString().isEmpty() || capacity.getText().toString().isEmpty() || details.getText().toString().isEmpty() || id.equals(null) || currDate.isEmpty() || fname.isEmpty() || fnumber.isEmpty()) {

                    Toast.makeText(addLiveStock.this, "Enter all details", Toast.LENGTH_LONG).show();
                }
                else{

                    Map<String, Object> liveStock = new HashMap<>();
                    liveStock.put("type", type.getText().toString());
                    liveStock.put("capacity", capacity.getText().toString());
                    liveStock.put("details", details.getText().toString());
                    liveStock.put("farmerId", id);
                    liveStock.put("date", currDate);
                    liveStock.put("name", fname);
                    liveStock.put("number", fnumber);

                    firebaseFirestore.collection("livestock")
                            .add(liveStock)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Intent it = new Intent(addLiveStock.this, MainActivity.class);
                                    startActivity(it);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });

    }
}
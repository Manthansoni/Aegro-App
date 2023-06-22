package com.example.aegroapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aegroapp.R;
import com.example.aegroapp.sahakari.SahkariActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class addMilk extends AppCompatActivity {
    EditText name, number, fat, quantity, price, contai;
    Spinner village, milktype;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    String usernaam;
    Button addmilk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milk);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usernaam = extras.getString("username");
            Log.d(TAG, "onCreate:" + usernaam);
        }

        name = findViewById(R.id.farmername);
        number = findViewById(R.id.phoneno);
        fat = findViewById(R.id.fat);
        quantity = findViewById(R.id.quantity);
        price = findViewById(R.id.price);
        contai = findViewById(R.id.addmilkcontainer);
        milktype = findViewById(R.id.milktype);
        addmilk = findViewById(R.id.addMilk);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currDate = sdf.format(new Date());

        ArrayList<String> m = new ArrayList<>();
        m.add("cow");
        m.add("Buffalo");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        milktype.setAdapter(arrayAdapter);


        DocumentReference docRef = firebaseFirestore.collection("farmer").document(usernaam);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        document.getData();
                        Log.d(TAG, "onComplete: " + document.getString("name"));
                        name.setText(document.getString("name"));
                        number.setText(document.getString("number"));


                    } else {
//                        Log.d(TAG, "No such document");
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        addmilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fat.getText().toString().isEmpty() || quantity.getText().toString().isEmpty() || price.getText().toString().isEmpty() || contai.equals(null)) {
                    Toast.makeText(addMilk.this, "Enter all details", Toast.LENGTH_LONG).show();
                }
                else{
                    Map<String, Object> user = new HashMap<>();
                    user.put("amount", price.getText().toString());
                    user.put("container", contai.getText().toString());
                    user.put("date", currDate);
                    user.put("fat", fat.getText().toString());
                    user.put("litre", quantity.getText().toString());
                    user.put("priceOfLitre", "20");
                    user.put("quality","good");
                    user.put("recievedby", "chirag");
                    user.put("type", milktype.getSelectedItem().toString());



                    FirebaseFirestore.getInstance().collection("farmer").document(usernaam).collection("daily").document(currDate)
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(addMilk.this,"Added Successfully",Toast.LENGTH_LONG).show();
                                    Intent inte = new Intent(addMilk.this, SahkariActivity.class);
                                    startActivity(inte);
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



//        Add a new document with a generated ID
//        firebaseFirestore.collection("farmer").document("rameshbhai").collection("daily")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "user added");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "user not added");
//
//                    }
//                });




    }
}
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aegroapp.R;
import com.example.aegroapp.sahakari.SahkariActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addFarmer extends AppCompatActivity {
    EditText name,number,age,address;
    RadioGroup gender;
    Spinner village;
    Button addFarm;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmer);

        name = findViewById(R.id.name);
        number = findViewById(R.id.phoneno);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        gender = findViewById(R.id.genderBtn);
        village = findViewById(R.id.village);
        addFarm = findViewById(R.id.addFarmer);

        ArrayList<String> farmVillage = new ArrayList<>();
        farmVillage.add("Navagam");
        farmVillage.add("Kalol");
        farmVillage.add("Viramgam");
        farmVillage.add("Kobagam");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, farmVillage);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village.setAdapter(arrayAdapter);

        addFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty() || age.getText().toString().isEmpty() || address.equals(null)) {
                    Toast.makeText(addFarmer.this, "Enter all details", Toast.LENGTH_LONG).show();
                }
                else{

                    String g = ((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();
                    String use = name.getText().toString();
                    int u =  use.indexOf(' ');
                    String username = use.substring(0,u);
                    username = username.toLowerCase();

                    String nu = number.getText().toString();

                    Map<String, Object> user = new HashMap<>();
                    user.put("name", name.getText().toString());
                    user.put("number", nu);
                    user.put("age", age.getText().toString());
                    user.put("address", address.getText().toString());
                    user.put("gender", g);
                    user.put("village", village.getSelectedItem().toString());
                    user.put("username",username+"bhai");
                    user.put("password",username+"@"+nu.substring(0,2));



//                  Add a new document with a generated ID
                    firebaseFirestore.collection("farmer").document(username+"bhai")
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(addFarmer.this,"Farmer Added Successfully",Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(addFarmer.this, SahkariActivity.class);
                                    startActivity(in);
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
package com.example.aegroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class addVillage extends AppCompatActivity {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_village);

        String[] milk ={"Cow","Buffalo","Goat"};
        String[] v_name ={"Limbad","Telavi","Bhavda"};
    }
}
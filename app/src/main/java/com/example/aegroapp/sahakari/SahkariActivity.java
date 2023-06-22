package com.example.aegroapp.sahakari;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aegroapp.QrScanner;
import com.example.aegroapp.R;
import com.example.aegroapp.addFarmer;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class SahkariActivity extends AppCompatActivity{

    private static final String TAG = "SahkariActivity";
//    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private SahkariMilkDetailsAdapter adapter;
    private RecyclerView recyclerView;
    ImageView qrscan;
    Button addF;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahkari);

        qrscan = findViewById(R.id.qrCodeIcon);
        addF = findViewById(R.id.sahkariSearch);

        addF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SahkariActivity.this, addFarmer.class);
                startActivity(in);
            }
        });

        qrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SahkariActivity.this, QrScanner.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.sahkariRV);
        recyclerView.setNestedScrollingEnabled(false);
        setUpRecyclerView();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
            Log.d(TAG, "onStart: Listening");
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_sahkari, container, false);
//
//
//        return view;
//    }

    public void setUpRecyclerView() {
        Query query = FirebaseFirestore.getInstance()
                .collection("shakari_mandali");
//                .orderBy("timestamp");
//                .limit(50);

        FirestoreRecyclerOptions<SahkariMilkDetails> options = new FirestoreRecyclerOptions.Builder<SahkariMilkDetails>()
                .setQuery(query, SahkariMilkDetails.class)
                .build();

        adapter = new SahkariMilkDetailsAdapter(options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}


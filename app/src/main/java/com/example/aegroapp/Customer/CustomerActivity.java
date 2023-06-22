package com.example.aegroapp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aegroapp.QrScanner;
import com.example.aegroapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class CustomerActivity extends AppCompatActivity{

    private static final String TAG = "CustomerActivity";
    //    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CustomerMilkDetailsAdapter adapter;
    private RecyclerView recyclerView;
    ImageView qrscan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        qrscan = findViewById(R.id.qrCodeIcon);

        qrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerActivity.this, QrScanner.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.customerRV);
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

    public void setUpRecyclerView() {
        Query query = FirebaseFirestore.getInstance()
                .collection("customer");
//                .orderBy("timestamp");
//                .limit(50);

        FirestoreRecyclerOptions<CustomerMilkDetails> options = new FirestoreRecyclerOptions.Builder<CustomerMilkDetails>()
                .setQuery(query, CustomerMilkDetails.class)
                .build();

        adapter = new CustomerMilkDetailsAdapter(options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}


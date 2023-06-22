package com.example.aegroapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class LivestockFragment extends Fragment {

    private static final String TAG = "LivestockFragment";
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private LiveStockAdapter adapter;
    private RecyclerView recyclerView;
    ImageView qr;
    Button aliveStock;

    public static LivestockFragment newInstance() {
        return new LivestockFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livestock, container, false);
        recyclerView = view.findViewById(R.id.liveStockDetailsRV);
        setUpRecyclerView();

        qr = view.findViewById(R.id.qrCodeIcon);
        aliveStock = view.findViewById(R.id.AddLiveStock);
        aliveStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),addLiveStock.class);
                startActivity(i);
            }
        });

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrpopup qrc = new qrpopup();
                qrc.show(getActivity().getSupportFragmentManager(), "QrCode");
            }
        });

        return view;
    }

    public void setUpRecyclerView() {
        Query query = FirebaseFirestore.getInstance()
                .collection("livestock");
//                .orderBy("timestamp");
//                .limit(50);

        FirestoreRecyclerOptions<LiveStockDetails> options = new FirestoreRecyclerOptions.Builder<LiveStockDetails>()
                .setQuery(query, LiveStockDetails.class)
                .build();

        adapter = new LiveStockAdapter(options);
//        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}

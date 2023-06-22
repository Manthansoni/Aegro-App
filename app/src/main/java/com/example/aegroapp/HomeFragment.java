package com.example.aegroapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private com.example.aegroapp.MilkDetailsAdapter adapter;
    private RecyclerView recyclerView;
    ImageView qr;
    SharedPreferences sp;
    String id;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.milkDetailsRV);
        recyclerView.setNestedScrollingEnabled(false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        sp = getActivity().getSharedPreferences("", Context.MODE_PRIVATE);
        id = sp.getString("username",null);

        qr = view.findViewById(R.id.qrCodeIcon);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrpopup qrc = new qrpopup();
                qrc.show(getActivity().getSupportFragmentManager(), "QrCode");
            }
        });


//        getActivity().setsu(toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);


//        if (toolbar != null) {
//            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        }

        setUpRecyclerView();


        return view;
    }

    public void setUpRecyclerView() {
        Query query = FirebaseFirestore.getInstance()
                .collection("farmer").document(id.toString()).collection("daily");
//                .orderBy("timestamp");
//                .limit(50);

        FirestoreRecyclerOptions<com.example.aegroapp.MilkDetails> options = new FirestoreRecyclerOptions.Builder<com.example.aegroapp.MilkDetails>()
                .setQuery(query, com.example.aegroapp.MilkDetails.class)
                .build();

        adapter = new com.example.aegroapp.MilkDetailsAdapter(options);
//        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        //
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);

// Add a new document with a generated ID
//        firebaseFirestore.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });



    }
}


//        Log.d(TAG, "onCreateView: " + query.get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                }));




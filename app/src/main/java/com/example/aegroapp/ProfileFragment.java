package com.example.aegroapp;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.zxing.WriterException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class ProfileFragment extends Fragment {

    private Button logout;
    ImageView qr;
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    TextView na, nu, ad, vi;
    SharedPreferences sp;
    private static String TAG = "ProfileFragment";
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        na = view.findViewById(R.id.name);
        nu = view.findViewById(R.id.mobile);
        ad = view.findViewById(R.id.add);
        vi = view.findViewById(R.id.village);
        logout = view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences("",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(getContext(),login.class);
                startActivity(i);
            }
        });

        qr = view.findViewById(R.id.qrCodeIcon);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrpopup qrc = new qrpopup();
                qrc.show(getActivity().getSupportFragmentManager(), "QrCode");
            }
        });


//
//        DocumentReference documentReference = firebaseFirestore.collection("farmer").document(id).get()
//                .addOnSuccessListener(
//
//                )
//        documentReference.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                na.setText(value.getString("name"));
//                nu.setText(value.getString("number"));
//                ad.setText(value.getString("address"));
//                vi.setText(value.getString("village"));
//            }
//        });
        sp = getActivity().getSharedPreferences("", Context.MODE_PRIVATE);
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
                        na.setText(document.getString("name"));
                        nu.setText(document.getString("number"));
                        ad.setText(document.getString("address"));
                        vi.setText(document.getString("village"));
                    } else {
//                        Log.d(TAG, "No such document");
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        return view;


    }
    
}
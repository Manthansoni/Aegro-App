package com.example.aegroapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

public class LiveStockAdapter extends FirestoreRecyclerAdapter<LiveStockDetails, LiveStockViewHolder> {

    private static final String TAG = "LiveStockAdapter";


    public LiveStockAdapter(@NonNull FirestoreRecyclerOptions<LiveStockDetails> options) {
        super(options);
    }
//
//    public AdapterView.OnItemClickListener listener;
//
//    public interface onItemClickListener {
//        void onItemClick(DocumentSnapshot documentSnapshot, int position);
//    }
//
//    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
//        this.listener = listener;
//    }


    @NonNull
    @Override
    public LiveStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.livestock_card, parent, false);
        return new LiveStockViewHolder(view);
    }

    @Override
    public void onError(FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }

//    public static Drawable LoadImageFromWebOperations(String url) {
//        try {
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "src name");
//            return d;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @Override
    protected void onBindViewHolder(@NonNull LiveStockViewHolder holder, int position, @NonNull LiveStockDetails model) {
        holder.name.setText(model.getName());
        holder.number.setText(model.getNumber());
        holder.date.setText(model.getDate());
        holder.type.setText(model.getType());
        holder.capacity.setText(model.getCapacity());
        holder.details.setText(model.getDetails());
//        holder.url.setImageBitmap();
//        Picasso.get().load(model.getLiveStockUrl()).into(holder.url);
    }

}

class LiveStockViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "LiveStockViewHolder";
    TextView name, number, date, type, capacity, details;
    ImageView url;

    public LiveStockViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.liveStockCardAvatarName);
        number = itemView.findViewById(R.id.liveStockCardPhone);
        date = itemView.findViewById(R.id.liveStockCardDate);
        type = itemView.findViewById(R.id.liveStockCardType);
        capacity = itemView.findViewById(R.id.liveStockCardCapacity);
        details = itemView.findViewById(R.id.liveStockCardDetails);
        url = itemView.findViewById(R.id.liveStockCardImage);
    }

}
package com.example.aegroapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MilkDetailsAdapter extends FirestoreRecyclerAdapter<com.example.aegroapp.MilkDetails, MilkAdapterVH> {

    private static final String TAG = "MilkDetailsAdapter";


    public MilkDetailsAdapter(@NonNull FirestoreRecyclerOptions<com.example.aegroapp.MilkDetails> options) {
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
    public MilkAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_details_card, parent, false);
        return new MilkAdapterVH(view);
    }

    @Override
    public void onError(FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }
    boolean isExpanded = false;
    @Override
    protected void onBindViewHolder(@NonNull MilkAdapterVH holder, int position, @NonNull com.example.aegroapp.MilkDetails model) {
        holder.amount.setText(String.valueOf(model.getAmount()));
        holder.priceOfOneLitre.setText(String.valueOf(model.getPriceOfLitre()));
        holder.receivedBy.setText(model.getReceivedBy());
        holder.milkFat.setText(String.valueOf(model.getFat()));
        holder.milkType.setText(model.getType());
        holder.container.setText(String.valueOf(model.getContainer()));
        holder.date.setText(model.getDate());
        holder.quality.setText(model.getQuality());
        holder.litre.setText(model.getLitre());
        holder.expandableLayout.setVisibility(View.GONE);
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Before Item Clicked" + isExpanded);
               holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
               isExpanded = !isExpanded;
                Log.d(TAG, "After Item Clicked" + isExpanded);

            }
        });
    }
}

class MilkAdapterVH extends RecyclerView.ViewHolder {
    private static final String TAG = "MilkAdapterVH";
    TextView milkType, milkFat, quality, receivedBy, time, container, priceOfOneLitre, amount, date, litre;
    ConstraintLayout expandableLayout;

//    private MilkAdapterVH.ClickListener mClickListener;

//    public interface ClickListener{
//        public void onItemClick(View view, int position);
    //    }
//
//    public void setOnClickListener(MilkAdapterVH.ClickListener clickListener){
//        mClickListener = clickListener;
//    }

    public MilkAdapterVH(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        litre = itemView.findViewById(R.id.litre);
        milkType = itemView.findViewById(R.id.milkType);
        time = itemView.findViewById(R.id.time);
        milkFat = itemView.findViewById(R.id.milkFat);
        container = itemView.findViewById(R.id.container);
        quality = itemView.findViewById(R.id.quality);
        receivedBy = itemView.findViewById(R.id.receivedBy);
        priceOfOneLitre = itemView.findViewById(R.id.priceOfOneLitre);
        amount = itemView.findViewById(R.id.amount);
        expandableLayout = itemView.findViewById(R.id.expandableMilkCard);
//
//        itemView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.d(TAG, "Item clicked from method");
//                        int position = getAbsoluteAdapterPosition();
//                        Log.d(TAG, "onClick: isexpanded " + MilkDetailsAdapter.isExpanded);
//                        expandableLayout.setVisibility(MilkDetailsAdapter.isExpanded ? View.VISIBLE : View.GONE);
//
//                    }
//                }
//        );

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               mClickListener.onItemClick(v, getAbsoluteAdapterPosition());
//
//            }
//
//
//        });
    }

}
//
//
//public class MilkDetailsAdapter extends RecyclerView.Adapter<MilkDetails, MilkDetailsAdapter.MilkAdapterVH>(options) {
//    public static final String TAG = "MilkDetailsAdapter";
////    List<MilkDetails> milkDetailsList;
//    private FirestoreRecyclerAdapter<MilkDetails, MilkAdapterVH> adapter;
//
//
////    FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<MilkDetails, milk>(options) {
////        @Override
////        public void onBindViewHolder(ChatHolder holder, int position, Chat model) {
////
////        }
////
////        @Override
////        public ChatHolder onCreateViewHolder(ViewGroup group, int i) {
////            // Using a custom layout called R.layout.message for each item, we create a new instance of the viewholder
////            View view = LayoutInflater.from(group.getContext())
////                    .inflate(R.layout.message, group, false);
////
////            return new ChatHolder(view);
////        }
////    };
//
//    public MilkDetailsAdapter(List<MilkDetails> milkDetails) {
//        this.milkDetailsList = milkDetails;
//    }
//
//    @NonNull
//    @Override
//    public MilkAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_details_card, parent, false);
//        return new MilkAdapterVH(view);
//    }
//
////    @Override
////    public void onBindViewHolder(@NonNull MilkAdapterVH holder, int position) {
////        MilkDetails milkDetails = milkDetailsList.get(position);
////        holder.amount.setText((int) milkDetails.getAmount());
////        holder.priceOfOneLitre.setText((int) milkDetails.getPriceOfLitre());
////        holder.receivedBy.setText(milkDetails.getReceivedBy());
////        holder.milkFat.setText(milkDetails.getFat());
////        holder.milkType.setText(milkDetails.getType());
////        holder.time.setText(milkDetails.getTime());
////        holder.container.setText(milkDetails.getContainer());
////        holder.date.setText(milkDetails.getDate());
////        holder.quality.setText(milkDetails.getQuality());
////        holder.litre.setText(milkDetails.getLitre());
////        boolean isExpanded = milkDetailsList.get(position).isExpanded();
////        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
////    }
////
////    @Override
////    public int getItemCount() {
////        return milkDetailsList.size();
////    }
//
//    class MilkAdapterVH extends RecyclerView.ViewHolder {
//
//        private static final String TAG = "MilkAdapterVH";
//        TextView milkType, milkFat, quality, receivedBy, time, container, priceOfOneLitre, amount, date, litre;
//        ConstraintLayout expandableLayout;
//
//        public MilkAdapterVH(@NonNull View itemView) {
//            super(itemView);
//            date = itemView.findViewById(R.id.date);
//            litre = itemView.findViewById(R.id.litre);
//            milkType = itemView.findViewById(R.id.milkType);
//            time = itemView.findViewById(R.id.time);
//            milkFat = itemView.findViewById(R.id.milkFat);
//            container = itemView.findViewById(R.id.container);
//            quality = itemView.findViewById(R.id.quality);
//            receivedBy = itemView.findViewById(R.id.receivedBy);
//            priceOfOneLitre = itemView.findViewById(R.id.priceOfOneLitre);
//            amount = itemView.findViewById(R.id.amount);
//            expandableLayout = itemView.findViewById(R.id.expandableMilkCard);
//        }
//    }
//}

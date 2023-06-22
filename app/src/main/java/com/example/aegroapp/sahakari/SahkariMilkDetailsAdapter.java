package com.example.aegroapp.sahakari;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aegroapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SahkariMilkDetailsAdapter extends FirestoreRecyclerAdapter<SahkariMilkDetails, SahkariAdapterVH> {

    private static final String TAG = "SahkariMilkDetailsAdapter";

    public SahkariMilkDetailsAdapter(@NonNull FirestoreRecyclerOptions<SahkariMilkDetails> options) {
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
    public SahkariAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_details_card_sahkari, parent, false);
        return new SahkariAdapterVH(view);
    }

    @Override
    public void onError(FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }

    boolean isExpanded = false;

    @Override
    protected void onBindViewHolder(@NonNull SahkariAdapterVH holder, int position, @NonNull SahkariMilkDetails model) {
        holder.milkFat.setText(String.valueOf(model.getFat()));
        holder.milkType.setText(model.getType());
        holder.time.setText(model.getTime());
        holder.container.setText(String.valueOf(model.getContainer()));
        Log.d(TAG, "onBindViewHolder: " + model.getLitre());

        holder.date.setText(model.getDate());
        holder.litre.setText(model.getLitre());
        holder.expandableLayout.setVisibility(View.VISIBLE);
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

class SahkariAdapterVH extends RecyclerView.ViewHolder {
    private static final String TAG = "SahkariAdapterVH";
    TextView milkType, milkFat, time, container, date, litre;
    ConstraintLayout expandableLayout;

//    private SahkariAdapterVH.ClickListener mClickListener;

//    public interface ClickListener{
//        public void onItemClick(View view, int position);
    //    }
//
//    public void setOnClickListener(SahkariAdapterVH.ClickListener clickListener){
//        mClickListener = clickListener;
//    }

    public SahkariAdapterVH(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.sahkariCardDate);
        litre = itemView.findViewById(R.id.sahkariCardLitre);
        milkType = itemView.findViewById(R.id.sahkariMilkType);
        time = itemView.findViewById(R.id.sahkariTime);
        milkFat = itemView.findViewById(R.id.sahkariMilkFat);
        container = itemView.findViewById(R.id.sahkariContainer);
        expandableLayout = itemView.findViewById(R.id.expandableSahkariCard);
//
//        itemView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.d(TAG, "Item clicked from method");
//                        int position = getAbsoluteAdapterPosition();
//                        Log.d(TAG, "onClick: isexpanded " + SahkariMilkDetailsAdapter.isExpanded);
//                        expandableLayout.setVisibility(SahkariMilkDetailsAdapter.isExpanded ? View.VISIBLE : View.GONE);
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
//public class SahkariMilkDetailsAdapter extends RecyclerView.Adapter<SahkariMilkDetails, SahkariMilkDetailsAdapter.SahkariAdapterVH>(options) {
//    public static final String TAG = "SahkariMilkDetailsAdapter";
////    List<SahkariMilkDetails> milkDetailsList;
//    private FirestoreRecyclerAdapter<SahkariMilkDetails, SahkariAdapterVH> adapter;
//
//
////    FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<SahkariMilkDetails, milk>(options) {
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
//    public SahkariMilkDetailsAdapter(List<SahkariMilkDetails> SahkariMilkDetails) {
//        this.milkDetailsList = SahkariMilkDetails;
//    }
//
//    @NonNull
//    @Override
//    public SahkariAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_details_card, parent, false);
//        return new SahkariAdapterVH(view);
//    }
//
////    @Override
////    public void onBindViewHolder(@NonNull SahkariAdapterVH holder, int position) {
////        SahkariMilkDetails SahkariMilkDetails = milkDetailsList.get(position);
////        holder.amount.setText((int) SahkariMilkDetails.getAmount());
////        holder.priceOfOneLitre.setText((int) SahkariMilkDetails.getPriceOfLitre());
////        holder.receivedBy.setText(SahkariMilkDetails.getReceivedBy());
////        holder.milkFat.setText(SahkariMilkDetails.getFat());
////        holder.milkType.setText(SahkariMilkDetails.getType());
////        holder.time.setText(SahkariMilkDetails.getTime());
////        holder.container.setText(SahkariMilkDetails.getContainer());
////        holder.date.setText(SahkariMilkDetails.getDate());
////        holder.quality.setText(SahkariMilkDetails.getQuality());
////        holder.litre.setText(SahkariMilkDetails.getLitre());
////        boolean isExpanded = milkDetailsList.get(position).isExpanded();
////        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
////    }
////
////    @Override
////    public int getItemCount() {
////        return milkDetailsList.size();
////    }
//
//    class SahkariAdapterVH extends RecyclerView.ViewHolder {
//
//        private static final String TAG = "SahkariAdapterVH";
//        TextView milkType, milkFat, quality, receivedBy, time, container, priceOfOneLitre, amount, date, litre;
//        ConstraintLayout expandableLayout;
//
//        public SahkariAdapterVH(@NonNull View itemView) {
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

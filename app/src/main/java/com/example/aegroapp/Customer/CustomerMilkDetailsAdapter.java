package com.example.aegroapp.Customer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aegroapp.R;
import com.example.aegroapp.Customer.CustomerMilkDetails;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class CustomerMilkDetailsAdapter extends FirestoreRecyclerAdapter<CustomerMilkDetails, CustomerAdapterVH> {

    private static final String TAG = "CustomerMilkDetailsAdapter";

    public CustomerMilkDetailsAdapter(@NonNull FirestoreRecyclerOptions<CustomerMilkDetails> options) {
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
    public CustomerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_details_card_customer, parent, false);
        return new CustomerAdapterVH(view);
    }

    @Override
    public void onError(FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }

    boolean isExpanded = false;

    @Override
    protected void onBindViewHolder(@NonNull CustomerAdapterVH holder, int position, @NonNull CustomerMilkDetails model) {
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

class CustomerAdapterVH extends RecyclerView.ViewHolder {
    private static final String TAG = "CustomerAdapterVH";
    TextView milkType, milkFat, time, container, date, litre;
    ConstraintLayout expandableLayout;

//    private CustomerAdapterVH.ClickListener mClickListener;

//    public interface ClickListener{
//        public void onItemClick(View view, int position);
    //    }
//
//    public void setOnClickListener(CustomerAdapterVH.ClickListener clickListener){
//        mClickListener = clickListener;
//    }

    public CustomerAdapterVH(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.customerCardDate);
        litre = itemView.findViewById(R.id.customerCardLitre);
        milkType = itemView.findViewById(R.id.customerMilkType);
        time = itemView.findViewById(R.id.customerTime);
        milkFat = itemView.findViewById(R.id.customerMilkFat);
        container = itemView.findViewById(R.id.customerContainer);
        expandableLayout = itemView.findViewById(R.id.expandableCustomerCard);
//
//        itemView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.d(TAG, "Item clicked from method");
//                        int position = getAbsoluteAdapterPosition();
//                        Log.d(TAG, "onClick: isexpanded " + CustomerMilkDetailsAdapter.isExpanded);
//                        expandableLayout.setVisibility(CustomerMilkDetailsAdapter.isExpanded ? View.VISIBLE : View.GONE);
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
//public class CustomerMilkDetailsAdapter extends RecyclerView.Adapter<CustomerMilkDetails, CustomerMilkDetailsAdapter.CustomerAdapterVH>(options) {
//    public static final String TAG = "CustomerMilkDetailsAdapter";
////    List<CustomerMilkDetails> milkDetailsList;
//    private FirestoreRecyclerAdapter<CustomerMilkDetails, CustomerAdapterVH> adapter;
//
//
////    FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<CustomerMilkDetails, milk>(options) {
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
//    public CustomerMilkDetailsAdapter(List<CustomerMilkDetails> CustomerMilkDetails) {
//        this.milkDetailsList = CustomerMilkDetails;
//    }
//
//    @NonNull
//    @Override
//    public CustomerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_details_card, parent, false);
//        return new CustomerAdapterVH(view);
//    }
//
////    @Override
////    public void onBindViewHolder(@NonNull CustomerAdapterVH holder, int position) {
////        CustomerMilkDetails CustomerMilkDetails = milkDetailsList.get(position);
////        holder.amount.setText((int) CustomerMilkDetails.getAmount());
////        holder.priceOfOneLitre.setText((int) CustomerMilkDetails.getPriceOfLitre());
////        holder.receivedBy.setText(CustomerMilkDetails.getReceivedBy());
////        holder.milkFat.setText(CustomerMilkDetails.getFat());
////        holder.milkType.setText(CustomerMilkDetails.getType());
////        holder.time.setText(CustomerMilkDetails.getTime());
////        holder.container.setText(CustomerMilkDetails.getContainer());
////        holder.date.setText(CustomerMilkDetails.getDate());
////        holder.quality.setText(CustomerMilkDetails.getQuality());
////        holder.litre.setText(CustomerMilkDetails.getLitre());
////        boolean isExpanded = milkDetailsList.get(position).isExpanded();
////        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
////    }
////
////    @Override
////    public int getItemCount() {
////        return milkDetailsList.size();
////    }
//
//    class CustomerAdapterVH extends RecyclerView.ViewHolder {
//
//        private static final String TAG = "CustomerAdapterVH";
//        TextView milkType, milkFat, quality, receivedBy, time, container, priceOfOneLitre, amount, date, litre;
//        ConstraintLayout expandableLayout;
//
//        public CustomerAdapterVH(@NonNull View itemView) {
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

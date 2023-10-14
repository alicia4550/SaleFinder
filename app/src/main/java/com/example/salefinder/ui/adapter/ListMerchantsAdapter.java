package com.example.salefinder.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.R;
import com.example.salefinder.ThirdFragment;
import com.example.salefinder.ui.model.Merchant;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ListMerchantsAdapter extends RecyclerView.Adapter<ListMerchantsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        public TextView nameTextView;
        public Button deleteButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.merchant_name);
            deleteButton = (Button) itemView.findViewById(R.id.delete_merchant_btn);
        }
    }

    // Store a member variable for the contacts
    private List<Merchant> merchants;
    private ThirdFragment fragment;

    // Pass in the contact array into the constructor
    public ListMerchantsAdapter(List<Merchant> merchants, ThirdFragment fragment) {
        this.merchants = merchants;
        this.fragment = fragment;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ListMerchantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_list_merchants, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListMerchantsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Merchant merchant = merchants.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(merchant.getName());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                fragment.addMerchantToSpinner(merchants.get(position).getName());
                merchants.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return merchants.size();
    }
}

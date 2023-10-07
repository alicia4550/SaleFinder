package com.example.salefinder.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.R;
import com.example.salefinder.service.ImageLoadTaskService;
import com.example.salefinder.ui.model.Merchant;

import java.util.List;

public class MerchantsAdapter extends RecyclerView.Adapter<MerchantsAdapter.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView merchantTextView;

        private ImageView merchantLogoView;

        private RecyclerView salesItemRecyclerView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            merchantTextView = (TextView) itemView.findViewById(R.id.merchant_name);
            merchantLogoView = (ImageView) itemView.findViewById(R.id.merchant_logo);
            salesItemRecyclerView = itemView.findViewById(R.id.recycler_sales_items);
        }
    }

    // Store a member variable for the contacts
    private List<Merchant> merchants;

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    // Pass in the contact array into the constructor
    public MerchantsAdapter(List<Merchant> merchants) {
        this.merchants = merchants;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MerchantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_merchants, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MerchantsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Merchant merchant = merchants.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.merchantTextView;
        textView.setText(merchant.getName());

        ImageView imageView = holder.merchantLogoView;
        merchant.getLogoUrl().replace("http:", "https:");
        new ImageLoadTaskService(merchant.getLogoUrl(), imageView).execute();

        // Here we have assigned the layout as LinearLayout with vertical orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.salesItemRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL,
                false);

        // Since this is a nested layout, so to define how many child items should be prefetched when the
        // child RecyclerView is nested inside the parent RecyclerView, we use the following method
        layoutManager.setInitialPrefetchItemCount(merchant.getSalesItemList().size());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        SalesItemsAdapter salesItemsAdapter = new SalesItemsAdapter(merchant.getSalesItemList());
        holder.salesItemRecyclerView.setLayoutManager(layoutManager);
        holder.salesItemRecyclerView.setAdapter(salesItemsAdapter);
        holder.salesItemRecyclerView.setRecycledViewPool(viewPool);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return merchants.size();
    }
}

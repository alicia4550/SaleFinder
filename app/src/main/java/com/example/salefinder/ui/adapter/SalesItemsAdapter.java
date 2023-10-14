package com.example.salefinder.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.R;
import com.example.salefinder.service.ImageLoadTaskService;
import com.example.salefinder.ui.model.SalesItem;

import java.util.List;

public class SalesItemsAdapter extends RecyclerView.Adapter<SalesItemsAdapter.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView priceTextView;
        public ImageView cutoutImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.sales_item_name);
            priceTextView = (TextView) itemView.findViewById(R.id.sales_item_price);
            cutoutImageView = (ImageView) itemView.findViewById(R.id.cutout_image);
        }
    }

    // Store a member variable for the contacts
    private List<SalesItem> salesItems;

    // Pass in the contact array into the constructor
    public SalesItemsAdapter(List<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public SalesItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_sales_items, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SalesItemsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        SalesItem salesItem = salesItems.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(salesItem.getName());

        TextView priceView = holder.priceTextView;
        if (salesItem.getPrice() != -1)
            priceView.setText("$" + String.format("%.02f", salesItem.getPrice()));

        ImageView imageView = holder.cutoutImageView;
        salesItem.getCutoutUrl().replace("http:", "https:");
        new ImageLoadTaskService(salesItem.getCutoutUrl(), imageView).execute();
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return salesItems.size();
    }
}

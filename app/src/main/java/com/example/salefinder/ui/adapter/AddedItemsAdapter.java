package com.example.salefinder.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.R;
import com.example.salefinder.ui.model.AddedItem;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class AddedItemsAdapter extends RecyclerView.Adapter<AddedItemsAdapter.ViewHolder> {

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

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
            deleteButton = (Button) itemView.findViewById(R.id.delete_item_btn);
        }
    }

    // Store a member variable for the contacts
    private List<AddedItem> addedItems;

    // Pass in the contact array into the constructor
    public AddedItemsAdapter(List<AddedItem> addedItems) {
        this.addedItems = addedItems;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public AddedItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_added_items, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(AddedItemsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        AddedItem addedItem = addedItems.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(addedItem.getName());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                addedItems.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return addedItems.size();
    }
}

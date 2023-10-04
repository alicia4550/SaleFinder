package com.example.salefinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.databinding.FragmentFirstBinding;
import com.example.salefinder.ui.adapter.AddedItemsAdapter;
import com.example.salefinder.ui.model.AddedItem;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private AddedItemsAdapter addedItemsAdapter;
    private List<AddedItem> addedItems;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerAddedItems = (RecyclerView) getView().findViewById(R.id.recycler_added_items);
        addedItems = new ArrayList<>();
        addedItemsAdapter = new AddedItemsAdapter(addedItems);
        // Attach the adapter to the recyclerview to populate items
        recyclerAddedItems.setAdapter(addedItemsAdapter);
        // Set layout manager to position the items
        recyclerAddedItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_item = (EditText) getView().findViewById(R.id.edit_text_item);
                String itemText = editText_item.getText().toString();
                System.out.println(itemText);
                editText_item.setText("");

                addedItems.add(0, new AddedItem(itemText));
                addedItemsAdapter.notifyItemInserted(0);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
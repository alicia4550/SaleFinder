package com.example.salefinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.databinding.FragmentFirstBinding;
import com.example.salefinder.ui.adapter.ListItemsAdapter;
import com.example.salefinder.ui.model.ListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ListItemsAdapter listItemsAdapter;
    private ArrayList<ListItem> listItems;

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
        listItems = new ArrayList<>();
        listItemsAdapter = new ListItemsAdapter(listItems);
        // Attach the adapter to the recyclerview to populate items
        recyclerAddedItems.setAdapter(listItemsAdapter);
        // Set layout manager to position the items
        recyclerAddedItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_item = (EditText) getView().findViewById(R.id.edit_text_item);
                String itemText = editText_item.getText().toString();
                System.out.println(itemText);
                editText_item.setText("");

                listItems.add(0, new ListItem(itemText));
                listItemsAdapter.notifyItemInserted(0);
            }
        });

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < listItems.size(); i++) {
                    System.out.println(listItems.get(i));
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("listItems", (Serializable) listItems);
                getParentFragmentManager().setFragmentResult("requestKey", bundle);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
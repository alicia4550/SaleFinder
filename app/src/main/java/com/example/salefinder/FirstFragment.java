package com.example.salefinder;

import static java.lang.Float.parseFloat;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.databinding.FragmentFirstBinding;
import com.example.salefinder.entity.Item;
import com.example.salefinder.repository.ItemRepository;
import com.example.salefinder.ui.adapter.ListItemsAdapter;
import com.example.salefinder.ui.model.ListItem;
import com.example.salefinder.ui.model.Merchant;
import com.example.salefinder.ui.model.SalesItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ListItemsAdapter listItemsAdapter;
    private List<ListItem> listItems;

    private List<Merchant> merchantList;

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
        listItems = ((MainActivity)getActivity()).listItems == null ? new ArrayList<>() : ((MainActivity)getActivity()).listItems;
        listItemsAdapter = new ListItemsAdapter(listItems);
        // Attach the adapter to the recyclerview to populate items
        recyclerAddedItems.setAdapter(listItemsAdapter);
        // Set layout manager to position the items
        recyclerAddedItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem();
            }
        });

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).listItems = listItems;

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).setMerchantSalesItems();
                    }
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });

        EditText editText_item = (EditText) getView().findViewById(R.id.edit_text_item);
        editText_item.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    addListItem();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addListItem() {
        EditText editText_item = (EditText) getView().findViewById(R.id.edit_text_item);
        String itemText = editText_item.getText().toString();
        System.out.println(itemText);
        editText_item.setText("");

        listItems.add(0, new ListItem(itemText));
        listItemsAdapter.notifyItemInserted(0);
        ((MainActivity)getActivity()).listItems = listItems;
    }

}
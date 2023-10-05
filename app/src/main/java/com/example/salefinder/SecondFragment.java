package com.example.salefinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.databinding.FragmentSecondBinding;
import com.example.salefinder.ui.adapter.ListItemsAdapter;
import com.example.salefinder.ui.adapter.MerchantsAdapter;
import com.example.salefinder.ui.model.ListItem;
import com.example.salefinder.ui.model.Merchant;
import com.example.salefinder.ui.model.SalesItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private ArrayList<ListItem> listItems;

    private MerchantsAdapter merchantsAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                listItems = (ArrayList<ListItem>) bundle.getSerializable("listItems");
            }
        });

        SalesItem salesItem1 = new SalesItem("name1", 1);
        SalesItem salesItem2 = new SalesItem("name2", 1);
        List<SalesItem> salesItemList1 = new ArrayList<>();
        salesItemList1.add(salesItem1);
        salesItemList1.add(salesItem2);
        Merchant merchant1 = new Merchant("merchant1", salesItemList1);
        SalesItem salesItem3 = new SalesItem("name3", 3);
        SalesItem salesItem4 = new SalesItem("name4", 4);
        List<SalesItem> salesItemList2 = new ArrayList<>();
        salesItemList2.add(salesItem3);
        salesItemList2.add(salesItem4);
        Merchant merchant2 = new Merchant("merchant2", salesItemList2);
        List<Merchant> merchantList = new ArrayList<>();
        merchantList.add(merchant1);
        merchantList.add(merchant2);

        RecyclerView recyclerMerchants = (RecyclerView) getView().findViewById(R.id.recycler_merchants);
        merchantsAdapter = new MerchantsAdapter(merchantList);
        // Attach the adapter to the recyclerview to populate items
        recyclerMerchants.setAdapter(merchantsAdapter);
        // Set layout manager to position the items
        recyclerMerchants.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
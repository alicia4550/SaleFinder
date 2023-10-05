package com.example.salefinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salefinder.databinding.FragmentSecondBinding;
import com.example.salefinder.ui.adapter.MerchantsAdapter;
import com.example.salefinder.ui.model.Merchant;

import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private MerchantsAdapter merchantsAdapter;

    private List<Merchant> merchantList;


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

        merchantList = ((MainActivity)getActivity()).merchantList;
//
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
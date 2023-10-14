package com.example.salefinder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.salefinder.databinding.FragmentThirdBinding;
import com.example.salefinder.ui.adapter.ListMerchantsAdapter;
import com.example.salefinder.ui.model.Merchant;

import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// */
public class ThirdFragment extends Fragment {

    private @NonNull FragmentThirdBinding binding;
    private ListMerchantsAdapter listMerchantsAdapter;
    private ArrayAdapter<String> spinnerAdapter;
    private List<String> allMerchants;
    private List<Merchant> selectedMerchantsList;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedMerchantsList = ((MainActivity) getActivity()).merchantList;
        allMerchants = ((MainActivity) getActivity()).allMerchants;

        for (Merchant selectedMerchant : selectedMerchantsList) {
            allMerchants.removeIf(value -> value.equals(selectedMerchant.getName()));
        }

        Spinner merchantSpinner = (Spinner) getView().findViewById(R.id.select_merchant_spinner);
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, allMerchants);
        merchantSpinner.setAdapter(spinnerAdapter);

        RecyclerView recyclerSelectedMerchants = (RecyclerView) getView().findViewById(R.id.recycler_selected_merchants);
        listMerchantsAdapter = new ListMerchantsAdapter(selectedMerchantsList, this);
        // Attach the adapter to the recyclerview to populate items
        recyclerSelectedMerchants.setAdapter(listMerchantsAdapter);
        // Set layout manager to position the items
        recyclerSelectedMerchants.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.addMerchantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = merchantSpinner.getSelectedItem().toString();
                if (selected.equals("---")) return;

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        List<Integer> flyerIds = ((MainActivity) getActivity()).flyerRepository.findFlyerIdByMerchant(selected);
                        String logoUrl = ((MainActivity) getActivity()).flyerRepository.findLogoByMerchant(selected).get(0);
                        selectedMerchantsList.add(0, new Merchant(selected, logoUrl, flyerIds));
                    }
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                listMerchantsAdapter.notifyItemInserted(0);

                allMerchants.remove(selected);
                merchantSpinner.setSelection(0);
            }
        });

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).merchantList = selectedMerchantsList;

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

                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addMerchantToSpinner(String name) {
        int insertionIndex = allMerchants.size() / 2;
        int start = 1;
        int end = allMerchants.size() - 1;

        if (name.compareTo(allMerchants.get(start)) < 0) {
            allMerchants.add(1, name);
        } else if (name.compareTo(allMerchants.get(end)) > 0) {
            allMerchants.add(name);
        } else {
            while (start < end) {
                if (name.compareTo(allMerchants.get(insertionIndex - 1)) > 0 && name.compareTo(allMerchants.get(insertionIndex)) < 0) {
                    allMerchants.add(insertionIndex, name);
                    break;
                } else if (name.compareTo(allMerchants.get(insertionIndex - 1)) < 0) {
                    end = insertionIndex;
                } else if (name.compareTo(allMerchants.get(insertionIndex)) > 0) {
                    start = insertionIndex;
                } else {
                    break;
                }
                insertionIndex = (start + end) / 2;
            }
        }

        Spinner merchantSpinner = (Spinner) getView().findViewById(R.id.select_merchant_spinner);
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, allMerchants);
        merchantSpinner.setAdapter(spinnerAdapter);
    }
}
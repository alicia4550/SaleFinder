package com.example.salefinder;

import static java.lang.Float.parseFloat;

import android.os.Bundle;

import com.example.salefinder.component.DaggerAppComponent;
import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;
import com.example.salefinder.module.AppModule;
import com.example.salefinder.module.DatabaseModule;
import com.example.salefinder.repository.FlyerRepository;
import com.example.salefinder.repository.ItemRepository;
import com.example.salefinder.service.WebScraperService;
import com.example.salefinder.ui.model.ListItem;
import com.example.salefinder.ui.model.Merchant;

import androidx.appcompat.app.AppCompatActivity;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.salefinder.databinding.ActivityMainBinding;
import com.example.salefinder.ui.model.SalesItem;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Inject
    public FlyerRepository flyerRepository;

    @Inject
    public ItemRepository itemRepository;

    public List<Merchant> merchantList;

    public List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .databaseModule(new DatabaseModule(getApplication()))
                .build()
                .inject(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listItems = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Getting flyers from Flipp...");
                List<Flyer> flyers = WebScraperService.getAllFlyers();
                for (Flyer flyer : flyers) {
                    flyerRepository.insert(flyer);
                }
                System.out.println("Saved flyers from Flipp");

                System.out.println("Getting items from flyers...");
                List<String> merchantNames = Arrays.asList("FreshCo", "Food Basics", "No Frills", "Walmart");
                merchantList = new ArrayList<>();
                for (String merchantName : merchantNames) {
                    List<Integer> flyerIds = flyerRepository.findFlyerIdByMerchant(merchantName);
                    merchantList.add(new Merchant(merchantName, flyerIds));

                    for (int flyerId : flyerIds) {
                        List<Item> itemList = WebScraperService.getAllItemsByFlyer(flyerId);
                        for (Item item : itemList) {
                            itemRepository.insert(item);
                        }
                    }
                }
                System.out.println("Saved items from flyers");
            }
        }).start();

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setMerchantSalesItems() {
        for (Merchant merchant : merchantList) {
            for (int flyerId : merchant.getFlyerIdList()) {
                for (ListItem listItem : listItems) {
                    List<Item> itemList = itemRepository.findByFlyerIdAndName(flyerId, listItem.getName());
                    List<SalesItem> salesItemList = itemList.stream()
                            .map(item -> new SalesItem(item.name, parseFloat(item.price)))
                            .collect(Collectors.toList());

                    merchant.addSalesItems(salesItemList);
                }
            }
        }
    }
}
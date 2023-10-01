package com.example.salefinder.webscraper;

import com.example.salefinder.model.Flyer;
import com.example.salefinder.model.Item;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FlippScraper {
    public static Flyer[] flyersArray;

    public static void GetAllFlyers() {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse("https://cdn-gateflipp.flippback.com/bf/flipp/data").newBuilder();
        urlBuilder.addQueryParameter("locale", "en-ca")
                .addQueryParameter("postal_code", "M6H4B9")
                .addQueryParameter("sid", "5606199655403465");

        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("Success");

            String flippString = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode flippObj = mapper.readTree(flippString);

            flyersArray = mapper.readValue(flippObj.findValue("flyers").toString(), Flyer[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> GetFlyerIds(ArrayList<String> merchants) {
        ArrayList<Integer> flyerIds = new ArrayList<>();
        for (Flyer flyer : flyersArray) {
            if (merchants.contains(flyer.getMerchant())) {
                flyerIds.add(flyer.getId());
                System.out.println(flyer.getMerchant() + ": " + flyer.getId());
            }
        }
        return flyerIds;
    }

    public static void GetProducts(int flyerId) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse("https://cdn-gateflipp.flippback.com/bf/flipp/flyers/"+flyerId).newBuilder();
        urlBuilder.addQueryParameter("locale", "en-ca")
                .addQueryParameter("sid", "5606199655403465");

        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("Success");

            String flyerString = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode flyerObj = mapper.readTree(flyerString);

            System.out.println(flyerObj);

            Item[] itemsArray = mapper.readValue(flyerObj.findValue("items").toString(), Item[].class);

            for (Item item : itemsArray) {
                System.out.println(item.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<String> merchants = new ArrayList<String>(
                Arrays.asList("FreshCo",
                        "Food Basics",
                        "No Frills",
                        "Walmart"));

        GetAllFlyers();
        ArrayList<Integer> flyerIds = GetFlyerIds(merchants);
        for (Integer i : flyerIds) {
            System.out.println((i));
        }
//        GetProducts(5970294);
    }
}

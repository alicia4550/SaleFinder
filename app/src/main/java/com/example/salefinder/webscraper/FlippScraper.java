package com.example.salefinder.webscraper;

import com.example.salefinder.entity.Flyer;
import com.example.salefinder.entity.Item;
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

    public static Flyer[] GetAllFlyers() {
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
        return flyersArray;
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

            Item[] itemsArray = mapper.readValue(flyerObj.findValue("items").toString(), Item[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ArrayList<String> merchants = new ArrayList<String>(
//                Arrays.asList("FreshCo",
//                        "Food Basics",
//                        "No Frills",
//                        "Walmart"));
//
//        Flyer[] flyers = GetAllFlyers();
//        System.out.println(flyers.length);
//        ArrayList<Integer> flyerIds = GetFlyerIds(merchants);
//        for (Integer i : flyerIds) {
//            System.out.println((i));
//        }
//        GetProducts(5970294);
    }
}

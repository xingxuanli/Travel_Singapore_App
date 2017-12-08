package com.example.user.andoridproject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by study on 3/12/17.
 */

public class TSPMethod {

    public static ArrayList<ArrayList<String>> myRoutes(String[] myPlaces) {
        HashMap<String, double[]> map =PlaceData.createMap();
        HashMap<String, Character> placeCode =new HashMap<>();
        placeCode.put("Marina Bay Sands", 'a');
        placeCode.put("Singapore Flyer", 'b');
        placeCode.put("Vivo City", 'c');
        placeCode.put("Resorts World Sentosa", 'd');
        placeCode.put("Buddha Tooth Relic Temple", 'e');
        placeCode.put("Zoo", 'f');

        //create an Arraylist for all the routes
        ArrayList<String> routes =new ArrayList<>();

        //generate a string of all the places need visiting
        String places ="";
        for(String s :myPlaces) {
            places +=String.valueOf(placeCode.get(s));
        }

        int pointer = 0;
        int placeNumber = places.length();
        String minSubroute = "";

        for (int j = 0; j < placeNumber; j++) {
            int minTime = Integer.MAX_VALUE;
            String temp;
            for (int i = 0; i < places.length(); i++) {
                if (j == 0) {
                    temp = "a" + String.valueOf(places.charAt(i));
                } else {
                    temp = String.valueOf(routes.get(j - 1).charAt(1)) + String.valueOf(places.charAt(i));
                }
                int testTime = (int) map.get(temp)[5];
                if (testTime < minTime) {
                    minTime = testTime;
                    minSubroute = temp;
                    pointer = i;
                }
            }
            routes.add(minSubroute);
            places = places.substring(0, pointer) + places.substring(pointer + 1);
        }
        routes.add(String.valueOf(routes.get(placeNumber - 1).charAt(1)) + "a");
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        output.add(routes);

        return output;

    }
}

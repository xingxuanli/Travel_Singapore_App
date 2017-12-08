package com.example.user.andoridproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by study on 3/12/17.
 */

public class MainAlgo {

    public static ArrayList<String> choosePlaces(String[] myPlaces) {
        HashMap<String, Character> placeCode =new HashMap<>();
        placeCode.put("Marina Bay Sands", 'a');
        placeCode.put("Singapore Flyer", 'b');
        placeCode.put("Vivo City", 'c');
        placeCode.put("Resorts World Sentosa", 'd');
        placeCode.put("Buddha Tooth Relic Temple", 'e');
        placeCode.put("Zoo", 'f');

        String places ="";
        for(String s :myPlaces) {
            places +=String.valueOf(placeCode.get(s));
        }

        ArrayList<String> opt =new ArrayList<>();
        permutation("a", places, opt, "a");

        return opt;
    }

    private static void permutation(String prefix, String str,
                                    ArrayList<String> opt, String suffix) {
        int n=str.length();
        if(n ==0) {
            opt.add(prefix +suffix);
        } else {
            for (int i =0; i <n; i++) {
                permutation(prefix +str.charAt(i), str.substring(0, i) +str.substring(i +1, n),
                        opt, suffix);
            }
        }
    }

    //generate arraylist of arraylist of routes
    public static ArrayList<ArrayList<String>> allRoutes(ArrayList<String> places) {
        ArrayList<ArrayList<String>> allRoutes =new ArrayList<>();
        for (String t :places) {
            ArrayList<String> oneRoute =new ArrayList<>();
            for (int i =0; i < t.length() -1; i ++) {
                oneRoute.add(String.valueOf(t.charAt(i)) +
                        String.valueOf(t.charAt(i +1)));
            }
            allRoutes.add(oneRoute);
        }
        return allRoutes;
    }

    //generate the optimal travelling plan
    public static String[] timeandCost(ArrayList<ArrayList<String>> allRoutes,
                                       double budget) {
        HashMap<String, double[]> map = PlaceData.createMap();
        ArrayList<String> eachRoute =new ArrayList<>();
        ArrayList<Integer> finalTime =new ArrayList<>();
        ArrayList<Double> finalCost =new ArrayList<>();

        for (ArrayList<String> subRoute :allRoutes) {
            int v =(int) Math.pow(3.0, (double) subRoute.size());
            double[] totalTime =new double[v];
            double[] totalCost =new double[v];

            for (int i =0; i <subRoute.size(); i ++) {
                int k =0;

                while (k <v) {

                    for (int j =0; j <6; j +=2) {
                        int counter =0;

                        while (counter <v /((int) Math.pow(3.0, (double) (i +1)))) {
                            totalCost[k] +=map.get(subRoute.get(i))[j];
                            totalTime[k] +=map.get(subRoute.get(i))[j +1];
                            counter ++;
                            k ++;
                        }

                    }
                }
            }

            double minTime =Double.MAX_VALUE;
            double minCost =0;
            int counter2 =0;
            for (int j =0; j <totalTime.length; j ++) {
                if (totalTime[j] <minTime) {
                    if (totalCost[j] <=budget) {
                        minTime =totalTime[j];
                        minCost =totalCost[j];
                        counter2 =j;
                    }
                }
            }

            //create a ternary number
            String trace =Integer.toString(Base(counter2));
            while (trace.length() <subRoute.size()) {
                trace ="0" +trace;
            }
            eachRoute.add(trace);
            finalTime.add((int) minTime);
            finalCost.add(minCost);
        }

        int optTime =Integer.MAX_VALUE;
        for (int t :finalTime) {
            if (t <optTime) {
                optTime =t;
            }
        }

        int k= finalTime.indexOf(optTime);
        String finalRoute ="";
        for (int i =0; i <allRoutes.get(k).size(); i ++) {
            if (i ==allRoutes.get(k).size() -1) {
                finalRoute +=allRoutes.get(k).get(i);
            } else {
                finalRoute +=allRoutes.get(k).get(i) +",";
            }
        }

        String[] solutions ={finalRoute, eachRoute.get(k),
                Double.toString(finalCost.get(k)), Integer.toString(optTime)};
        return solutions;
    }

    //base3
    private static int Base(int n) {
        int r =0;
        int f =1;
        while (n >0) {
            r +=n %3 *f;
            n /=3;
            f *=10;
        }
        return r;
    }

    //choose transport modes
    public static String[] transportation(String[] solutions) {
        String[] ways ={"public", "taxi", "walk"};
        String[] opt =new String[solutions[1].length()];
        for (int i =0; i <solutions[1].length(); i ++) {
            opt[i] =ways[Integer.parseInt(String.valueOf(solutions[1].charAt(i)))];
        }
        return opt;
    }

    //return a string array result
    public static String[] getResult(String[] solutions) {
        HashMap<Character, String> placeCode =new HashMap<>();
        placeCode.put('a', "Marina Bay Sands");
        placeCode.put('b', "Singapore Flyer");
        placeCode.put('c', "Vivo City");
        placeCode.put('d', "Resorts World Sentosa");
        placeCode.put('e', "Buddha Tooth Relic Temple");
        placeCode.put('f', "Zoo");

        char[] opt =new char[solutions[1].length() +1];
        String[] places =new String[solutions[1].length() +1];
        int counter =0;
        for (int i =0; i <solutions[0].length(); i +=3) {
            opt[counter] =solutions[0].charAt(i);
            counter ++;
        }
        opt[solutions[1].length()] ='a';
        for (int j =0; j <opt.length; j ++) {
            places[j] =placeCode.get(opt[j]);
        }
        return places;
    }

    public static String getMode(String[] solution, String startLocation) {
        String[] bestRoute = getResult(solution);
        String[] transModes = getResult(solution);
        int index = Arrays.asList(bestRoute).indexOf(startLocation);
        return startLocation + " to " + bestRoute[index + 1] + ": by " + transModes[index];
    }

    // return the total travelling cost
    public static String getCost(String[] solution) {
        double cost = Math.round(Double.parseDouble(solution[2]) * 100.0) / 100.0;
        return "Total cost: S$" + String.valueOf(cost);
    }

    // return the total travelling time
    public static String getTime(String[] solution) {
        return "Total time: " + solution[3] + " min";
    }
}

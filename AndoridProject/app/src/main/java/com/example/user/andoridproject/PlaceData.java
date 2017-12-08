package com.example.user.andoridproject;

import java.util.HashMap;

/**
 * Created by study on 3/12/17.
 */

public class PlaceData {

    public static HashMap<String, double[]> createMap() {
        HashMap<String, double[]> map =new HashMap<String, double[]>();

        /**
         * A: Marina Bay Sands
         * B: Singapore Flyer
         * C: Vivo City
         * D: Resorts World Sentosa
         * E: Buddha Tooth Relic Temple
         * F: Zoo
         */

        //create table data
        double[] AB ={0.83, 17, 3.22, 3, 0, 14};
        double[] AC ={1.18, 26, 6.96, 14, 0, 69};
        double[] AD ={4.03, 35, 8.50, 19, 0, 76};
        double[] AE ={0.88, 19, 4.98, 8, 0, 28};
        double[] AF ={1.96, 84, 18.4, 30, 0, 269};

        double[] BA ={0.83, 17, 4.32, 6, 0, 14};
        double[] BC ={1.26, 31, 7.84, 13, 0, 81};
        double[] BD ={4.03, 38, 9.38, 18, 0, 88};
        double[] BE ={0.98, 24, 4.76, 8, 0, 39};
        double[] BF ={1.89, 85, 18.18, 29, 0, 264};

        double[] CA ={1.18, 24, 8.30, 12, 0, 69};
        double[] CB ={1.26, 29, 7.96, 14, 0, 81};
        double[] CD ={2.00, 10, 4.54, 9, 0, 12};
        double[] CE ={0.98, 18, 6.42, 11, 0, 47};
        double[] CF ={1.99, 85, 22.58, 31, 0, 270};

        double[] DA ={1.18, 33, 8.74, 13, 0, 76};
        double[] DB ={1.26, 38, 8.40, 14, 0, 88};
        double[] DC ={0.00, 10, 3.22, 4, 0, 12};
        double[] DE ={0.98, 27, 6.64, 12, 0, 55};
        double[] DF ={1.99, 92, 22.80, 32, 0, 285};

        double[] EA ={0.88, 18, 5.32, 7, 0, 28};
        double[] EB ={0.98, 23, 4.76, 8, 0, 39};
        double[] EC ={0.98, 19, 4.98, 9, 0, 47};
        double[] ED ={3.98, 28, 6.52, 14, 0, 55};
        double[] EF ={1.91, 83, 18.4, 30, 0, 264};

        double[] FA ={1.88, 86, 22.48, 32, 0, 269};
        double[] FB ={1.96, 87, 19.40, 29, 0, 264};
        double[] FC ={2.11, 86, 21.48, 32, 0, 270};
        double[] FD ={4.99, 96, 23.68, 36, 0, 285};
        double[] FE ={1.91, 84, 21.60, 30, 0, 264};

        //create hashmap
        map.put("ab", AB);
        map.put("ac", AC);
        map.put("ad", AD);
        map.put("ae", AE);
        map.put("af", AF);

        map.put("ba", BA);
        map.put("bc", BC);
        map.put("bd", BD);
        map.put("be", BE);
        map.put("bf", BF);

        map.put("ca", CA);
        map.put("cb", CB);
        map.put("cd", CD);
        map.put("ce", CE);
        map.put("cf", CF);

        map.put("da", DA);
        map.put("db", DB);
        map.put("dc", DC);
        map.put("de", DE);
        map.put("df", DF);

        map.put("ea", EA);
        map.put("eb", EB);
        map.put("ec", EC);
        map.put("ed", ED);
        map.put("ef", EF);

        map.put("fa", FA);
        map.put("fb", FB);
        map.put("fc", FC);
        map.put("fd", FD);
        map.put("fe", FE);

        return map;
    }
}

package com.example.user.andoridproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    CheckBox flyer;
    CheckBox vivo;
    CheckBox sentosa;
    CheckBox temple;
    CheckBox zoo;
    EditText budget;
    Button brute;
    Button fast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        flyer =(CheckBox) findViewById(R.id.flyer);
        vivo =(CheckBox) findViewById(R.id.vivo);
        sentosa =(CheckBox) findViewById(R.id.sentosa);
        temple =(CheckBox) findViewById(R.id.temple);
        zoo =(CheckBox) findViewById(R.id.zoo);

        budget =(EditText) findViewById(R.id.budget);

        brute =(Button) findViewById(R.id.brute);
        fast =(Button) findViewById(R.id.fast);

    }

    public void brute_force(View v) {
        double mybudget =Double.parseDouble(budget.getText().toString());
        ArrayList<String> places = new ArrayList<>();

        if (flyer.isChecked()) {
            places.add("Singapore Flyer");
        }
        if (sentosa.isChecked()) {
            places.add("Resorts World Sentosa");
        }
        if (temple.isChecked()) {
            places.add("Buddha Tooth Relic Temple");
        }
        if (vivo.isChecked()) {
            places.add("Vivo City");
        }
        if (zoo.isChecked()) {
            places.add("Zoo");
        }

        String[] placessss = new String[places.size()];
        placessss = places.toArray(placessss);

//        String test ="";
//        int size =places.size();
//        for(int i =0; i <size; i ++) {
//            test +=placessss[i];
//        }
//        Toast.makeText(this, "lol", Toast.LENGTH_SHORT);
//        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();


        String[] result;

        ArrayList<String> listOfPlaces = MainAlgo.choosePlaces(placessss);
        ArrayList<ArrayList<String>> possiblePermutations = MainAlgo.allRoutes(listOfPlaces);
        result = MainAlgo.timeandCost(possiblePermutations, mybudget);

        String[] finalRoutes =MainAlgo.getResult(result);
        String[] finalModes =MainAlgo.transportation(result);
        String cost =MainAlgo.getCost(result);
        String time =MainAlgo.getTime(result);

        String test ="";
        int size =finalModes.length;
        for(int i =0; i <size; i ++) {
            test +=finalModes[i];
        }
//        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();

        Intent cintent =new Intent(this, DisplayRoutes.class);
        cintent.putExtra("Final_Route", finalRoutes);
        cintent.putExtra("Final_Modes", finalModes);
        cintent.putExtra("COST", cost);
        cintent.putExtra("TIME", time);
        startActivity(cintent);

    }

    public void fast_algo(View v) {
        double mybudget =Double.parseDouble(budget.getText().toString());
        ArrayList<String> places = new ArrayList<>();

        if (flyer.isChecked()) {
            places.add("Singapore Flyer");
        }
        if (sentosa.isChecked()) {
            places.add("Resorts World Sentosa");
        }
        if (temple.isChecked()) {
            places.add("Buddha Tooth Relic Temple");
        }
        if (vivo.isChecked()) {
            places.add("Vivo City");
        }
        if (zoo.isChecked()) {
            places.add("Zoo");
        }

        String[] placessss = new String[places.size()];
        placessss = places.toArray(placessss);

//        String test ="";
//        int size =places.size();
//        for(int i =0; i <size; i ++) {
//            test +=placessss[i];
//        }
//        Toast.makeText(this, "lol", Toast.LENGTH_SHORT);
//        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();


        String[] result;

        result = MainAlgo.timeandCost((TSPMethod.myRoutes(placessss)), mybudget);
//        Toast.makeText(this, result[0], Toast.LENGTH_SHORT).show();

        String[] finalRoutes =MainAlgo.getResult(result);
        String[] finalModes =MainAlgo.transportation(result);
        String cost =MainAlgo.getCost(result);
        String time =MainAlgo.getTime(result);

//        String test ="";
//        int size =finalModes.length;
//        for(int i =0; i <size; i ++) {
//            test +=finalModes[i];
//        }
//        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();

        Intent cintent =new Intent(this, DisplayRoutes.class);
        cintent.putExtra("Final_Route", finalRoutes);
        cintent.putExtra("Final_Modes", finalModes);
        cintent.putExtra("COST", cost);
        cintent.putExtra("TIME", time);
        startActivity(cintent);

    }
}

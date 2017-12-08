package com.example.user.andoridproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayRoutes extends AppCompatActivity {
    TextView totalcost;
    TextView totaltime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_routes);

        String[] routes = getIntent().getStringArrayExtra("Final_Route");
        String[] modes = getIntent().getStringArrayExtra("Final_Modes");
        String cost = getIntent().getStringExtra("COST");
        String time = getIntent().getStringExtra("TIME");

//        String routee =String.valueOf(routes.length);
//        String modee =String.valueOf(modes.length);
//        Toast.makeText(this, routee +modee, Toast.LENGTH_SHORT).show();

        ArrayList<RouteType> alltheroutes =new ArrayList<>();
        myAdapter adapter =new myAdapter(this, alltheroutes);

        ListView mylistview =(ListView) findViewById(R.id.listview);
        mylistview.setAdapter(adapter);

        for (int i =0; i <modes.length; i ++) {
            RouteType one =new RouteType(routes[i +1], modes[i]);
            adapter.add(one);
        }

        totalcost =(TextView) findViewById(R.id.cost);
        totalcost.setText(cost);

        totaltime =(TextView) findViewById(R.id.time);
        totaltime.setText(time);
    }
}

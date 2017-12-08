package com.example.user.andoridproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity  {

    public static final String KEY="key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickIntro(View view) {
        //intent
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }

    public void onClickList(View view) {
        //intent
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void onClickLoc(View view) {
        //intent
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickIti(View view) {
        //intent
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}

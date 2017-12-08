package com.example.user.andoridproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GuideActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AttractionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    AttractionJsonData[] mJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Intent intent = getIntent();

        //json data

        parseJson();


        //recycler view

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AttractionAdapter(this, mJsonData);
        mRecyclerView.setAdapter(mAdapter);

    }


    //Json data processing

    public class AttractionJsonData{
        String name;
        String introduction;
        String file;
    }

    private String readTxt(int resource){
        String line;
        String output="";
        InputStream inputStream = getResources().openRawResource(resource);

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            while ((line = reader.readLine()) != null){
                output = output + line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }

    void parseJson(){
        Gson gson = new Gson();
        String jsonData = readTxt(R.raw.attractions);
        Log.i("User",jsonData);//print to log cad to check for confidence
        mJsonData =  gson.fromJson(jsonData,AttractionJsonData[].class);
    }
}

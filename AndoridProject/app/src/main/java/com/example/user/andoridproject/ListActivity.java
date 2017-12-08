package com.example.user.andoridproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    private SpendingDbHelper spendingDbHelper;
    private SQLiteDatabase spendingDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();

        // Create a new instance of SpendingDbHelper
        spendingDbHelper = new SpendingDbHelper(this);

        // Get an instance of the database that can be written to
        spendingDb = spendingDbHelper.getWritableDatabase();
        spendingDbHelper.onUpgrade(spendingDb,0,0);

    }

    public void onClickAddToDb(View view){

        EditText enterName = (EditText) findViewById(R.id.editTextName);
        EditText enterDuration = (EditText) findViewById(R.id.editTextDuration);

        String name = enterName.getText().toString();
        String duration = enterDuration.getText().toString();

        // Store the contents into a ContentValues Object
        // variables which are completed in TO DO 3.3
        ContentValues cv = new ContentValues();
        cv.put(SpendingContract.SpendingEntry.COL_NAME, name);
        cv.put(SpendingContract.SpendingEntry.COL_DURATION, duration);

        // Insert the ContentValues object into the database
        spendingDb.insert(SpendingContract.SpendingEntry.TABLE_NAME, null ,cv);

        Toast.makeText(
                this, "add successfully: "+name+", "+duration, Toast.LENGTH_SHORT).show();

    }

    public void onClickGetEntireDb(View view){

        // Call the query or rawQuery method of the spendingDb object and
        //          store the result in a Cursor object
        Cursor cursor = spendingDb.rawQuery("SELECT * FROM "
                + SpendingContract.SpendingEntry.TABLE_NAME, null);

        // Extract the data from the Cursor object and display it on the textView widget

        //Get the reference to your textView widget to display the results in
        TextView textView = (TextView)findViewById(R.id.textViewEntireDatabase) ;

        String outstring = "";

        //Get the column index.
        int indexDuration = cursor.getColumnIndex(SpendingContract.SpendingEntry.COL_DURATION);
        int indexName = cursor.getColumnIndex(SpendingContract.SpendingEntry.COL_NAME);
        int indexID = cursor.getColumnIndex(SpendingContract.SpendingEntry._ID);

        while(cursor.moveToNext()){
            String myDuration = cursor.getString(indexDuration);
            String myName = cursor.getString(indexName);
            String myID = cursor.getString(indexID);

            //id: duration, name
            outstring += "No. " + myID + " Location: " + myName + ", Duration: " + myDuration + "\n";
        }

        textView.setText(outstring);

    }


    public void onClickDeleteFromDb(View view){

        try{
            EditText editTextID = (EditText) findViewById(R.id.editTextID);
            String id = editTextID.getText().toString();

            // DELETE FROM my_table_name WHERE where_clause
            ContentValues cv = new ContentValues();
            cv.put(SpendingContract.SpendingEntry._ID,id);

            final String sqlQuery = "DELETE FROM " + SpendingContract.SpendingEntry.TABLE_NAME
                    + " WHERE " + cv;
            spendingDb.execSQL(sqlQuery);
            Toast.makeText(this,"finish deleting id: "+id,Toast.LENGTH_SHORT).show();

        }catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"???",Toast.LENGTH_SHORT).show();
        }

    }
}


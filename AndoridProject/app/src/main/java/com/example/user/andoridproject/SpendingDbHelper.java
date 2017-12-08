package com.example.user.andoridproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 30/11/2017.
 */

public class SpendingDbHelper extends SQLiteOpenHelper {


    private final Context context;
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;

    SpendingDbHelper(Context context) {
        super(context, SpendingContract.SpendingEntry.TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        SQLite command that creates the table
//        CREATE TABLE SpendingRecord ( _ID INTEGER PRIMARY KEY AUTOINCREMENT,
//                Amount TEXT NOT NULL,
//                Remarks TEXT NOT NULL );

        final String SQL_CREATE_TABLE = "CREATE TABLE "
                + SpendingContract.SpendingEntry.TABLE_NAME + " ( "
                + SpendingContract.SpendingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SpendingContract.SpendingEntry.COL_NAME + " TEXT NOT NULL, "
                + SpendingContract.SpendingEntry.COL_DURATION + " TEXT NOT NULL );";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

//        SQLite command that deletes the table
//        DROP TABLE IF EXISTS SpendingRecord

        final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
                + SpendingContract.SpendingEntry.TABLE_NAME;

        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);

    }

}

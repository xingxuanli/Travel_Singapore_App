package com.example.user.andoridproject;

import android.provider.BaseColumns;

/**
 * Created by User on 30/11/2017.
 */

public class SpendingContract {

    public static final class SpendingEntry implements BaseColumns {

        // default ID column called "_ID"
        public static final String TABLE_NAME = "FavouriteList";
        public static final String COL_NAME = "Name";
        public static final String COL_DURATION = "Duration";

    }
}
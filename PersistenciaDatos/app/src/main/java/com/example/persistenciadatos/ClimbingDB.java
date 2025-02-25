package com.example.persistenciadatos;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ClimbingDB extends SQLiteAssetHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "climbing.db";

    public ClimbingDB(Context context) {
        super(context, DBNAME, null, VERSION);
    }
}

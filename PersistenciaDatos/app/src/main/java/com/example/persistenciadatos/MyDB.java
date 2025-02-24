package com.example.persistenciadatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    private static String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER PRIMARY KEY, nombre TEXT)";
    private static String sqlDrop = "DROP TABLE IF EXISTS Usuarios";

    public static final String DBNAME = "Usuarios";
    public static final int VERSION = 1;

    public MyDB(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDrop);
        db.execSQL(sqlCreate);
    }

    public long insert(String tableName, ContentValues contentValues) {
        SQLiteDatabase wdb = this.getWritableDatabase();

        wdb.beginTransaction();
        long res = wdb.insert(tableName, null, contentValues);
        wdb.endTransaction();

        wdb.close();

        return res;
    }

    public void select (Context context) {
        SQLiteDatabase rdb = this.getWritableDatabase();
        String[] params = new String[1];
        params[0] = "1";
        Cursor c = rdb.rawQuery("SELECT * FROM Usuarios WHERE codigo = ?", params);

        if (c.moveToFirst()) {
            do {
                String usuario = c.getString(c.getColumnIndexOrThrow("codigo")) +
                        " - " + c.getString(c.getColumnIndexOrThrow("nombre"));
                Toast.makeText(context, usuario, Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }

        c.close();
        rdb.close();
    }
}

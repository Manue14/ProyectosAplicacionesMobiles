package com.example.persistenciadatos;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String EMAIL = "email";
    private static final String TOKEN = "token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString(EMAIL, "pgonvaz@edu.xunta.gal");

        editor.apply();
        //editor.commit();

        String email = sp.getString(EMAIL, null);

        try {
            MasterKey mk = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();

            SharedPreferences spEncrypted = EncryptedSharedPreferences.create(this, "ENCTPTEDSHARE",
                    mk, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            SharedPreferences.Editor ed = spEncrypted.edit();
            ed.putString(TOKEN, "123456");

            ed.apply();

           String t = spEncrypted.getString(TOKEN, null);

            //Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //startActivity(new Intent(this, PreferenceShow.class));

        MyDB db = new MyDB(this, MyDB.DBNAME, MyDB.VERSION);

        ContentValues cv = new ContentValues();
        cv.put("codigo", 1);
        cv.put("nombre", "prueba");

        db.insert("Usuarios", cv);

        db.select(this);


    }
}
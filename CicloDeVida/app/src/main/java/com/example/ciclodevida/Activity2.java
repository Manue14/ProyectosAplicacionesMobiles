package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.i("ciclo_de_vida","Estamos en onCreate() de la Activity2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ciclo_de_vida","Estamos en onStart() de la Activity2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ciclo_de_vida","Estamos en onPause() de la Activity2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ciclo_de_vida","Estamos en onResume() de la Activity2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ciclo_de_vida","Estamos en onStop() de la Activity2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ciclo_de_vida","Estamos en onRestart() de la Activity2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ciclo_de_vida","Estamos en onDestroy() de la Activity2");
    }
}
package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Estamos en onCreate()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Estamos en onStart()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Estamos en onPause()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Estamos en onResume()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Estamos en onStop()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Estamos en onRestart()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Estamos en onDestroy()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onDestroy()");
    }

    public void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_finalizar:
                finish();
            break;

            case R.id.btn_activity_2:
                //generar el objeto de la clase Intent
                Intent intent = new Intent(this, Activity2.class);
                startActivity(intent);
            break;
        }
    }
}
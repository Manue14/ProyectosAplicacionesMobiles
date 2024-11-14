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
    private String strDatoEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Estamos en onCreate()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onCreate() de la Activity1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Estamos en onStart()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onStart() de la Activity1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Estamos en onPause()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onPause() de la Activity1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Estamos en onResume()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onResume() de la Activity1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Estamos en onStop()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onStop() de la Activity1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Estamos en onRestart()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onRestart() de la Activity1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Estamos en onDestroy()", Toast.LENGTH_LONG).show();
        Log.i("ciclo_de_vida","Estamos en onDestroy() de la Activity1");
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

            case R.id.btn_activity_3:
                Intent intent3 = new Intent(this, Activity3.class);
                this.strDatoEnvio = "Esto es un mensaje para la Activity 3";
                //añadimos al intent cada uno de los datos a enviar
                intent3.putExtra("mensaje", strDatoEnvio);
                intent3.putExtra("numero", 10);
                startActivity(intent3);
            break;

            case R.id.btn_activity_4:
                Intent intent4 = new Intent(this, Activity4.class);
                this.strDatoEnvio = "Esto es un mensaje para la Activity 4";
                //añadimos un bundle con los datos a enviar
                //TODO
                startActivity(intent4);
            break;
        }
    }
}
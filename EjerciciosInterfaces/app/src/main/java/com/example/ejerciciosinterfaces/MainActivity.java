package com.example.ejerciciosinterfaces;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        textViewRespuesta = findViewById(R.id.view_respuesta);
    }

    public void onClickBtnSi(View view) {
        textViewRespuesta.setText("Has pulsado el botón Sí");
    }

    public void onClickBtnNo(View view) {
    }
}
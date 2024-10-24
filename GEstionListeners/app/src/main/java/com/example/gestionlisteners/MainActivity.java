package com.example.gestionlisteners;

import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView viewRespuesta;
    Button btn_clase_interna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewRespuesta = findViewById(R.id.view_respuesta);

        //crear objeto de la clase Auxiliar
        Auxiliar escuchador_1 = new Auxiliar();
        //asociar el escuhador a la vista correspondiente
        btn_clase_interna = findViewById(R.id.btn_clase_interna);
        btn_clase_interna.setOnClickListener(escuchador_1);
    }//end onCreate

    //Método 1: crear una clase interna auxiliar para implementar el escuchador
    private class Auxiliar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //gestinar la pulsación del botón 1
            viewRespuesta.setText("Botón clase interna pulsado");
        }
    }//end Auxiliar

}//end MainActivity
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView viewRespuesta;
    Button btn_clase_interna, btn_clase_anonima, btn_3_en_1, btn_implements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewRespuesta = findViewById(R.id.view_respuesta);

        //Método 1
        //crear objeto de la clase Auxiliar
        Auxiliar escuchador_1 = new Auxiliar();
        //asociar el escuhador1 (clase auxiliar) a la vista correspondiente
        btn_clase_interna = findViewById(R.id.btn_clase_interna);
        btn_clase_interna.setOnClickListener(escuchador_1);

        //Método 2
        //asociar el escuchador2 (clase anónima) a la vista correspondiente
        btn_clase_anonima = findViewById(R.id.btn_clase_anonima);
        btn_clase_anonima.setOnClickListener(escuchador2);

        //Método 3
        btn_3_en_1 = findViewById(R.id.btn_3en1);
        btn_3_en_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewRespuesta.setText("Botón 3 en 1 pulsado");
            }
        });

        //Método 4
        btn_implements = findViewById(R.id.btn_implements);
        btn_implements.setOnClickListener(this);

    }//end onCreate

    @Override
    public void onClick(View view) {
        viewRespuesta.setText("Botón método implements pulsado");
    }

    //Método 1: crear una clase interna auxiliar para implementar el escuchador
    private class Auxiliar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //gestinar la pulsación del botón 1
            viewRespuesta.setText("Botón clase interna pulsado");
        }
    }//end Auxiliar

    //Método 2: objeto escuchador mediante una clase anónima
    private View.OnClickListener escuchador2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            viewRespuesta.setText("Botón clase anónima pulsado");
        }
    };//end clase anónima

}//end MainActivity
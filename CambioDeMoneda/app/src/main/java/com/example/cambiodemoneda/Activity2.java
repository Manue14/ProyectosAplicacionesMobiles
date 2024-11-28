package com.example.cambiodemoneda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private TextView txt_resultado;
    private Button btn_volver;

    private final float PESETAS_CONST = 0.00601f;
    private final float EUROS_CONST = 166.386f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        initialize();
        Intent intent = getIntent();
        float cantidad = intent.getFloatExtra("cantidad", 0);
        boolean toPesetas = intent.getBooleanExtra("toPesetas", false);
        boolean toEuros = intent.getBooleanExtra("toEuros", false);

        String inicial = String.format("%.02f", cantidad);
        if (toPesetas) {
            String conversion = String.format("%.02f", this.eurosToPesetas(cantidad));
            this.txt_resultado.setText(inicial + " euros equivalen a " + conversion + " pesetas");
        } else if (toEuros) {
            String conversion = String.format("%.02f", this.pesetasToEuros(cantidad));
            this.txt_resultado.setText(inicial + " pesetas equivalen a " + conversion + " euros");
        }

        this.btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity2.this.finish();
            }
        });
    }

    private void initialize() {
        this.txt_resultado = findViewById(R.id.txt_resultado);
        this.btn_volver = findViewById(R.id.btn_volver);
    }

    private float pesetasToEuros(float pesetas) {
        return pesetas * PESETAS_CONST;
    }

    private float eurosToPesetas(float euros) {
        return euros * EUROS_CONST;
    }
}

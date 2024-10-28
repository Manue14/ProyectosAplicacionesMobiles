package com.example.ejercicioseventos2;

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
    Button btnClick, btnFin, btnReset;
    TextView txtClicks;
    int counter = 0;
    String message = "Has pulsado " + counter + " veces";
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            txtClicks.setText(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtClicks = findViewById(R.id.txt_clicks);
        txtClicks.setText(message);

        Auxiliar axuliarObject = new Auxiliar();
        btnClick = findViewById(R.id.btn_click);
        btnClick.setOnClickListener(axuliarObject);

        btnFin = findViewById(R.id.btn_fin);
        btnFin.setOnClickListener(listener);

        btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                message = "Has pulsado " + counter + " veces";
                txtClicks.setText(message);
            }
        });
    }

    private class Auxiliar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            counter += 1;
            message = "Has pulsado " + counter + " veces";
        }
    }
}
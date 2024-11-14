package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity3 extends AppCompatActivity {
    private TextView tvRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        this.tvRecibido = findViewById(R.id.tv_dato_recibido);

        Intent intent = getIntent();
        String strDatoRecibido = intent.getStringExtra("mensaje");
        int datoRecibido = intent.getIntExtra("numero", -1);
        tvRecibido.setText(strDatoRecibido);
        tvRecibido.append("\n" + datoRecibido);
    }
}
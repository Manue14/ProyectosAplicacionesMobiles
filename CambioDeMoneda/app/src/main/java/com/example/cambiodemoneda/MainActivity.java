package com.example.cambiodemoneda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private RadioButton rd_pesetas_euros;
    private RadioButton rd_euros_pesetas;
    private EditText et_moneda;
    private Button btn_cambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        Toast toast = Toast.makeText(this, "Introduzca un valor válido", Toast.LENGTH_SHORT);
        this.btn_cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_moneda.getText().toString().isEmpty()) {
                    toast.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    intent.putExtra("cantidad", Float.parseFloat(et_moneda.getText().toString()));
                    intent.putExtra("toEuros", rd_pesetas_euros.isChecked());
                    intent.putExtra("toPesetas", rd_euros_pesetas.isChecked());
                    startActivity(intent);
                }
            }
        });
    }

    private void initialize() {
        this.rd_pesetas_euros = findViewById(R.id.rd_pesetas_euros);
        this.rd_euros_pesetas = findViewById(R.id.rd_euros_pesetas);
        this.et_moneda = findViewById(R.id.et_moneda);
        this.btn_cambiar = findViewById(R.id.btn_cambiar);
    }
}
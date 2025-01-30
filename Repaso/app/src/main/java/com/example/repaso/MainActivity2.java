package com.example.repaso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    public static final String ACTIVITY_RES_MSG = "msg";
    private TextView txtName;
    private Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initialize();

        String nombre = Objects.requireNonNull(getIntent().getExtras()).getString("nombre");
        txtName.setText(String.format(getString(R.string.hola), nombre));

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    private void initialize() {
        txtName = findViewById(R.id.txt_name);
        btnVolver = findViewById(R.id.btn_volver);
    }

    private void goBack() {
        Intent intent = new Intent();
        intent.putExtra(ACTIVITY_RES_MSG, getString(R.string.cierre));
        setResult(MainActivity2.RESULT_OK, intent);
        finish();
    }
}
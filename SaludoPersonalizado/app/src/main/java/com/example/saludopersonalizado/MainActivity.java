package com.example.saludopersonalizado;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre, txtNacimiento;
    private Button btnSaludar;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNombre(txtNombre.getText().toString()) &&
                checkNacimiento(txtNacimiento.getText().toString())) {
                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    int nacimiento = Integer.parseInt(txtNacimiento.getText().toString());
                    //StringBuilder
                    if (year - nacimiento >= 18) {
                        txtView.setText("hola, " + txtNombre.getText().toString() + " eres mayor de edad");
                    } else {
                        txtView.setText("hola, " + txtNombre.getText().toString() + " no eres mayor de edad");
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Faltan valores o no son válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeViews() {
        this.txtNombre = findViewById(R.id.txt_nombre);
        this.txtNacimiento = findViewById(R.id.txt_nacimiento);
        this.btnSaludar = findViewById(R.id.btn_saludar);
        this.txtView = findViewById(R.id.txt_view);
    }

    private boolean checkNombre(String nombre) {
        if (nombre.trim() == null || nombre.trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkNacimiento(String nacimiento) {
        if (nacimiento.trim() == null || nacimiento.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(nacimiento);
        Matcher matcher = pattern.matcher("[1-9][0-9]{3}");

        return matcher.find();
    }
}
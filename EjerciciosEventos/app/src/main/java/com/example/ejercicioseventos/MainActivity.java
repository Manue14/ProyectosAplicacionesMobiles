package com.example.ejercicioseventos;

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
    TextView clicksView;
    Button clickButton;
    int counter = 0;
    String message = "Has pulsado " + counter + " veces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicksView = findViewById(R.id.txt_clicks);
        clicksView.setText(message);

        clickButton = findViewById(R.id.btn_click);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter += 1;
                clicksView.setText("Has pulsado " + counter + " veces");
            }
        });
    }
}
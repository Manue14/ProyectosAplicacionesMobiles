package com.example.ejercicioselectorcolores;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private TextView txtRed, txtGreen, txtBlue, txtAlpha;
    private View viewColor;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_view);

        this.initialize();

        this.btnReturn.setOnClickListener(returnListener);

        Bundle colorBundle = getIntent().getBundleExtra("colorBundle");
        int redValue = colorBundle.getInt("red");
        int greenValue = colorBundle.getInt("green");
        int blueValue = colorBundle.getInt("blue");
        int alphaValue = colorBundle.getInt("alpha");

        this.txtRed.setText(Integer.toString(redValue));
        this.txtGreen.setText(Integer.toString(greenValue));
        this.txtBlue.setText(Integer.toString(blueValue));
        this.txtAlpha.setText(Integer.toString(alphaValue));

        this.viewColor.setBackgroundColor(Color.argb(alphaValue, redValue, greenValue, blueValue));
    }

    private void initialize() {
        this.viewColor = findViewById(R.id.view_color_2);
        this.txtRed = findViewById(R.id.txt_red_value);
        this.txtGreen = findViewById(R.id.txt_green_value);
        this.txtBlue = findViewById(R.id.txt_blue_value);
        this.txtAlpha = findViewById(R.id.txt_alpha_value);
        this.btnReturn = findViewById(R.id.btn_return);
    }

    private View.OnClickListener returnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Activity2.this.finish();
        }
    };
}

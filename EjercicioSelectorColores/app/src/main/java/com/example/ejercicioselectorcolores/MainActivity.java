package com.example.ejercicioselectorcolores;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar skbRed, skbGreen, skbBlue, skbAlpha;
    private TextView txtRed, txtGreen, txtBlue, txtAlpha;
    private View viewColor;
    private int redValue, greenValue, blueValue, alphaValue;
    private HashMap<Integer, String> equivalencies = new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        updateColorView();

        this.skbRed.setOnSeekBarChangeListener(this);
        this.skbGreen.setOnSeekBarChangeListener(this);
        this.skbBlue.setOnSeekBarChangeListener(this);
        this.skbAlpha.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.red_bar:
                this.redValue = i;
                this.txtRed.setText(String.valueOf(i));
            break;

            case R.id.green_bar:
                this.greenValue = i;
                this.txtGreen.setText(String.valueOf(i));
            break;

            case R.id.blue_bar:
                this.blueValue = i;
                this.txtBlue.setText(String.valueOf(i));
            break;

            case R.id.alpha_bar:
                this.alphaValue = i;
                this.txtAlpha.setText(String.valueOf(i));
            break;

        }
        updateColorView();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    private void initialize() {
        this.skbRed = findViewById(R.id.red_bar);
        this.skbGreen = findViewById(R.id.green_bar);
        this.skbBlue = findViewById(R.id.blue_bar);
        this.skbAlpha = findViewById(R.id.alpha_bar);
        this.txtRed = findViewById(R.id.txt_red_value);
        this.txtGreen = findViewById(R.id.txt_green_value);
        this.txtBlue = findViewById(R.id.txt_blue_value);
        this.txtAlpha = findViewById(R.id.txt_alpha_value);
        this.viewColor = findViewById(R.id.view_color);

        this.redValue = this.skbRed.getProgress();
        this.greenValue = this.skbGreen.getProgress();
        this.blueValue = this.skbBlue.getProgress();
        this.alphaValue = this.skbAlpha.getProgress();

        this.txtRed.setText(String.valueOf(this.redValue));
        this.txtGreen.setText(String.valueOf(this.greenValue));
        this.txtBlue.setText(String.valueOf(this.blueValue));
        this.txtAlpha.setText(String.valueOf(this.alphaValue));

        this.equivalencies.put(10, "A");
        this.equivalencies.put(11, "B");
        this.equivalencies.put(12, "C");
        this.equivalencies.put(13, "D");
        this.equivalencies.put(14, "E");
        this.equivalencies.put(15, "F");
    }

    private void updateColorView() {
        String hexValue = "#" + intToHex(this.alphaValue) + intToHex(this.redValue) +
                intToHex(this.greenValue) + intToHex(this.blueValue);
        this.viewColor.setBackgroundColor(Color.parseColor(hexValue));
    }

    private String intToHex(int i) {
        String hex = "";
        do {
            hex = (i % 16 >= 10 ? this.equivalencies.get(i % 16) : i % 16) + hex;
            i = i / 16;
        } while (i > 0);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }
}
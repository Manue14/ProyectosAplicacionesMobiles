package com.example.ejercicioeventosetiqueta;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView myTextView;
    private ImageView myImgView;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.my_text_view);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    myTextView.setText(getResources().getString(R.string.changed_text));
                    myTextView.setTextSize(getResources().getDimension(R.dimen.changed_text_size));
                    myTextView.setTextColor(getResources().getColor(R.color.blue));
                    myTextView.setBackgroundColor(getResources().getColor(R.color.green));
                    counter += 1;
                } else {
                    girar(myTextView);
                }
            }
        });

        myImgView = findViewById(R.id.my_img_view);
        myImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girar(myImgView);
            }
        });
    }

    private void girar(View view) {
        float rotation = view.getRotation();
        view.setRotation(rotation + 45);
    }
}
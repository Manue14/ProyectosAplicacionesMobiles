package com.example.ejercicioeventosetiqueta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
    private Button btnAccept, returnButton;
    private RadioGroup rdGroup;
    private int selectedId;
    private LinearLayout initialLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

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

        myImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girar(myImgView);
            }
        });

        selectedId = rdGroup.getCheckedRadioButtonId();
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = i;
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setView(selectedId);
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnView();
            }
        });
    }

    private void girar(View view) {
        float rotation = view.getRotation();
        view.setRotation(rotation + 45);
    }

    private void setView(int id) {
        if (id == R.id.rd_txt) {
            initialLayout.setVisibility(View.GONE);
            myImgView.setVisibility(View.GONE);

            myTextView.setVisibility(View.VISIBLE);
            returnButton.setVisibility(View.VISIBLE);
        } else {
            initialLayout.setVisibility(View.GONE);
            myTextView.setVisibility(View.GONE);

            myImgView.setVisibility(View.VISIBLE);
            returnButton.setVisibility(View.VISIBLE);
        }
    }

    private void returnView() {
        myTextView.setVisibility(View.GONE);
        myImgView.setVisibility(View.GONE);
        returnButton.setVisibility(View.GONE);

        initialLayout.setVisibility(View.VISIBLE);
    }

    private void initializeViews() {
        initialLayout = findViewById(R.id.inital_layout);
        btnAccept = findViewById(R.id.accept_btn);
        returnButton = findViewById(R.id.return_btn);
        myTextView = findViewById(R.id.my_text_view);
        myImgView = findViewById(R.id.my_img_view);
        rdGroup = findViewById(R.id.rd_group);
        btnAccept = findViewById(R.id.accept_btn);
        returnButton = findViewById(R.id.return_btn);
    }
}
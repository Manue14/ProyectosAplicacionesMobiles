package com.example.ejercicioeventosetiqueta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final float MIN_TURN_ANGLE = 10f;
    private static final float MAX_TURN_ANGLE = 90f;

    private TextView myTextView;
    private ImageView myImgView;
    private int counter = 0;
    private Button btnAccept, returnButton;
    private RadioGroup rdGroup;
    private int selectedId;
    private LinearLayout initialLayout;
    private EditText txtAngle;
    float angleTurn = 0f;

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
                String angleStr = txtAngle.getText().toString();
                if (angleStr != null && !angleStr.trim().isEmpty()) {
                    angleTurn = Float.parseFloat(angleStr);

                    if (angleTurn < MIN_TURN_ANGLE || angleTurn > MAX_TURN_ANGLE) {
                        showToast("Introduzca un valor en el rango especificado");
                    } else {
                        showToast("Ángulo de giro sestablecido a: " + angleTurn);
                        setView(selectedId);
                    }

                } else {
                    showToast("Introduzca un valor");
                }
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
        if (view.getRotation() >= 360f) {
            view.setRotation(view.getRotation() % 360f);
        }
        view.setRotation(view.getRotation() + angleTurn);
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
        txtAngle = findViewById(R.id.txt_angle);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
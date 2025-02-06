package com.example.recyclerviewproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AnimalView extends AppCompatActivity {
    private TextView titleView;
    private TextView textView;
    private ImageView imgView;
    private Button backButton;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.animal_view);
        initialize();

        titleView.setText(animal.getName());
        textView.setText(animal.getText());
        imgView.setImageResource(animal.getImg_resource_id());

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("animal", animal);
            setResult(AnimalView.RESULT_OK, intent);
            this.finish();
        });
    }

    private void initialize() {
        titleView = findViewById(R.id.animal_view_title_view);
        textView = findViewById(R.id.animal_view_text_view);
        imgView = findViewById(R.id.animal_view_img_view);
        backButton = findViewById(R.id.animal_view_back_button);

        Intent intent = getIntent();
        animal = intent.getParcelableExtra("animal");
    }
}

package com.example.repaso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnAct2Deprecated, btnAct2, btnCameraDeprecated, btnCamera;
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        btnAct2Deprecated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deprecatedChange();
            }
        });
    }

    private void initialize() {
        btnAct2Deprecated = findViewById(R.id.btn_act2_deprecated);
        btnAct2 = findViewById(R.id.btn_act2);
        btnCameraDeprecated = findViewById(R.id.btn_camera_deprecated);
        btnCamera = findViewById(R.id.btn_camera);
        myTextView = findViewById(R.id.my_txt);
    }

    private void deprecatedChange() {
        Intent intent = new Intent(this, MainActivity2.class);
        //Intent intent = new Intent(view.getContext(), MainActivity2.class);
        intent.putExtra("nombre", "Manuel");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            myTextView.setText("Resultado correcto " + data.getStringExtra(MainActivity2.ACTIVITY_RES_MSG));
        }
    }
}
package com.example.repaso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnAct2Deprecated, btnAct2, btnCameraDeprecated, btnCamera;
    private TextView myTextView;
    private ActivityResultLauncher<Intent> intentLlamadaMainActivity2;

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

        btnAct2.setOnClickListener(registerToActivity2);
    }

    private void initialize() {
        btnAct2Deprecated = findViewById(R.id.btn_act2_deprecated);
        btnAct2 = findViewById(R.id.btn_act2);
        btnCameraDeprecated = findViewById(R.id.btn_camera_deprecated);
        btnCamera = findViewById(R.id.btn_camera);
        myTextView = findViewById(R.id.my_txt);

        intentLlamadaMainActivity2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        myTextView.setText("Activity Result OK: Callback " + result.getData().getStringExtra(MainActivity2.ACTIVITY_RES_MSG));
                    }
                });
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

    private View.OnClickListener registerToActivity2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), MainActivity2.class);
            intent.putExtra("nombre", "Manuel");
            intentLlamadaMainActivity2.launch(intent);
        }
    };
}
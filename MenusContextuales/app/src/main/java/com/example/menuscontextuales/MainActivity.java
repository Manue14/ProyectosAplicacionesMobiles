package com.example.menuscontextuales;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Toolbar tb;
    private TextView tagTxt;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();

        setSupportActionBar(tb);

        registerForContextMenu(tagTxt);
        registerForContextMenu(imgView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.tagTextView) {
            getMenuInflater().inflate(R.menu.text_menu, menu);
        }

        if (v.getId() == R.id.imageView) {
            getMenuInflater().inflate(R.menu.img_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int selectedId = item.getItemId();

        if (selectedId == R.id.text_op1) {
            Toast.makeText(this, "Opción 1 de text pulsada", Toast.LENGTH_SHORT).show();
        }

        if (selectedId == R.id.text_op2) {
            Toast.makeText(this, "Opción 2 de text pulsada", Toast.LENGTH_SHORT).show();
        }

        if (selectedId == R.id.text_op3) {
            Toast.makeText(this, "Opción 3 de text pulsada", Toast.LENGTH_SHORT).show();
        }

        if (selectedId == R.id.img_opA) {
            Toast.makeText(this, "Opción A de image pulsada", Toast.LENGTH_SHORT).show();
        }

        if (selectedId == R.id.img_opB) {
            Toast.makeText(this, "Opción B de image pulsada", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }


    private void initialize() {
        this.tb = findViewById(R.id.toolbar);
        this.tagTxt = findViewById(R.id.tagTextView);
        this.imgView = findViewById(R.id.imageView);
    }
}
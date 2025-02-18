package com.example.toolbarexample;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ToolBarDeprecated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tool_bar_deprecated);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar tb = findViewById(R.id.toolbar2);
        setSupportActionBar(tb);

        TextView tv = findViewById(R.id.textView);
        registerForContextMenu(tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudef, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.op1) {
            Toast.makeText(this, "Opción 1 deprecated", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.op2) {
            Toast.makeText(this, "Opción 2 deprecated", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.op3) {
            Toast.makeText(this, "Opción 3 deprecated", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menudef, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.op1) {
            Toast.makeText(this, "Opción 1 deprecated context", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.op2) {
            Toast.makeText(this, "Opción 2 deprecated context", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.op3) {
            Toast.makeText(this, "Opción 3 deprecated context", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
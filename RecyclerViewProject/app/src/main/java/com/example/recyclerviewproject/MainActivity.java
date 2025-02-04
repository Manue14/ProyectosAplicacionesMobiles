package com.example.recyclerviewproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;

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

        ArrayList<Animal> animales = new ArrayList<>();
        Animal aguila = new Animal("Águila", "Texto águila", R.drawable.aguila);
        Animal ballena = new Animal("Ballena", "Texto ballena", R.drawable.ballena);
        Animal caballo = new Animal("Caballo", "Texto caballo", R.drawable.caballo);
        Animal canario = new Animal("Canario", "Texto canario", R.drawable.canario);
        Animal delfin = new Animal("Delfín", "Texto delfín", R.drawable.delfin);
        Animal gato = new Animal("Gato", "Texto gato", R.drawable.gato);
        Animal perro = new Animal("Perro", "Texto perro", R.drawable.perro);
        Animal vaca = new Animal("Vaca", "Texto vaca", R.drawable.vaca);
        animales.add(aguila);
        animales.add(ballena);
        animales.add(caballo);
        animales.add(canario);
        animales.add(delfin);
        animales.add(gato);
        animales.add(perro);
        animales.add(vaca);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(new AnimalListAdapter(animales));
    }

    private void initialize() {
        myRecyclerView = findViewById(R.id.my_recycler_view);
    }
}
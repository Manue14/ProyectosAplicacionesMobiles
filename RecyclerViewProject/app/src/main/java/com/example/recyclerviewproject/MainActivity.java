package com.example.recyclerviewproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private ActivityResultLauncher<Intent> animalViewResultLauncher;
    private AnimalViewModel avm;

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

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        avm.getMutableLiveData().observe(this, animals -> {
            myRecyclerView.setAdapter(new AnimalListAdapter(animals, animalViewResultLauncher));
        });
    }

    private void initialize() {
        myRecyclerView = findViewById(R.id.my_recycler_view);
        initializeLista();
        animalViewResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Animal animal = result.getData().getParcelableExtra("animal");
                    avm.markAsRead(animal);
                });
    }

    private void initializeLista() {
        LinkedList<Animal> animales = new LinkedList<>();
        Animal aguila = new Animal("Águila", "Texto águila.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.aguila);
        Animal ballena = new Animal("Ballena", "Texto ballena.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.ballena);
        Animal caballo = new Animal("Caballo", "Texto caballo.\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.caballo);
        Animal canario = new Animal("Canario", "Texto canario.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.canario);
        Animal delfin = new Animal("Delfín", "Texto delfín.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.delfin);
        Animal gato = new Animal("Gato", "Texto gato.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.gato);
        Animal perro = new Animal("Perro", "Texto perro.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.perro);
        Animal vaca = new Animal("Vaca", "Texto vaca.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.vaca);
        animales.add(aguila);
        animales.add(ballena);
        animales.add(caballo);
        animales.add(canario);
        animales.add(delfin);
        animales.add(gato);
        animales.add(perro);
        animales.add(vaca);

        avm = new ViewModelProvider(this).get(AnimalViewModel.class);

        if (avm.getAnimales() == null) {
            avm.setAnimales(animales);
        }
    }
}
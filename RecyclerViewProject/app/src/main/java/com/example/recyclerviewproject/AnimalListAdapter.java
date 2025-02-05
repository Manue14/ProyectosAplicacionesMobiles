package com.example.recyclerviewproject;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalListAdapter extends RecyclerView.Adapter {
    private List<Animal> animales;
    private ActivityResultLauncher<Intent> animalViewResultLauncher;

    public AnimalListAdapter(List<Animal> animales, MainActivity mainActivity){
        this.animales = animales;
        animalViewResultLauncher = mainActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d("My debug msg", result.getData().getStringExtra("animal"));
                });
    }

    @NonNull
    @Override
    public CardFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card, parent, false);
        return new CardFragment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CardFragment cardFragment = (CardFragment) holder;
        Animal animal = animales.get(position);
        cardFragment.getAnimalTitleView().setText(animal.getName());
        cardFragment.getAnimalTextView().setText(animal.getText());
        cardFragment.getAnimalImgView().setImageResource(animal.getImg_resource_id());
        cardFragment.getSeeAnimalButton().setOnClickListener(view -> {
            onClickSeeButton(animal, view);
        });
    }

    @Override
    public int getItemCount() {
        return animales.size();
    }

    private void onClickSeeButton(Animal animal, View view) {
        Intent intent = new Intent(view.getContext(), AnimalView.class);
        intent.putExtra("animal", animal);
        animalViewResultLauncher.launch(intent);
    }
}

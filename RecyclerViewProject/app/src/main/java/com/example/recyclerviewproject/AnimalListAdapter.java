package com.example.recyclerviewproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalListAdapter extends RecyclerView.Adapter {
    private List<Animal> animales;

    public AnimalListAdapter(List<Animal> animales){
        this.animales = animales;
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
            System.out.println(animal.getName());
        });
    }

    @Override
    public int getItemCount() {
        return animales.size();
    }

    private void onClickSeeButton(Animal animal) {

    }
}

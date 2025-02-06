package com.example.recyclerviewproject;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;

public class AnimalViewModel extends ViewModel {
    private MutableLiveData<List<Animal>> animales = new MutableLiveData<>();

    public MutableLiveData<List<Animal>> getMutableLiveData() {
        return this.animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales.setValue(animales);
    }

    public List<Animal> getAnimales() {
        return this.animales.getValue();
    }

    public void markAsRead(Animal animal) {
        List<Animal> animales1 = this.getAnimales();
        for (Animal a : animales1) {
            if (a.getName().equals(animal.getName())) {
                a.setRead(true);
            }
        }
        setAnimales(animales1);
    }
}

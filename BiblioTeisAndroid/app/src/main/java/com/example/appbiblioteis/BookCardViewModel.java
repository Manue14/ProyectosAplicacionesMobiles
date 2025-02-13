package com.example.appbiblioteis;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbiblioteis.API.models.Book;

import java.util.List;

public class BookCardViewModel extends ViewModel {
    private MutableLiveData<List<Book>> libros = new MutableLiveData<>();

    public MutableLiveData<List<Book>> getMutableLiveData() {
        return this.libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros.setValue(libros);
    }

    public List<Book> getLibros() {
        return this.libros.getValue();
    }
}
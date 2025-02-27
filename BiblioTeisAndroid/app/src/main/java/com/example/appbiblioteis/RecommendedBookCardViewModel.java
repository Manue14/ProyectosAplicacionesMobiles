package com.example.appbiblioteis;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendedBookCardViewModel extends ViewModel {
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

    public void loadRecommendedBooks() {

        new BookRepository().getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {

                List<Book> recommendedBooks = new ArrayList<>();

                Collections.shuffle(result);

                recommendedBooks.add(result.get(0));
                recommendedBooks.add(result.get(1));
                recommendedBooks.add(result.get(2));
                recommendedBooks.add(result.get(3));

                libros.setValue(recommendedBooks);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Libros", "Error al obtener nuevos libros: " + t.getMessage());
            }
        });
    }
}
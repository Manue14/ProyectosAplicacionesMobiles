package com.example.appbiblioteis;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewBookCardViewModel extends ViewModel {
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

    public void loadNewBooks() {

        new BookRepository().getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {

                List<Book> newBooks = new ArrayList<>();
                result.sort((book1, book2) -> {
                    return -1 * LocalDateTime.parse(book1.getPublishedDate()).compareTo(
                            LocalDateTime.parse(book2.getPublishedDate())
                    );
                });

                newBooks.add(result.get(0));
                newBooks.add(result.get(1));
                newBooks.add(result.get(2));
                newBooks.add(result.get(3));

                libros.setValue(newBooks);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Libros", "Error al obtener nuevos libros: " + t.getMessage());
            }
        });
    }
}

package com.example.appbiblioteis;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvailableBookCardViewModel extends ViewModel {
    private List<Book> allAvailableBooks = new ArrayList<>();
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

    public void loadAvailableBooks() {

        new BookRepository().getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {

                for (Book book : result) {
                    if (book.isAvailable()) {
                        allAvailableBooks.add(book);
                    }
                }

                libros.setValue(allAvailableBooks);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Libros", "Error al obtener nuevos libros: " + t.getMessage());
            }
        });
    }

    public void filter(CharSequence author, CharSequence title) {
        List<Book> copy = new ArrayList<>();

        for (Book libro : allAvailableBooks) {
            if (libro.getAuthor().contains(author) && libro.getTitle().contains(title)) {
                copy.add(libro);
            }
        }
        libros.setValue(copy);
    }
}

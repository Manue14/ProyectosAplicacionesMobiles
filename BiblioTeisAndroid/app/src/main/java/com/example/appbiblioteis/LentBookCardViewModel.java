package com.example.appbiblioteis;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.models.BookLending;
import com.example.appbiblioteis.API.models.User;
import com.example.appbiblioteis.API.repository.BookLendingRepository;
import com.example.appbiblioteis.API.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LentBookCardViewModel extends ViewModel {
    private User user;
    List<Book> lentBooks = new ArrayList<>();
    private MutableLiveData<List<Book>> libros = new MutableLiveData<>();

    public void setUser(User user) {
        this.user = user;
    }

    public MutableLiveData<List<Book>> getMutableLiveData() {
        return this.libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros.setValue(libros);
    }

    public List<Book> getLibros() {
        return this.libros.getValue();
    }

    public void loadLentBooks() {
        new BookLendingRepository().getAllLendings(new BookRepository.ApiCallback<List<BookLending>>() {
            @Override
            public void onSuccess(List<BookLending> result) {
                for (BookLending lending : result) {
                    if (lending.getUserId() == user.getId()) {
                        addBookToList(lending);
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Libros", "Error al préstamos: " + t.getMessage());
            }
        });
    }

    private void addBookToList(BookLending lending) {
        new BookRepository().getBookById(lending.getBookId(), new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                lentBooks.add(result);
                setLibros(lentBooks);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Libros", "Error al obtener libros prestados: " + t.getMessage());
            }
        });
    }
}

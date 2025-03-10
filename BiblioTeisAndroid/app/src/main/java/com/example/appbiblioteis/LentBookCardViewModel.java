package com.example.appbiblioteis;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.models.BookLending;
import com.example.appbiblioteis.API.models.User;
import com.example.appbiblioteis.API.repository.BookLendingRepository;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.API.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LentBookCardViewModel extends ViewModel {
    private int userId;
    List<Book> lentBooks = new ArrayList<>();
    private MutableLiveData<List<Book>> libros = new MutableLiveData<>();

    public void setUserId(int userId) {
        this.userId = userId;
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
        lentBooks.clear();
        Log.d("a", "heloooo");
        new UserRepository().getUserById(userId, new BookRepository.ApiCallback<User>() {
            @Override
            public void onSuccess(User result) {
                for (BookLending lending : result.getBookLendings()) {
                    if (lending.getReturnDate() == null) {
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

package com.example.appbiblioteis;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.models.User;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.API.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MainView extends AppCompatActivity  {
    private BookRepository bookRepository;
    private RecyclerView newBooksRecyclerView;
    private BookCardViewModel newBooksCardViewModel;
    List<Book> nuevosLibros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();

        newBooksRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,LinearLayoutManager.HORIZONTAL, false));

        bookRepository.getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {
                List<Book> nuevosLibros = new ArrayList<>();

                nuevosLibros.add(result.get(0));
                nuevosLibros.add(result.get(1));
                nuevosLibros.add(result.get(2));
                nuevosLibros.add(result.get(3));

                newBooksCardViewModel.setLibros(nuevosLibros);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Libros", "Error al cargar el RecylerView de nuevos libros: " + t.getMessage());
            }
        });

    }

    private void initialize() {
        this.bookRepository = new BookRepository();
        this.newBooksRecyclerView = findViewById(R.id.new_books_recycler_view);
        this.newBooksCardViewModel = new ViewModelProvider(this).get(BookCardViewModel.class);
        this.newBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            newBooksRecyclerView.setAdapter(new BookListAdapter(libros));
        });
    }
}

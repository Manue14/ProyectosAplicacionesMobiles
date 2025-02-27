package com.example.appbiblioteis;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbiblioteis.API.repository.BookRepository;

public class MainView extends AppCompatActivity  {
    private BookRepository bookRepository;
    private RecyclerView recommendedBooksRecyclerView;
    private RecyclerView newBooksRecyclerView;
    private RecyclerView availableBooksRecyclerView;
    private RecommendedBookCardViewModel recommendedBooksCardViewModel;
    private NewBookCardViewModel newBooksCardViewModel;
    private AvailableBookCardViewModel availableBooksCardViewModel;

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

        recommendedBooksRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,LinearLayoutManager.HORIZONTAL, false));

        newBooksRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,LinearLayoutManager.HORIZONTAL, false));

        availableBooksRecyclerView.setLayoutManager(new GridLayoutManager(
                this, 2));

        recommendedBooksCardViewModel.loadRecommendedBooks();
        newBooksCardViewModel.loadNewBooks();
        availableBooksCardViewModel.loadAvailableBooks();
    }

    private void initialize() {
        this.bookRepository = new BookRepository();

        this.recommendedBooksRecyclerView = findViewById(R.id.recommended_books_recycler_view);
        this.newBooksRecyclerView = findViewById(R.id.new_books_recycler_view);
        this.availableBooksRecyclerView = findViewById(R.id.available_books_recycler_view);

        this.recommendedBooksCardViewModel = new ViewModelProvider(this).get(RecommendedBookCardViewModel.class);
        this.newBooksCardViewModel = new ViewModelProvider(this).get(NewBookCardViewModel.class);
        this.availableBooksCardViewModel = new ViewModelProvider(this).get(AvailableBookCardViewModel.class);

        this.recommendedBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            recommendedBooksRecyclerView.setAdapter(new BookListAdapter(libros));
        });
        this.newBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            newBooksRecyclerView.setAdapter(new BookListAdapter(libros));
        });
        this.availableBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            availableBooksRecyclerView.setAdapter(new BookListAdapter(libros));
        });
    }
}

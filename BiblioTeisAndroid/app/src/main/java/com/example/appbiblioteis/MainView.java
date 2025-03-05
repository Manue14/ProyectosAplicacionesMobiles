package com.example.appbiblioteis;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuProvider;
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
    private ActivityResultLauncher<Intent> bookDetailViewActivityResultLauncher;
    private RecommendedBookCardViewModel recommendedBooksCardViewModel;
    private NewBookCardViewModel newBooksCardViewModel;
    private AvailableBookCardViewModel availableBooksCardViewModel;
    private EditText authorFilter;
    private EditText titleFilter;
    private CharSequence authorString = "";
    private CharSequence titleString = "";
    private ImageButton goBackButton;
    private ImageButton qrButton;
    private ImageButton userButton;

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

        authorFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authorString = s;
                availableBooksCardViewModel.filter(authorString, titleString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        titleFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleString = s;
                availableBooksCardViewModel.filter(authorString, titleString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Toolbar tb = findViewById(R.id.main_toolbar);
        setSupportActionBar(tb);
        initializeToolbar();

        addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                 /*
                if (id == R.id.op1) {
                    Toast.makeText(MainActivity.this, "Opción 1", Toast.LENGTH_SHORT).show();
                    return true;
                }
                */
                return false;
            }
        });
    }

    private void initialize() {
        this.bookRepository = new BookRepository();

        this.recommendedBooksRecyclerView = findViewById(R.id.recommended_books_recycler_view);
        this.newBooksRecyclerView = findViewById(R.id.new_books_recycler_view);
        this.availableBooksRecyclerView = findViewById(R.id.available_books_recycler_view);

        this.bookDetailViewActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    //Empty
                }
        );

        this.recommendedBooksCardViewModel = new ViewModelProvider(this).get(RecommendedBookCardViewModel.class);
        this.newBooksCardViewModel = new ViewModelProvider(this).get(NewBookCardViewModel.class);
        this.availableBooksCardViewModel = new ViewModelProvider(this).get(AvailableBookCardViewModel.class);

        this.recommendedBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            recommendedBooksRecyclerView.setAdapter(new BookListAdapter(libros, this.bookDetailViewActivityResultLauncher));
        });
        this.newBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            newBooksRecyclerView.setAdapter(new BookListAdapter(libros, this.bookDetailViewActivityResultLauncher));
        });
        this.availableBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            availableBooksRecyclerView.setAdapter(new BookListAdapter(libros, this.bookDetailViewActivityResultLauncher));
        });

        this.authorFilter = findViewById(R.id.author_filter);
        this.titleFilter = findViewById(R.id.title_filter);
    }

    private void initializeToolbar() {
        this.goBackButton = findViewById(R.id.go_back_button);
        this.qrButton = findViewById(R.id.qr_button);
        this.userButton = findViewById(R.id.user_button);

        ActivityResultLauncher<Intent> userViewActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    //Empty
                });
        Intent userIntent = new Intent(this, UserView.class);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewActivityResultLauncher.launch(userIntent);
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });
    }
}

package com.example.appbiblioteis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbiblioteis.API.models.User;
import com.example.appbiblioteis.services.Session;

public class UserView extends AppCompatActivity {
    private User user;
    private TextView userNameTxt;
    private TextView userEmailTxt;
    private TextView userDateTxt;
    private RecyclerView lentBooksRecyclerView;
    private LentBookCardViewModel lentBooksCardViewModel;
    private ActivityResultLauncher<Intent> bookDetailViewActivityResultLauncher;
    private ImageButton goBackButton;
    private ImageButton qrButton;
    private ImageButton userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();

        userNameTxt.setText(user.getName());
        userEmailTxt.setText(user.getEmail());
        userDateTxt.setText(user.getDateJoined().substring(0, 10));

        lentBooksRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        lentBooksCardViewModel.loadLentBooks();

        Toolbar tb = findViewById(R.id.main_toolbar);
        setSupportActionBar(tb);
        initializeToolbar();
    }

    private void initialize() {
        this.user = Session.getInstance().getUser();
        this.userNameTxt = findViewById(R.id.user_name_text);
        this.userEmailTxt = findViewById(R.id.user_email_text);
        this.userDateTxt = findViewById(R.id.user_date_text);
        this.lentBooksRecyclerView = findViewById(R.id.lent_books_recycler_view);

        this.bookDetailViewActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    //Empty
                }
        );
        this.lentBooksCardViewModel = new ViewModelProvider(this).get(LentBookCardViewModel.class);
        lentBooksCardViewModel.setUser(this.user);
        this.lentBooksCardViewModel.getMutableLiveData().observe(this, libros -> {
            lentBooksRecyclerView.setAdapter(new WideBookListAdapter(libros, this.bookDetailViewActivityResultLauncher));
        });
    }

    private void initializeToolbar() {
        this.goBackButton = findViewById(R.id.go_back_button);
        this.qrButton = findViewById(R.id.qr_button);
        this.userButton = findViewById(R.id.user_button);

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
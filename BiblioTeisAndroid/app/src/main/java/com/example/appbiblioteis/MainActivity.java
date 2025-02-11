package com.example.appbiblioteis;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.services.LoginService;

public class MainActivity extends AppCompatActivity {
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private LoginService loginService;
    private View.OnClickListener onLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();

        loginButton.setOnClickListener(onLogin);
        /*MainActivityVM vm = new ViewModelProvider(this).get(MainActivityVM.class);

        vm.book.observe(this, (Book book) -> {
            ((TextView)findViewById(R.id.name)).setText(book.getTitle());
            ((TextView)findViewById(R.id.Autor)).setText(book.getAuthor());
        });

        BookRepository br = new BookRepository();

        br.getBookById(1, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                vm.book.setValue(result);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("msg_bug", t.getMessage());
            }
        });*/

    }

    private void initialize() {
        this.emailInput = findViewById(R.id.email_input);
        this.passwordInput = findViewById(R.id.password_input);
        this.loginButton = findViewById(R.id.login_button);
        this.loginService = new LoginService();

        this.onLogin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                loginService.logIn(email, password);
            }
        };
    }
}
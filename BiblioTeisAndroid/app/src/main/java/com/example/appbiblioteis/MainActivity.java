package com.example.appbiblioteis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.services.LoginService;
import com.example.appbiblioteis.services.Session;

public class MainActivity extends AppCompatActivity {
    private Session session;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private LoginService loginService;
    private View.OnClickListener onLogin;
    private ActivityResultLauncher<Intent> mainViewResultLauncher;

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
    }

    private void initialize() {
        this.session = Session.getInstance();
        this.emailInput = findViewById(R.id.email_input);
        this.passwordInput = findViewById(R.id.password_input);
        this.loginButton = findViewById(R.id.login_button);
        this.loginService = new LoginService();

        this.mainViewResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Toast.makeText(this, result.getData().getStringExtra("test"), Toast.LENGTH_SHORT).show();
                });

        this.onLogin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                loginService.logIn(email, password);

                if (session.getUser() != null) {
                    Intent intent = new Intent(v.getContext(), MainView.class);
                    mainViewResultLauncher.launch(intent);
                }
            }
        };
    }
}
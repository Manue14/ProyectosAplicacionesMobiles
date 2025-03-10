package com.example.appbiblioteis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.models.BookLending;
import com.example.appbiblioteis.API.models.User;
import com.example.appbiblioteis.API.repository.BookLendingRepository;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.API.repository.ImageRepository;
import com.example.appbiblioteis.services.Session;

import okhttp3.ResponseBody;

public class BookDetailView extends AppCompatActivity {
    private Book libro;
    private int libro_id;
    private User user;
    private BookRepository bookRepository;
    private BookLendingRepository lendingRepository;
    private ImageRepository imageRepository;
    private Drawable placeholderImg;
    private TextView titleTxt;
    private TextView authorTxt;
    private ImageView imgView;
    private TextView isbnTxt;
    private TextView fechaTxt;
    private TextView copiasTotalesTxt;
    private TextView copiasDisponiblesTxt;
    private TextView fechaLiberacionTxt;
    private Button prestarBtn;
    private Button devolverBtn;
    private Button backBtn;
    private ImageButton goBackButton;
    private ImageButton qrButton;
    private ImageButton userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.book_detail_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
        initializeToolbar();

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(BookDetailView.RESULT_OK, intent);
            this.finish();
        });

        prestarBtn.setOnClickListener(view -> {
            lendBook();
        });

        devolverBtn.setOnClickListener(view -> {
            returnBook();
        });
    }

    private void initialize() {
        this.user = Session.getInstance().getUser();
        this.bookRepository = new BookRepository();
        this.lendingRepository = new BookLendingRepository();
        this.imageRepository = new ImageRepository();
        this.placeholderImg = ResourcesCompat.getDrawable(getResources(), R.drawable.book_cover_placeholder, null);

        this.titleTxt = findViewById(R.id.book_view_title_text);
        this.authorTxt = findViewById(R.id.book_view_autor_text);
        this.imgView = findViewById(R.id.book_view_img_view);
        this.isbnTxt = findViewById(R.id.book_view_isbn_text);
        this.fechaTxt = findViewById(R.id.book_view_fecha_text);
        this.copiasTotalesTxt = findViewById(R.id.book_view_copias_totales);
        this.copiasDisponiblesTxt = findViewById(R.id.book_view_copias_disponibles);
        this.fechaLiberacionTxt = findViewById(R.id.book_view_fecha_liberacion);
        this.prestarBtn = findViewById(R.id.book_view_prestar_btn);
        this.devolverBtn = findViewById(R.id.book_view_devolver_btn);
        this.backBtn = findViewById(R.id.book_view_return_btn);

        Intent intent = getIntent();
        this.libro_id = intent.getIntExtra("libro_id", 0);
        getBookFromAPI(libro_id);

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

    private void getBookFromAPI(int target_id) {
        bookRepository.getBookById(target_id, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                libro = result;
                loadBookData(result);

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(BookDetailView.this, "Error al obtener el libro con id: " + target_id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBookData(Book libro) {
        if (this.user == null) {
            this.user = Session.getInstance().getUser();
        }
        titleTxt.setText(libro.getTitle());
        authorTxt.setText(libro.getAuthor());
        isbnTxt.setText(libro.getIsbn());
        fechaTxt.setText(libro.getPublishedDate());

        if (libro != null && libro.getBookPicture() != null) {
            if (!libro.getBookPicture().isBlank()) {
                imageRepository.getImage(libro.getBookPicture(), new BookRepository.ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody result) {
                        Bitmap decodedByte = BitmapFactory.decodeStream(result.byteStream());

                        imgView.setImageBitmap(decodedByte);

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        imgView.setImageDrawable(placeholderImg);
                    }
                });
            }
        }

        updateLendingButtons(libro);
    }

    private void updateLendingButtons(Book libro) {
        if (libro.isAvailable()) {
            prestarBtn.setEnabled(true);
        } else {
            prestarBtn.setEnabled(false);
        }

        devolverBtn.setEnabled(false);
        for (BookLending lending : libro.getBookLendings()) {
            if (lending.getUserId() == user.getId() && lending.getReturnDate() == null) {
                devolverBtn.setEnabled(true);
            }
        }
    }

    private void lendBook() {
        lendingRepository.lendBook(libro.getId(), user.getId(), new BookRepository.ApiCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    Toast.makeText(BookDetailView.this, "Libro prestado con éxito", Toast.LENGTH_SHORT).show();
                    getBookFromAPI(libro_id);
                } else {
                    Toast.makeText(BookDetailView.this, "No se pudo prestar el libro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(BookDetailView.this, "No se pudo prestar el libro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void returnBook() {
        lendingRepository.returnBook(libro_id, new BookRepository.ApiCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    Toast.makeText(BookDetailView.this, "Libro devuelto con éxito", Toast.LENGTH_SHORT).show();
                    getBookFromAPI(libro_id);
                } else {
                    Toast.makeText(BookDetailView.this, "No se pudo devolver el libro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(BookDetailView.this, "No se pudo devolver el libro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
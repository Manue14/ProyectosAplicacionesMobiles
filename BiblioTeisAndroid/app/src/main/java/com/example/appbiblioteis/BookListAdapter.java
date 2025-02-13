package com.example.appbiblioteis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.API.repository.ImageRepository;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;

public class BookListAdapter extends RecyclerView.Adapter {
    private List<Book> libros;
    private ImageRepository imageRepository;

    public BookListAdapter(List<Book> libros){
        this.libros = libros;
        this.imageRepository = new ImageRepository();
    }

    @NonNull
    @Override
    public BookCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
        return new BookCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookCard bookCard = (BookCard) holder;
        Book libro = libros.get(position);

        bookCard.getBookTitleView().setText(libro.getTitle());
        if (!libro.getBookPicture().isBlank()) {
            imageRepository.getImage(libro.getBookPicture(), new BookRepository.ApiCallback<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody result) {
                    Bitmap decodedByte = BitmapFactory.decodeStream(result.byteStream());

                    bookCard.getBookImageView().setImageBitmap(decodedByte);

                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("Img", "Error al cargar la imagen del libro");
                }
            });



            bookCard.getBookImageView().setImageURI(Uri.parse(libro.getBookPicture()));
        }
        bookCard.getSeeBookButton().setOnClickListener(view -> {
            onClickSeeButton(libro, view);
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    private void onClickSeeButton(Book libro, View view) {
        Log.d("book-msg", "Seleccionado libro: " + libro.getTitle());
    }
}

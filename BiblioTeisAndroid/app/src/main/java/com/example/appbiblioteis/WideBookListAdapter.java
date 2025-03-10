package com.example.appbiblioteis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbiblioteis.API.models.Book;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.API.repository.ImageRepository;

import java.util.List;

import okhttp3.ResponseBody;

public class WideBookListAdapter extends RecyclerView.Adapter {
    private List<Book> libros;
    private ImageRepository imageRepository;
    private Drawable placeholderImg;
    private ActivityResultLauncher<Intent> bookDetailViewActivityResultLauncher;

    public WideBookListAdapter(List<Book> libros, ActivityResultLauncher<Intent> bookDetailViewActivityResultLauncher){
        this.libros = libros;
        this.bookDetailViewActivityResultLauncher = bookDetailViewActivityResultLauncher;
        this.imageRepository = new ImageRepository();
    }

    @NonNull
    @Override
    public WideBookCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wide_book_card, parent, false);
        this.placeholderImg = ResourcesCompat.getDrawable(parent.getResources(), R.drawable.book_cover_placeholder, null);
        return new WideBookCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WideBookCard bookCard = (WideBookCard) holder;
        Book libro = libros.get(position);

        bookCard.getBookTitleView().setText(libro.getTitle());
        bookCard.getAuthorView().setText(libro.getAuthor());

        bookCard.getBookImageView().setImageDrawable(placeholderImg);
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
        Intent intent = new Intent(view.getContext(), BookDetailView.class);
        intent.putExtra("libro_id", libro.getId());
        bookDetailViewActivityResultLauncher.launch(intent);
    }
}

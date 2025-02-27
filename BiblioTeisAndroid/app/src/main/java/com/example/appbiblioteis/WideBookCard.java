package com.example.appbiblioteis;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class WideBookCard  extends RecyclerView.ViewHolder {
    private TextView bookTitleView;
    private TextView authorView;
    private ImageView bookImageView;
    private Button seeBookButton;

    public WideBookCard(View view) {
        super(view);

        this.bookTitleView = view.findViewById(R.id.book_card_title_text);
        this.authorView = view.findViewById(R.id.book_card_author_text);
        this.bookImageView = view.findViewById(R.id.book_card_img_view);
        this.seeBookButton = view.findViewById(R.id.see_book_button);
    }

    public TextView getBookTitleView() {
        return bookTitleView;
    }

    public void setBookTitleView(TextView bookTitleView) {
        this.bookTitleView = bookTitleView;
    }

    public TextView getAuthorView() {
        return authorView;
    }

    public void setAuthorView(TextView authorView) {
        this.authorView = authorView;
    }

    public ImageView getBookImageView() {
        return bookImageView;
    }

    public void setBookImageView(ImageView bookImageView) {
        this.bookImageView = bookImageView;
    }

    public Button getSeeBookButton() {
        return seeBookButton;
    }

    public void setSeeBookButton(Button seeBookButton) {
        this.seeBookButton = seeBookButton;
    }
}
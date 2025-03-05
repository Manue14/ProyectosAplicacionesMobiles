package com.example.appbiblioteis.API.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Book implements Parcelable {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String publishedDate;
    private String bookPicture;
    private boolean isAvailable;
    private List<BookLending> bookLendings;

    public Book() {}

    public Book(String title, String author, String isbn, String publishedDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.isAvailable = true;
    }

    private Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        isbn = in.readString();
        publishedDate = in.readString();
        bookPicture = in.readString();
        isAvailable = in.readBoolean();
        //in.readList(bookLendings, BookLending.class.getClassLoader(), BookLending.class);

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(title);
        out.writeString(author);
        out.writeString(isbn);
        out.writeString(publishedDate);
        out.writeString(bookPicture);
        out.writeBoolean(isAvailable);
        //out.writeList(bookLendings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[0];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<BookLending> getBookLendings() {
        return bookLendings;
    }

    public void setBookLendings(List<BookLending> bookLendings) {
        this.bookLendings = bookLendings;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }
}

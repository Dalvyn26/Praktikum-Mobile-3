package com.example.tp3;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title;
    private String author;
    private int year;
    private String blurb;
    private String imagePath;
    private boolean isLiked;
    private float rating;
    private String genre;

    public Book(String title, String author, int year, String blurb, String imagePath, boolean isLiked, float rating, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.imagePath = imagePath;
        this.isLiked = isLiked;
        this.rating = rating;
        this.genre = genre;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        year = in.readInt();
        blurb = in.readString();
        imagePath = in.readString();
        isLiked = in.readByte() != 0;
        rating = in.readFloat();
        genre = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getBlurb() { return blurb; }
    public void setBlurb(String blurb) { this.blurb = blurb; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(year);
        dest.writeString(blurb);
        dest.writeString(imagePath);
        dest.writeByte((byte) (isLiked ? 1 : 0));
        dest.writeFloat(rating);
        dest.writeString(genre);
    }
}

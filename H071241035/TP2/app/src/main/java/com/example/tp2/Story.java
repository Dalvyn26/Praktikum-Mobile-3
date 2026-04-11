package com.example.tp2;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {
    private User user;
    private int imageResource;
    private String title;

    public Story(User user, int imageResource, String title) {
        this.user = user;
        this.imageResource = imageResource;
        this.title = title;
    }

    protected Story(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        imageResource = in.readInt();
        title = in.readString();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public User getUser() { return user; }
    public int getImageResource() { return imageResource; }
    public String getTitle() { return title; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeInt(imageResource);
        dest.writeString(title);
    }
}

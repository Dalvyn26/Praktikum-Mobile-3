package com.example.tp2;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private User user;
    private int imageResource;
    private String imageUri; // For gallery images
    private String caption;

    public Post(User user, int imageResource, String caption) {
        this.user = user;
        this.imageResource = imageResource;
        this.caption = caption;
        this.imageUri = null;
    }

    public Post(User user, String imageUri, String caption) {
        this.user = user;
        this.imageUri = imageUri;
        this.caption = caption;
        this.imageResource = 0;
    }

    protected Post(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        imageResource = in.readInt();
        imageUri = in.readString();
        caption = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public User getUser() { return user; }
    public int getImageResource() { return imageResource; }
    public String getImageUri() { return imageUri; }
    public String getCaption() { return caption; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeInt(imageResource);
        dest.writeString(imageUri);
        dest.writeString(caption);
    }
}

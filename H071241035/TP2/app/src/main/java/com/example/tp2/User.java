package com.example.tp2;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String fullName;
    private int profileImage;

    public User(String username, String fullName, int profileImage) {
        this.username = username;
        this.fullName = fullName;
        this.profileImage = profileImage;
    }

    protected User(Parcel in) {
        username = in.readString();
        fullName = in.readString();
        profileImage = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public int getProfileImage() { return profileImage; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(fullName);
        dest.writeInt(profileImage);
    }
}

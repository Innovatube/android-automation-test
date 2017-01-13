package com.example.espresso.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by quanlt on 10/01/2017.
 */

public class Character implements Parcelable {
    private String name;
    private String role;
    private String portrait;

    public Character() {
    }

    public Character(String name, String role, String portrait) {
        this.name = name;
        this.role = role;
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.role);
        dest.writeString(this.portrait);
    }

    protected Character(Parcel in) {
        this.name = in.readString();
        this.role = in.readString();
        this.portrait = in.readString();
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}

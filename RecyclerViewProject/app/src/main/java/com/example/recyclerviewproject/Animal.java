package com.example.recyclerviewproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    private String name;
    private String text;
    private int img_resource_id;
    private boolean isRead;

    public Animal() {}

    public Animal(String name, String text, int img_resource_id) {
        this.name = name;
        this.text = text;
        this.img_resource_id = img_resource_id;
        this.isRead = false;
    }

    private Animal(Parcel in) {
        name = in.readString();
        text = in.readString();
        img_resource_id = in.readInt();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg_resource_id() {
        return img_resource_id;
    }

    public void setImg_resource_id(int img_resource_id) {
        this.img_resource_id = img_resource_id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(text);
        out.writeInt(img_resource_id);
    }

    public static final Parcelable.Creator<Animal> CREATOR = new Parcelable.Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel source) {
            return new Animal(source);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[0];
        }
    };
}
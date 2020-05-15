package com.example.api_consumer;

import android.graphics.Bitmap;

public class Pokemon {

    private String name;

    private Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

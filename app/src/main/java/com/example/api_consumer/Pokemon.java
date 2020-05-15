package com.example.api_consumer;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private String name;

    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

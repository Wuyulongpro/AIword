package com.example.aiword;

/**
 * Created by Longge on 2018/12/31.
 */

public class DrawerContent {
    private int imageView;
    private String text;
    private int id;

    public DrawerContent() {
    }

    public DrawerContent(int imageView, String text, int id) {
        this.imageView = imageView;
        this.text = text;
        this.id = id;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

package com.example.apnaniwas.ui.home.homepost;

import android.graphics.drawable.Drawable;

public class HomePostModel {

    private int postImage;
    private Drawable postImg;

    private String postTitle;
    private String postSummary;
    public HomePostModel(int postImage, String postTitle, String postSummary) {
        this.postImage=postImage;
        this.postTitle = postTitle;
        this.postSummary = postSummary;
    }

    public HomePostModel(Drawable postImg, String postTitle, String postSummary) {
        this.postImg=postImg;
        this.postTitle = postTitle;
        this.postSummary = postSummary;
    }
    public String getPostTitle() {
        return postTitle;
    }
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    public String getPostSummary() {
        return postSummary;
    }
    public void setPostSummary(String postSummary) {
        this.postSummary = postSummary;
    }

    public Drawable getPostImg() {
        return postImg;
    }

    public void setPostImg(Drawable postImg) {
        this.postImg = postImg;
    }

    public int getPostImage() {
        return postImage;
    }
    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }
}

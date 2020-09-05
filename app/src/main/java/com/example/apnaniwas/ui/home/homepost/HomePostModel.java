package com.example.apnaniwas.ui.home.homepost;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.target.CustomTarget;

public class HomePostModel {

    private  String post_mem_id;
    private int postGradientImage;
    private Drawable postImg;
    private Bitmap BitImg;
    private String postId;
    private String postTitle;
    private String postSummary;
    CustomTarget<Bitmap> img;
    private String imgURL;

    public HomePostModel(int postImage, String postTitle, String postSummary) {
        this.postGradientImage =postImage;
        this.postTitle = postTitle;
        this.postSummary = postSummary;
    }

/*    public HomePostModel(Drawable postImg, String postTitle, String postSummary) {
        this.postImg=postImg;
        this.postTitle = postTitle;
        this.postSummary = postSummary;

    }*/

/*
    public HomePostModel(int gradient_background, Bitmap bitmap, String post_title, String post_desc) {
        this.postImage=gradient_background;
        this.BitImg=bitmap;
        this.postTitle = post_title;
        this.postSummary = post_desc;
    }
*/

    public HomePostModel(int gradient_background, CustomTarget<Bitmap> img, String imgURL,String member_id,String postId,String post_title, String post_desc) {
        this.postGradientImage =gradient_background;
        this.img=img;
        this.postTitle = post_title;
        this.postSummary = post_desc;
        this.imgURL= imgURL;
        this.postId=postId;
        this.post_mem_id = member_id;
    }

    public String getPostTitle() {
        return postTitle;
    }
    public String getPostSummary() {
        return postSummary;
    }
    public Bitmap getPostImgBitmap() {
        return BitImg;
    }
    public int getPostGradientImage() {
        return postGradientImage;
    }
    public String getImgURL() {
        return imgURL;
    }
    public String getPostId() {
        return postId;
    }
    public String getPostMemId() {
        return post_mem_id;
    }
    public void setPostGradientImage(int postGradientImage) {
        this.postGradientImage = postGradientImage;
    }
    public Drawable getPostImg() {
        return postImg;
    }
    public void setPostSummary(String postSummary) {
        this.postSummary = postSummary;
    }
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    public void setPostImg(Drawable postImg) {
        this.postImg = postImg;
    }
}

package com.example.apnaniwas.ui.yourpost;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourPostViewModel implements Parcelable {

    private String imgTitle;
    private String imgUrl;

    public YourPostViewModel() {
    }

    protected YourPostViewModel(Parcel in){
        this.imgTitle = in.readString();
        this.imgUrl = in.readString();

    }

    public static final Creator<YourPostViewModel> CREATOR = new Creator<YourPostViewModel>() {
        @Override
        public YourPostViewModel createFromParcel(Parcel in) {
            return new YourPostViewModel(in);
        }

        @Override
        public YourPostViewModel[] newArray(int size) {
            return new YourPostViewModel[size];
        }
    };
    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgTitle);
        dest.writeString(imgUrl);
    }

}
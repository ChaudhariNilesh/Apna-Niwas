package com.example.apnaniwas.ui.services;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServicesViewModel {
    private String mServiceName;
    private Integer mServiceImg;
    private Integer mServiceNum;
    private Integer mID;

    ServicesViewModel(String mServiceName, Integer mServiceNum, Integer mServiceImg, Integer id) {
        this.mServiceName = mServiceName;
        this.mServiceImg = mServiceImg;
        this.mServiceNum = mServiceNum;
        this.mID = id;
    }

    String getmServiceName() {
        return mServiceName;
    }

    public void setmServiceName(String mServiceName) {
        this.mServiceName = mServiceName;
    }

    Integer getmServiceImg() {
        return mServiceImg;
    }

    public void setmServiceImg(Integer mServiceImg) {
        this.mServiceImg = mServiceImg;
    }

    public Integer getmServiceNum() {
        return mServiceNum;
    }

    public void setmServiceNum(Integer mServiceNum) {
        this.mServiceNum = mServiceNum;
    }
}
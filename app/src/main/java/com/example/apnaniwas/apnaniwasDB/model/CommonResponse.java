package com.example.apnaniwas.apnaniwasDB.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("messsage")
    @Expose
    private String messsage;

    CommonResponse(int status)
    {
        this.status  = status;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}

package com.example.apnaniwas.apnaniwasDB.model.forgotpasswordresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {

    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("member_id")
    @Expose
    private int memberId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


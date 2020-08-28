package com.example.apnaniwas.apnaniwasDB.model.signupresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignResponse {
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("member_password")
    @Expose
    private String memberPassword;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("messsage")
    @Expose
    private String messsage;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
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

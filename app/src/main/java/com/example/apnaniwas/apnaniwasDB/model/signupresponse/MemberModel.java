package com.example.apnaniwas.apnaniwasDB.model.signupresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberModel {

        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("email_id")
        @Expose
        private String emailId;
        @SerializedName("member_id")
        @Expose
        private String memberId;
        @SerializedName("member_password")
        @Expose
        private String memberPassword;



    public MemberModel(String mem_name, String mem_mob, String mem_email, String mem_pswd, String memberId) {
        this.memberName = mem_name;
        this.phoneNo = mem_mob;
        this.emailId = mem_email;
        this.memberPassword = mem_pswd;
        this.memberId = memberId;

    }

    public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
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

        public String getMemberPassword() {
            return memberPassword;
        }

        public void setMemberPassword(String memberPassword) {
            this.memberPassword = memberPassword;
        }
}

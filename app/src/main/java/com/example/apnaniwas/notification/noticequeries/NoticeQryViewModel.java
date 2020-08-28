package com.example.apnaniwas.notification.noticequeries;

public class NoticeQryViewModel {
    String userName,qryTime,userQry;
    int usrProfileImage;


    public NoticeQryViewModel(String textQry,
                              String userName, String qryTime, int usrProfileImage) {
        this.usrProfileImage=usrProfileImage;
        this.userQry=textQry;
        this.userName=userName;
        this.qryTime=qryTime;
    }
    public  int getUsrProfileImage()
    {
        return usrProfileImage;
    }
    public  String getQry(){ return userQry; }
    public  String getUserName() { return userName; }
    public  String getQryTime()
    {
        return qryTime;
    }

//    public void setUsrProfileImage(int usrProfileImage) {
//        this.usrProfileImage = usrProfileImage;
//    }
//
//    public void setQry(String userQry) {
//        this.userQry = userQry;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setQryTime(String qryTime) {
//        this.qryTime = qryTime;
//    }
}

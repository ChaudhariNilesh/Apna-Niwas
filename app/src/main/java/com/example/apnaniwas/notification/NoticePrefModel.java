package com.example.apnaniwas.notification;

import com.example.apnaniwas.util.NoticePref;

import java.util.Comparator;

public class NoticePrefModel {

/*    public NoticePrefModel(String noticeJsonData, String noticeKey) {
        this.noticeJsonData = noticeJsonData;
        this.noticeKey = noticeKey;
    }*/

    String noticeJsonData;
    String noticeKey;
    int imgInfo;
    int imgNoticeQry;
    int ImgDel;

    public int getImgDel() {
        return ImgDel;
    }

    public void setImgDel(int imgDel) {
        ImgDel = imgDel;
    }

    public int getImgInfo() {
        return imgInfo;
    }

    public void setImgInfo(int imgInfo) {
        this.imgInfo = imgInfo;
    }

    public int getImgNoticeQry() {
        return imgNoticeQry;
    }

    public void setImgNoticeQry(int imgNoticeQry) {
        this.imgNoticeQry = imgNoticeQry;
    }

    public String getnoticeJsonData() {
        return noticeJsonData;
    }

    public void setnoticeJsonData(String noticeJsonData) {
        this.noticeJsonData = noticeJsonData;
    }

    public String getnoticeKey() {
        return noticeKey;
    }
    public void setnoticeKey(String noticeKey) {
        this.noticeKey = noticeKey;
    }

/*
    @Override
    public int compareTo(Object obj) {

        String compareNoticeKey=((NoticePrefModel)obj).getnoticeKey();

        int compare = this.noticeKey.compareTo(compareNoticeKey);
        if(compare > 0){

        }
        int icompareNoticeKey = Integer.parseInt(String.valueOf(compareNoticeKey));
        int inoticeKey = Integer.parseInt((String)this.noticeKey);
        return inoticeKey-icompareNoticeKey;
    }
*/

    public static Comparator<NoticePrefModel> noticeUUIDcmp = new Comparator<NoticePrefModel>() {

        @Override
        public int compare(NoticePrefModel notice1, NoticePrefModel notice2) {
            String notice_uuid1 = notice1.getnoticeKey();
            String notice_uuid2 = notice2.getnoticeKey();

            return notice_uuid2.compareTo(notice_uuid1);

        }};

}

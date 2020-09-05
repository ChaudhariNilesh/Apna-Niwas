package com.example.apnaniwas.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.apnaniwas.R;
import com.example.apnaniwas.notification.NoticePrefModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class NoticePref {
    public static final String SHARED_PREF_NAME = "NOTICE_PREF";
    public static  String  NOTICE_ID;
    public static  String  NOTICE_COUNT;
    public int cnt;
    @SuppressLint("StaticFieldLeak")
    private static NoticePref mInstance;

    private final Context mCtx;
    private String title;
    private String message;
    public static String NOTICE_TS;

    private NoticePref(Context context) {
        mCtx = context;
    }

    public static synchronized NoticePref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NoticePref(context);
        }
        return mInstance;
    }

    public void addNoticePref(JSONObject jsonObject,String timestamp){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        NOTICE_TS = timestamp;
        editor.putString(NOTICE_TS, String.valueOf(jsonObject));
        editor.apply();
        editor.commit();

    }

    public ArrayList<NoticePrefModel> getNoticePref(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        Map<String, ?> allEntries = sharedPreferences.getAll();

        ArrayList<NoticePrefModel> noticePrefList = new ArrayList<NoticePrefModel>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            NoticePrefModel prefData = new NoticePrefModel();
            prefData.setnoticeKey(entry.getKey());
            prefData.setnoticeJsonData(entry.getValue().toString());
            prefData.setImgDel(R.drawable.ic_delete);
            prefData.setImgInfo(R.drawable.ic_info);
           // prefData.setImgNoticeQry(R.drawable.ic_notice_qry);
            noticePrefList.add(prefData);
        }
        return noticePrefList;
    }

    public void removeNoticePref(String noticeId){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(noticeId));
        editor.apply();
    }
}

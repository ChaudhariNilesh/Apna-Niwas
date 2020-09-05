package com.example.apnaniwas.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class FirebaseTokenPref {
    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String TAG_TOKEN = "tagtoken";

    private static FirebaseTokenPref mInstance;
    private static Context mCtx;

    private FirebaseTokenPref(Context context) {
        mCtx = context;
    }

    public static synchronized FirebaseTokenPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FirebaseTokenPref(context);
        }
        return mInstance;
    }

    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }

}

package com.example.apnaniwas.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.apnaniwas.apnaniwasDB.model.loginresponse.LoginResponse;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.SignResponse;
import com.example.apnaniwas.login.Login;

import java.util.UUID;


public class SharedPreference {

    private static final String SHARED_PREF_NAME = "ApnaNiwasMemberRegister";
    private static final String KEY_USERNAME = "keymembername";
    private static final String KEY_MOBILE = "keymobile";
    private static final String KEY_EMAIL = "keygender";
    private static final String KEY_PASSWORD = "keypswd";
    private static final String KEY_MEMBER_ID = "keymemberid";
    private static final String UUID_KEY = "UUIDKey";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String SHARED_PREF_FIRST_TIME = "FirstLaunch";

    private static SharedPreference mInstance;

    private static Context context;
    private SharedPreference(Context context)
    {
       this.context = context;
    }

    public static synchronized SharedPreference getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreference(context);
        }
        return mInstance;
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FIRST_TIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FIRST_TIME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

    public void userRegister(SignResponse signResponse) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MEMBER_ID,signResponse.getMemberId());
        editor.putString(UUID_KEY,UUID.randomUUID().toString());
        editor.putString(KEY_USERNAME, signResponse.getMemberName());
        editor.putString(KEY_MOBILE, signResponse.getPhoneNo());
        editor.putString(KEY_EMAIL, signResponse.getEmailId());
        editor.putString(KEY_PASSWORD, signResponse.getMemberPassword());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UUID_KEY, null) != null;
    }

    public MemberModel getMember() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new MemberModel(
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_MOBILE, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_MEMBER_ID, null)

        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, Login.class));
    }


    public void userLogin(LoginResponse loginResponse) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MEMBER_ID,loginResponse.getMemberId());
        editor.putString(UUID_KEY,UUID.randomUUID().toString());
        editor.putString(KEY_USERNAME, loginResponse.getMemberName());
        editor.putString(KEY_MOBILE, loginResponse.getPhoneNo());
        editor.putString(KEY_EMAIL, loginResponse.getEmailId());
        editor.putString(KEY_PASSWORD, loginResponse.getMemberPassword());
        editor.apply();
    }
}

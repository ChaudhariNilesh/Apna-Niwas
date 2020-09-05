package com.example.apnaniwas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.notification.FCM.Config;
import com.example.apnaniwas.notification.FCM.TempLayout;
import com.example.apnaniwas.notification.Notification;
import com.example.apnaniwas.notification.NotificationBoard;
import com.example.apnaniwas.util.SharedPreference;
import com.example.apnaniwas.login.Login;
import com.example.apnaniwas.tmp.TmpProfile;
import com.example.apnaniwas.signup.MobileRegistration;

import javax.net.ssl.KeyManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (!SharedPreference.getInstance(this).isFirstTimeLaunch()) {
                startActivity(new Intent(this, MobileRegistration.class));
                finish();
            }
            else if (!SharedPreference.getInstance(this).isLoggedIn()) {
                    Intent i = new Intent(SplashActivity.this, Login.class);
                    startActivity(i);
                    finish();
            }
            else if (getIntent().hasExtra(Config.PUSH_NOTIFICATION)) {
                    Intent intent = new Intent(this, NotificationBoard.class);
                    startActivity(intent);
                    finish();
            }
            else{
                    startActivity(new Intent(this, BottomNavigationBar.class));
                    finish();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
                    Intent intent = new Intent(this, BottomNavigationBar.class);
                    startActivity(intent);
                    finish();
*/

    //CheckLogin();


    //usefull for login check and delay
/*
    private void CheckLogin() {
        if (KeyManager.getSharedPreferenceBoolean(SplashActivity.this, "isLoggedIn", false)) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 2500);
        }
    }
*/

}

package com.example.apnaniwas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.util.SharedPreference;
import com.example.apnaniwas.login.Login;
import com.example.apnaniwas.tmp.TmpProfile;
import com.example.apnaniwas.signup.MobileRegistration;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (!SharedPreference.getInstance(this).isFirstTimeLaunch()) {
            startActivity(new Intent(this, MobileRegistration.class));
            finish();
        }

       else if (!SharedPreference.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, Login.class));
            finish();
        }
        else
        {
            startActivity(new Intent(this, BottomNavigationBar.class));
            finish();
        }
    }
}

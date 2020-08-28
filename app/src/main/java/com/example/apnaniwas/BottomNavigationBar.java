package com.example.apnaniwas;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class BottomNavigationBar extends AppCompatActivity {
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        navView = findViewById(R.id.nav_view);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PERMISSION_GRANTED){
            String[] permission = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this,permission,1);
        }
       // BadgeDrawable badge = navView.getOrCreateBadge(R.id.navigation_payment);

/*
        if(!TextUtils.isEmpty(SharedPref.getApiKey(this)))
        {
            Toast.makeText(this,"Aleardy Logged In",Toast.LENGTH_SHORT).show();
        }
        else
        {
            finish();
            startActivity(new Intent(this, Login.class));
        }
*/

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

/*        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_payment)
                {
                    navView.removeBadge(R.id.navigation_payment);


                }
                return false;
            }
        });*/








    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PERMISSION_GRANTED)
        {

        }
        else
        {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}


package com.example.apnaniwas;

import android.os.Bundle;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class BottomNavigationBar extends AppCompatActivity {
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        navView = findViewById(R.id.nav_view);

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
}


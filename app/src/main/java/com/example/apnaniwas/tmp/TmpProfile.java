package com.example.apnaniwas.tmp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.util.SharedPreference;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;
import com.example.apnaniwas.R;
import com.google.firebase.auth.FirebaseAuth;

public class TmpProfile  extends AppCompatActivity {

    TextView textViewId, textViewUsername, textViewEmail, textViewGender,textViewPswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmp_profile);

/*        if(!TextUtils.isEmpty(SharedPref.getApiKey(this)))
        {
            Toast.makeText(this,"Aleardy Logged In",Toast.LENGTH_SHORT).show();

            // startActivity(new Intent(this,Login.class));
        }
        else
        {
            finish();
            startActivity(new Intent(this, Login.class));
        }*/

        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewGender = findViewById(R.id.textViewGender);
        textViewPswd = findViewById(R.id.textViewPswd);
        textViewId = findViewById(R.id.textViewId);


        //getting the current user
        MemberModel user = SharedPreference.getInstance(this).getMember();

        //setting the values to the textviews
       // textViewId.setText(String.valueOf(user.getMemberId()));
        textViewUsername.setText(user.getMemberName());
        textViewEmail.setText(user.getEmailId());
        textViewGender.setText(user.getPhoneNo());
        textViewPswd.setText(user.getMemberPassword());
        textViewId.setText(user.getMemberId());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener((View view) -> {
            finish();
            SharedPreference.getInstance(getApplicationContext()).logout();
            FirebaseAuth.getInstance().signOut();

        });
    }
}

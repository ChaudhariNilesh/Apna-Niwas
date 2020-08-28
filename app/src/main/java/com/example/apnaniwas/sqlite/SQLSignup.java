/*
package com.example.apnaniwas.SQLite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;


import java.util.List;

public class SQLSignup extends AppCompatActivity {

    EditText name, email, password, phoneno, uid;

    public static final String Logindata = "data";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String Phoneno = "phnoKey";
    public static final String Userid = "idKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }

    public void register(View view) {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.newUsrpassword);
        phoneno = findViewById(R.id.phoneno);

        String Usrname = name.getText().toString();
        String Usrpswd = password.getText().toString();
        String Usremail = email.getText().toString();
        String Usrmob = phoneno.getText().toString();

        DbHandler dbHandler = new DbHandler(SQLSignup.this);
        dbHandler.insertUserDetails(new UserDetailsModel(Usrname,Usrpswd,Usremail,Usrmob));
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();

        List<UserDetailsModel> UserList = dbHandler.GetUsers();

        for (UserDetailsModel user : UserList){
            String log = "Id: " + user.getId() + " ,Name: " + user.getUsrname() + " ,Password: " + user.getUsrpswd() + " ,email : " + user.getUsremail() + " ,mobile" + user.getUsrmob();
            Log.d("USERS ", log);
        }
        Intent intent = new Intent(SQLSignup.this, SQLiteDB.class);
        startActivity(intent);
    }

}

        // SHARED PREFERENCE CODE

       */
/* name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        phoneno=(EditText)findViewById(R.id.phoneno);
        uid=(EditText)findViewById(R.id.userid);
        go=(Button) findViewById(R.id.loginbutton);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences=getSharedPreferences(Logindata, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=sharedPreferences.edit();
                String username=name.getText().toString();
                String useremail=email.getText().toString();
                String userphoneno=phoneno.getText().toString();
                String userid=uid.getText().toString();
                String userpassword=password.getText().toString();
                edit.putString(Name,username);
                edit.putString(Email,useremail);
                edit.putString(Phoneno,userphoneno);
                edit.putString(Userid,userid);
                edit.putString(Password,userpassword);
                edit.commit();
            }
        });*/

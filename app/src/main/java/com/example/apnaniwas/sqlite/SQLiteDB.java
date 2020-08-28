/*
package com.example.apnaniwas.SQLite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;

public class SQLiteDB extends AppCompatActivity {


/// CODE FOR SQLITE DATABASE

    EditText name,email,password,phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_sqlite);
        loadListViewLayout();
    }

    public void loadListViewLayout(){
        ListView listView;
        AdapterUserList adapter;
        listView = findViewById(R.id.user_list);
        DbHandler dbHandler = new DbHandler(SQLiteDB.this);

        adapter = new AdapterUserList( this, R.layout.list_row_sql_design,dbHandler.GetUsers());
        listView.setAdapter(adapter);
    }

  public void btnEditUser(View view) {

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.newUsrpassword);
        phoneno = findViewById(R.id.phoneno);

        String Usrname = name.getText().toString();
        String Usrpswd = password.getText().toString();
        String Usremail = email.getText().toString();
        String Usrmob = phoneno.getText().toString();

        DbHandler db = new DbHandler(SQLiteDB.this);
        db.updateUsersDetails(new UserDetailsModel(Usrname,Usrpswd,Usremail,Usrmob));

    }

    public void btnDelUser(View view) {
        DbHandler db = new DbHandler(SQLiteDB.this);
        db.deleteUserDetails(new UserDetailsModel());
    }

}
*/

package com.example.apnaniwas.notification.noticequeries;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apnaniwas.R;

import java.util.ArrayList;
import java.util.Objects;


public class NoticeQueries extends AppCompatActivity {

    ListView listView;
    NoticeQueryAdapter adapter;
    EditText mQry;
    TextView clkAsk;
    ArrayList<NoticeQryViewModel> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity_notice_query);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notice Queries");

        listView= findViewById(R.id.list);
        itemList = new ArrayList<NoticeQryViewModel>();

        mQry = findViewById(R.id.et_query);
        clkAsk = findViewById(R.id.PostQry);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
        public void NoticeQueries(View view) {
             String query = mQry.getText().toString();
             mQry.getText().clear();
             if(!query.isEmpty())
             {


                 itemList.add(new NoticeQryViewModel(query, "Nilesh Chaudhary", "Now",R.drawable.exampleperson));
                 adapter=new NoticeQueryAdapter(this,R.layout.layout_notice_query,itemList);
                 listView.setAdapter(adapter);
             }
             else
             {
                Toast.makeText(getApplication(),"Can't Post a blank comment",Toast.LENGTH_SHORT).show();
             }

   }

}




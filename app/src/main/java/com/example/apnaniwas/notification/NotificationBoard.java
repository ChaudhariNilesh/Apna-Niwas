package com.example.apnaniwas.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.notification.noticequeries.NoticeQueries;
import com.example.apnaniwas.R;

import java.util.ArrayList;


public class NotificationBoard extends AppCompatActivity {
private static RecyclerView.Adapter adapter;
private RecyclerView.LayoutManager layoutManager;
private  static RecyclerView recyclerView;
private static ArrayList<NotificationViewModel> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        data=new ArrayList<NotificationViewModel>();
        for(int i =0; i<notices.id.length;i++)
        {
            data.add(new NotificationViewModel(
                    notices.subjectArray[i],
                    notices.postedByArray[i],
                    notices.id[i],
                    notices.drawableInfo,
                    notices.drawableNoticeQry
            ));
        }
        adapter=new NotificationBoardAdapter(data);
        recyclerView.setAdapter(adapter);
    }

/*    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

    //    @Override
    //    public void onClick(View v) {
    //        removeItem(v);
        } // <<*/

    public void notification(View view) {
        Intent intent = new Intent(this, NoticeQueries.class);
        startActivity(intent);
/*        if(view.getId() ==  R.id.ib_notice_details)
        {
            loadInfoAlertBox();
        }
       if (view.getId() == R.id.ib_NoticeQry) {

        }*/
    }



    public static class notices{
        static String[] subjectArray={"Meeting","Celebration","Fund","Water Supply","Event"};
        static String[] postedByArray={"Short Description","Short Description","Short Description","Short Description","Short Description"};
        static Integer drawableNoticeQry =R.drawable.ic_notice_qry;
        static Integer drawableInfo=R.drawable.ic_info;
        static Integer[] id ={0,1,2,3,4};
    }

}


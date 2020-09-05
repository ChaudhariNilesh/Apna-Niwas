package com.example.apnaniwas.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaniwas.notification.FCM.Config;
import com.example.apnaniwas.notification.FCM.MyFirebaseMessagingService;
import com.example.apnaniwas.R;
import com.example.apnaniwas.util.NoticePref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.apnaniwas.ui.home.HomeFragment.setupBadge;


public class NotificationBoard extends AppCompatActivity {

    public static View.OnClickListener noticeDelClickListener;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static ArrayList<NoticePrefModel> noticePrefArray = new ArrayList<NoticePrefModel>();
    private ImageButton imgBtnNoticeDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);

        setupBadge(0); //set notification badge
        populateNoticeData(); // get notice data
        noticeBroadcastHandler();
    }



    public void removeItem(int position) {
        noticePrefArray.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private void noticeBroadcastHandler() {
        mRegistrationBroadcastReceiver =
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                            try {
                                String noticeData = intent.getStringExtra("data");
                                assert noticeData != null;
                                JSONObject obj = new JSONObject(noticeData);
                                populateNoticeData();
                                Toast.makeText(getApplicationContext(), "New notification is about " + obj.getString("title"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No New Notification", Toast.LENGTH_LONG).show();
                        }
                    }
                };

    }

    private void populateNoticeData() {
        try {
            noticePrefArray = NoticePref.getInstance(this).getNoticePref();
            Collections.sort(noticePrefArray, NoticePrefModel.noticeUUIDcmp);
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new NotificationBoardAdapter(noticePrefArray, getApplicationContext());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
        MyFirebaseMessagingService.clearNotifications(getApplicationContext());
        populateNoticeData();
    }
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

}


package com.example.apnaniwas.notification.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.apnaniwas.R;
import com.example.apnaniwas.util.FirebaseTokenPref;

public class TempLayout extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private Button buttonDisplayToken;
    private TextView textViewToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templayout);

        //getting views from xml
        textViewToken = (TextView) findViewById(R.id.textViewToken);
        buttonDisplayToken = (Button) findViewById(R.id.buttonDisplayToken);

        //adding listener to view
        buttonDisplayToken.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == buttonDisplayToken) {
            //getting token from shared preferences
            String token = FirebaseTokenPref.getInstance(this).getDeviceToken();
                //showSmallNotification("HEY","ITS WORKING",);
            //if token is not null
            if (token != null) {
                //displaying the token
                textViewToken.setText(token);
            } else {
                //if token is null that means something wrong
                textViewToken.setText("Token not generated");
            }
        }
    }

    public void showSmallNotification(String title, String message, Intent intent) {
         final int ID_SMALL_NOTIFICATION = 235;
        String NOTIFICATION_CHANNEL_ID="channel_id";
        String CHANNEL_NAME="NOTICE_CHAIRMAN";
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        getApplicationContext(),
                        ID_SMALL_NOTIFICATION,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        //Notification channel should only be created for devices running Android 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            //Boolean value to set if lights are enabled for Notifications from this Channel
            notificationChannel.enableLights(true);

            //Boolean value to set if vibration is enabled for Notifications from this Channel
            notificationChannel.enableVibration(true);

            //Sets the color of Notification Light
            notificationChannel.setLightColor(Color.GREEN);

            //Set the vibration pattern for notifications. Pattern is in milliseconds with the format {delay,play,sleep,play,sleep...}
            notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500, 500});

            //Sets whether notifications from these Channel should be visible on Lockscreen or not
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(),NOTIFICATION_CHANNEL_ID);
        Notification notification;
        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.mipmap.ic_launcher))
                .setContentText(message)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) getApplication().getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(ID_SMALL_NOTIFICATION, notification);
    }
}
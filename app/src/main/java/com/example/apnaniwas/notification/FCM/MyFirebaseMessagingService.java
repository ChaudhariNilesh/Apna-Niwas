package com.example.apnaniwas.notification.FCM;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.apnaniwas.R;
import com.example.apnaniwas.SplashActivity;

import com.example.apnaniwas.ui.home.HomeFragment;
import com.example.apnaniwas.util.FirebaseTokenPref;
import com.example.apnaniwas.util.NoticePref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static com.example.apnaniwas.ui.home.HomeFragment.setupBadge;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseIIDService";
    public  short count = 0;
    public static int noticeCnt = 1;
    final String NOTIFICATION_CHANNEL_ID = "APNA_NIWAS_00";
    final int icon = R.mipmap.ic_launcher;
    private int noticeBell;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e("FirebaseIIDService", "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = Objects.requireNonNull(task.getResult()).getToken();
                        Log.e(TAG, "Refreshed token: " + token);
                        storeToken(token);
                    }
                });
    }

    private void storeToken(String token) {
            FirebaseTokenPref.getInstance(getApplicationContext()).saveDeviceToken(token);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null)
            return;
        if (remoteMessage.getData().size() > 0) {
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void sendNotification(JSONObject json) {
        try {

            Long tsLong = System.currentTimeMillis();
            String ts = tsLong.toString();
            // Date d = new Date(tsLong);
            NoticePref.getInstance(this).addNoticePref(json, ts);
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");

            //Splash is launcher if another activity is launcher then use that activity in intent.
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Config.PUSH_NOTIFICATION, "yes");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            if (TextUtils.isEmpty(message))
                return;
            else {
                showNotification(mBuilder, icon, title, message,pendingIntent);
            }
            sendToNoticeBoard(json);
        }
         catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("SEND NOTIFICATION", "Exception: " + e.getMessage());
        }
    }

    private void sendToNoticeBoard(JSONObject json) {
        Intent broadcastIntent = new Intent(Config.PUSH_NOTIFICATION);
        try {
            broadcastIntent.putExtra("data",json.getJSONObject("data").toString());
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(NotificationCompat.Builder mBuilder, int icon, String title, String message, PendingIntent pendingIntent) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //For Android Version Orio and greater than orio.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            @SuppressLint("WrongConstant") NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, title, importance);
            mChannel.setDescription(message);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLACK);
            mChannel.enableVibration(true);
            mNotifyManager.createNotificationChannel(mChannel);
        }
        //For Android Version lower than oreo.
        mBuilder.setContentTitle(title).setSmallIcon(icon)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), icon))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(Color.parseColor("#0055FF"))
                .setContentIntent(pendingIntent)
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        mNotifyManager.notify(count, mBuilder.build());
        count++;
        setupBadge(count);

    }


    public static void clearNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.cancelAll();
    }

}

//package com.example.apnaniwas.notification.FCM;
//
//import android.annotation.SuppressLint;
//import android.app.ActivityManager;
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.ComponentName;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.media.Ringtone;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.text.Html;
//import android.text.TextUtils;
//import android.util.Patterns;
//
//import androidx.annotation.RequiresApi;
//import androidx.core.app.NotificationCompat;
//
//import com.example.apnaniwas.R;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@RequiresApi(api = Build.VERSION_CODES.O)
//public class PushNotificationUtils  {
//
//    public static final int ID_BIG_NOTIFICATION = 234;
//    public static final int ID_SMALL_NOTIFICATION = 235;
//
//    public static final String NOTIFICATION_CHANNEL_ID="channel_id";
//
//    public static final String CHANNEL_NAME="NOTICE_CHAIRMAN";
//
//
//    private Context context;
//    private Context mContext;
//
//    public PushNotificationUtils(Context mCtx) {
//        this.context = mCtx;
//    }
//
//    public void showNotificationMessage(String title, String message, String timestamp, Intent intent) {
//        showNotificationMessage(title, message, timestamp,intent, null);
//    }
//
//    public void showNotificationMessage(final String title, final String message,String timestamp ,Intent intent, String imageUrl) {
//
//        if (TextUtils.isEmpty(message))
//            return;
//        final int icon = R.mipmap.ic_launcher;
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        final PendingIntent resultPendingIntent =
//                PendingIntent.getActivity(
//                        mContext,
//                        0,
//                        intent,
//                        PendingIntent.FLAG_CANCEL_CURRENT
//                );
//
//        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//                mContext,NOTIFICATION_CHANNEL_ID);
//
//
//        if (!TextUtils.isEmpty(imageUrl)) {
//
//            if (imageUrl.length() > 4 && Patterns.WEB_URL.matcher(imageUrl).matches()) {
//
//                Bitmap bitmap = getBitmapFromURL(imageUrl);
//
//                if (bitmap != null) {
//                    showBigNotification(bitmap, mBuilder, icon, title, message,timestamp, resultPendingIntent);
//                } else {
//                    showSmallNotification(mBuilder, icon, title, message, timestamp,resultPendingIntent);
//                }
//            }
//        } else {
//            showSmallNotification(mBuilder, icon, title, message, timestamp,resultPendingIntent);
//            //playNotificationSound();
//        }
//    }
//
//    private void showSmallNotification(NotificationCompat.Builder mBuilder, int icon, String title, String message,String timeStamp ,PendingIntent resultPendingIntent) {
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        String NOTIFICATION_CHANNEL_ID = "APNA_NIWAS_00";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
//            // Configure the notification channel.
//            notificationChannel.setDescription("Sample Channel description");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.BLACK);
//         //   notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//
//        inboxStyle.addLine(message);
//
//        Notification notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
//                .setAutoCancel(true)
//                .setContentTitle(title)
//                .setContentIntent(resultPendingIntent)
//                .setStyle(inboxStyle)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
//                .setContentText(message)
//                .build();
//        assert notificationManager != null;
//        notificationManager.notify(Config.NOTIFICATION_ID, notification);
//    }
//
//    private void showBigNotification(Bitmap bitmap, NotificationCompat.Builder mBuilder, int icon, String title, String message, String timeStamp, PendingIntent resultPendingIntent) {
//        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
//        bigPictureStyle.setBigContentTitle(title);
//        bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
//        bigPictureStyle.bigPicture(bitmap);
//        Notification notification;
//        notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
//                .setAutoCancel(true)
//                .setContentTitle(title)
//                .setContentIntent(resultPendingIntent)
//                .setStyle(bigPictureStyle)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
//                .setContentText(message)
//                .build();
//
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//        assert notificationManager != null;
//        notificationManager.notify(Config.NOTIFICATION_ID_BIG_IMAGE, notification);
//    }
//
//    private Bitmap getBitmapFromURL(String imageUrl) {
//        try {
//            URL url = new URL(imageUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            return BitmapFactory.decodeStream(input);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
///*    void playNotificationSound() {
//        try {
//            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
//                    + "://" + mContext.getPackageName() + "/raw/notification");
//            Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }*/
//
//    public static void clearNotifications(Context context) {
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        assert notificationManager != null;
//        notificationManager.cancelAll();
//    }
//
///*    public static long getTimeMilliSec(String timeStamp) {
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date date = format.parse(timeStamp);
//            assert date != null;
//            return date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }*/
//
//}
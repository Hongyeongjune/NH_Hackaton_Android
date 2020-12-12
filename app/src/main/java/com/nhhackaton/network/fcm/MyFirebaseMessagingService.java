package com.nhhackaton.network.fcm;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nhhackaton.R;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.view.fcmalert.FcmAlertDialog;
import com.nhhackaton.view.signin.SignInActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "Channel ID";
    private final long[] VIBRATE = {500, 1000, 500, 1000};

    public static void getFcmToekn() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            LogUtils.logInfo("Fetching Fcm registration token failed" + task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        LogUtils.logInfo("Token : " + token);
                    }
                });
    }

    @Override
    public void onNewToken(String token) {
        LogUtils.logInfo("Refreshed token : " + token);
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            notifyBackground(remoteMessage);
            if (isAppRunning(getApplicationContext())) {//포그라운드
                notifyForeground(remoteMessage);
            } else {//백그라운드
                notifyBackground(remoteMessage);
            }
        }
    }

    private boolean isAppRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        for (int i = 0; i < procInfos.size(); i++) {
            if (procInfos.get(i).processName.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private void notifyForeground(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, FcmAlertDialog.class);
        intent.putExtra("FCM_TITLE", remoteMessage.getNotification().getTitle());
        intent.putExtra("FCM_BODY", remoteMessage.getNotification().getBody());
        startActivity(intent);
    }

    private void notifyBackground(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationCompat =
                new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationCompat.setContentIntent(pendingIntent);
        notificationCompat.setWhen(System.currentTimeMillis());
        notificationCompat.setSmallIcon(R.drawable.common_google_signin_btn_icon_light);
        notificationCompat.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationCompat.setContentText(remoteMessage.getNotification().getBody());
        notificationCompat.setVibrate(VIBRATE);
        notificationCompat.setSound(uri);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notificationCompat.build());

    }

}

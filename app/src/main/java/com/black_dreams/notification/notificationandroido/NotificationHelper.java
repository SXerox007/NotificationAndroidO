package com.black_dreams.notification.notificationandroido;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Developer: SUMIT_THAKUR
 * Dated: 10/07/17.
 */
public class NotificationHelper extends ContextWrapper implements AppConstants {
    private NotificationManager manager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(final Context base) {
        super(base);
        NotificationChannel chan1 = new NotificationChannel(PRIMARY_CHANNEL,
                "Discount and Sales",
                NotificationManager.IMPORTANCE_DEFAULT);
        chan1.setLightColor(Color.BLACK);
        chan1.setDescription("sales 60-70 % off");
        chan1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(chan1);

        NotificationChannel chan2 = new NotificationChannel(SECONDARY_CHANNEL,
                "Updates", NotificationManager.IMPORTANCE_MAX);
        chan2.setLightColor(Color.BLACK);
        chan2.setDescription("Update the App soon");
        chan2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(chan2);

        NotificationChannel chan3 = new NotificationChannel(PRIMARY_CHANNEL,
                "New Collection", NotificationManager.IMPORTANCE_DEFAULT);
        chan3.setDescription("New vans shoes. Grab it.");
        chan3.setLightColor(Color.BLACK);
        chan3.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(chan3);
    }


    /**
     * @param title               title
     * @param body                body
     * @param levelOFNotifiaction level of Notifiaction/channel id
     * @return Notification.Builder configured with the selected channel and details
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    public Notification.Builder getNotification(String title, String body, String levelOFNotifiaction) {
        return new Notification.Builder(getApplicationContext(), levelOFNotifiaction)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(R.color.colorPrimaryDark)
                .setSmallIcon(getSmallIcon())
                .setBadgeIconType(R.id.icon_only)
                .setAutoCancel(true);
    }

    /**
     * Send a notification.
     *
     * @param id           The ID of the notification
     * @param notification The notification object
     */
    public void notify(int id, Notification.Builder notification) {
        getManager().notify(id, notification.build());
    }


    /**
     * Get the small icon for this app
     *
     * @return The small icon resource id
     */
    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }


    /**
     * Get the notification manager.
     * <p>
     * Utility method as this helper works with it a lot.
     *
     * @return The system service NotificationManager
     */
    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }


    /**
     * kill the notification
     */
    private void killNotification() {
        // PendingIntent pendingIntent = PendingIntent.getService(this,0,  ,PendingIntent.FLAG_CANCEL_CURRENT);
    }

}

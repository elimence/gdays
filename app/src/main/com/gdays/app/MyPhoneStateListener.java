package com.gdays.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import java.util.Date;


public class MyPhoneStateListener extends PhoneStateListener {

    Context context;
    private String caller;

    NotificationManager notificationManager;

    public static final long[] VIBRATE_LONG  = { 500,200,500,100,500,100,500,0,500, 0, 300 };

    public MyPhoneStateListener(Context context) {
        super();
        caller = "";
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                //when Idle i.e no call
                MyOverlayService.stop();
                sendSMS();
                showNotification();
                MainActivity_.increment();
                Toast.makeText(context, "Phone state Idle: "+incomingNumber, Toast.LENGTH_LONG).show();
                break;

            case TelephonyManager.CALL_STATE_OFFHOOK:
                //when Off hook i.e in call
                //Make intent and start your service here
                Toast.makeText(context, "Phone state Off hook: "+incomingNumber, Toast.LENGTH_LONG).show();
                break;

            case TelephonyManager.CALL_STATE_RINGING:
                //when Ringing
                MyOverlayService.callerID = incomingNumber;
                caller = incomingNumber;
                Intent intent1 = new Intent(context, MyOverlayService.class);
                context.startService(intent1);
                Toast.makeText(context, "Phone state Ringing: "+ incomingNumber, Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

    public  void sendSMS() {
        boolean autoRespond = Settings.getAutorespond();
        String responseText = Settings.getResponseText();

        if (autoRespond && caller != "" && MainActivity_.missed) {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(caller, null, responseText, null, null);
            Log.d("Listener", caller + " message sent ");
            caller = "";
        } else {
            Log.d("Listener", caller + " message not sent "+ responseText);
        }

    }


    void showNotification() {
        // intent that will be fired when a notification is clicked
        Intent intent = new Intent(context, MainActivity_.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // construct the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher))
                .setContentText("click to view number of responses sent")
                .setContentTitle(MainActivity_.getCount()+ " New Responses Sent");

        // add notification extras
        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);
        builder.setStyle(new NotificationCompat.InboxStyle());
        builder.setWhen(new Date().getTime());
        builder.setLights(Color.GREEN, 500, 500);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setVibrate( VIBRATE_LONG );

        // fire off the notification
        notificationManager.notify(1, builder.build());
    }
}
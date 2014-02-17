package com.gdays.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyPhoneStateListener extends PhoneStateListener {

    Context context;
    private String caller;

    public MyPhoneStateListener(Context context) {
        super();
        caller = "";
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                //when Idle i.e no call
                MyOverlayService.stop();
                sendSMS();
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
                Toast.makeText(context, "Phone state Ringing: "+incomingNumber, Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

    public  void sendSMS() {
//        Intent smsIntent = new Intent( Intent.ACTION_VIEW, Uri.parse("sms:" + caller) );
//        smsIntent.putExtra( "sms_body", "hi, why you call" );
//        smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (caller != "") {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(caller, null, "hey! why you call me?", null, null);
            Log.d("sam", caller+ " message sent ");
        }

    }
}
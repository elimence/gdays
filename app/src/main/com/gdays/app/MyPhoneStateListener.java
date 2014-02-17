package com.gdays.app;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyPhoneStateListener extends PhoneStateListener {

    Context context;
    public MyPhoneStateListener(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                //when Idle i.e no call
                MyOverlayService.stop();
                Toast.makeText(context, "Phone state Idle: "+incomingNumber, Toast.LENGTH_LONG).show();
                break;

            case TelephonyManager.CALL_STATE_OFFHOOK:
                //when Off hook i.e in call
                //Make intent and start your service here
                Toast.makeText(context, "Phone state Off hook: "+incomingNumber, Toast.LENGTH_LONG).show();
                break;

            case TelephonyManager.CALL_STATE_RINGING:
                //when Ringing
                Intent intent1 = new Intent(context, MyOverlayService.class);
                context.startService(intent1);
                Toast.makeText(context, "Phone state Ringing: "+incomingNumber, Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }
}
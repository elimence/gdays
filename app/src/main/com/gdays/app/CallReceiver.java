package com.gdays.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MyPhoneStateListener myPhoneListener = new MyPhoneStateListener(context);

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(myPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        telephonyManager.listen(myPhoneListener, PhoneStateListener.LISTEN_NONE);
    }

}


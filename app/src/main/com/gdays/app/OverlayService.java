package com.gdays.app;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OverlayService extends Service {

    protected int id = 0;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
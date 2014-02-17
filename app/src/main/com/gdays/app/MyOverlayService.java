package com.gdays.app;


import android.content.Context;

import com.gdays.app.ext.OverlayService;

public class MyOverlayService extends OverlayService {

    public static MyOverlayService instance;
    public static String callerID;

    private MyOverlayView overlayView;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        overlayView = new MyOverlayView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            overlayView.destroy();
        }

    }

    static public void stop() {
        if (instance != null) {
            instance.stopSelf();
        }
    }


}
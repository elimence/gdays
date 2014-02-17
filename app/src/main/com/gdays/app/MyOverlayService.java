package com.gdays.app;


import com.gdays.app.ext.OverlayService;

public class MyOverlayService extends OverlayService {

    public static MyOverlayService instance;

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
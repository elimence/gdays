package com.gdays.app;



public class SampleOverlayService extends OverlayService {

    public static SampleOverlayService instance;

    private SampleOverlayView overlayView;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        overlayView = new SampleOverlayView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (overlayView != null) {
            overlayView.destory();
        }

    }

    static public void stop() {
        if (instance != null) {
            instance.stopSelf();
        }
    }

}
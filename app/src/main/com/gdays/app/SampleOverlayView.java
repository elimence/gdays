package com.gdays.app;

import android.app.Service;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.gdays.app.R;
import com.gdays.app.SampleOverlayService;

import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.jawsware.core.share.OverlayService;
import com.jawsware.core.share.OverlayView;

public class SampleOverlayView extends OverlayView {

    private TextView info;

    public SampleOverlayView(OverlayService service) {
        super(service, R.layout.overlay, 1);
    }

    public int getGravity() {
        return Gravity.TOP + Gravity.RIGHT;
    }

    @Override
    protected void onInflateView() {
        info = (TextView) this.findViewById(R.id.textview_info);
    }

    @Override
    protected void refreshViews() {
        info.setText("WAITING\nWAITING");
    }

    @Override
    protected void onTouchEvent_Up(MotionEvent event) {
        info.setText("UP\nPOINTERS: " + event.getPointerCount());
    }

    @Override
    protected void onTouchEvent_Move(MotionEvent event) {
        info.setText("MOVE\nPOINTERS: " + event.getPointerCount());
    }

    @Override
    protected void onTouchEvent_Press(MotionEvent event) {
        info.setText("DOWN\nPOINTERS: " + event.getPointerCount());
    }

    @Override
    public boolean onTouchEvent_LongPress() {
        info.setText("LONG\nPRESS");

        return true;
    }


}
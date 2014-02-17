package com.gdays.app.ext;


import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public abstract class OverlayView extends RelativeLayout {

    protected WindowManager.LayoutParams layoutParams;

    private int layoutResId;

    public OverlayView(OverlayService service, int layoutResId) {
        super(service);
        this.layoutResId = layoutResId;
        load();
    }


    private void setupLayoutParams() {
        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

        layoutParams.gravity = Gravity.CENTER;
    }


    private void inflateView() {
        // Inflates the layout resource, sets up the LayoutParams and adds the
        // View to the WindowManager service.

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResId, this);
        onInflateView();

    }

    protected void onInflateView() {
        // Override this to make calls to findViewById() to setup references to
        // the views that were inflated.
        // This is called automatically when the object is created right after
        // the resource is inflated.
    }

    public boolean isVisible() {
        // Override this method to control when the Overlay is visible without
        // destroying it.
        return true;
    }

    protected void addView() {
        setupLayoutParams();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).addView(this, layoutParams);
        super.setVisibility(View.GONE);
    }

    protected void load() {
        inflateView();
        addView();
        refresh();
    }

    public void destroy() {
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).removeView(this);
    }

    public void refresh() {
        // Call this to update the contents of the Overlay.

        if (!isVisible()) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
    }


}

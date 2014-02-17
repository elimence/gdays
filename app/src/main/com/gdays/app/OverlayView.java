package com.gdays.app;


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

    public OverlayService getService() {
        return (OverlayService) getContext();
    }

    public int getLayoutGravity() {
        // Override this to set a custom Gravity for the view.

        return Gravity.CENTER;
    }

    private void setupLayoutParams() {
        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

        layoutParams.gravity = getLayoutGravity();

        onSetupLayoutParams();

    }

    protected void onSetupLayoutParams() {
        // Override this to modify the initial LayoutParams. Be sure to call
        // super.setupLayoutParams() first.
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

    public void refreshLayout() {
        // Call this to force the updating of the view's layout.

        if (isVisible()) {
            removeAllViews();
            inflateView();

            onSetupLayoutParams();

            ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).updateViewLayout(this, layoutParams);

            refresh();
        }

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

    protected void unload() {
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).removeView(this);

        removeAllViews();
    }

    protected void reload() {
        unload();

        load();
    }

    public void destory() {
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).removeView(this);
    }

    public void refresh() {
        // Call this to update the contents of the Overlay.

        if (!isVisible()) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);

            refreshViews();
        }
    }

    protected void refreshViews() {
        // Override this method to refresh the views inside of the Overlay. Only
        // called when Overlay is visible.
    }



    protected void hide() {
        // Set visibility, but bypass onVisibilityToChange()
        super.setVisibility(View.GONE);
    }

    protected void show() {
        // Set visibility, but bypass onVisibilityToChange()

        super.setVisibility(View.VISIBLE);
    }



}

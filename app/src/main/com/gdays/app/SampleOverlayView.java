package com.gdays.app;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;


public class SampleOverlayView extends OverlayView {

    public SampleOverlayView(OverlayService service) {
        super(service, R.layout.activity_incomming_call);
    }

    public int getGravity() {
        return Gravity.TOP + Gravity.RIGHT;
    }

    @Override
    protected void onInflateView() {
        Button close = (Button) this.findViewById(R.id.close);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SampleOverlayService.stop();
            }
        });
    }

}
package com.gdays.app;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.gdays.app.ext.OverlayService;
import com.gdays.app.ext.OverlayView;



public class MyOverlayView extends OverlayView {
Context context;
    public MyOverlayView(OverlayService service) {
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
                MainActivity_.missed = false;
                MyOverlayService.stop();
            }
        });

    }

}
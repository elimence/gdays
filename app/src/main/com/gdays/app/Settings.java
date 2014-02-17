package com.gdays.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.gdays.app.ext.MyPrefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_settings)
public class Settings extends Activity {

    @ViewById EditText responder_text;
    @ViewById Switch responder;

    @Pref
    public static MyPrefs_ preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void initViews() {
        responder_text.setText(preferences.responseText().get());
        responder.setChecked(preferences.autoResponse().get());
    }


    @Click
    public void responder(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            preferences.autoResponse().put(true);
            Toast.makeText(this, "Auto responder active", Toast.LENGTH_SHORT).show();
        } else {
            preferences.autoResponse().put(false);
            Toast.makeText(this, "Auto responder deactivated", Toast.LENGTH_SHORT).show();
        }
    }

    @Click
    public void btn_save_text(View view) {
        String response_text = responder_text.getText().toString();
        preferences.edit().responseText().put(response_text).apply();
        Toast.makeText(this, "Response text saved", Toast.LENGTH_SHORT).show();
    }

    public static boolean getAutorespond() {
        boolean flag = true;
        try {
            flag = preferences.autoResponse().get();
        } catch (NullPointerException npe) {
            Log.e("Settings", npe.fillInStackTrace().toString(), npe);
        }
        return flag;
    }

    public static String getResponseText() {
        String text = "Busy at the moment, will call back later!";

        try {
            text = preferences.responseText().get();
        } catch (NullPointerException npe) {
            Log.e("Settings", npe.fillInStackTrace().toString(), npe);
        }
        return text;
    }

}

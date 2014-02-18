package com.gdays.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import com.gdays.app.ext.MyPrefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

//TODO:
//Enhance MainActivity with the EActivity Annotation element

@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    public static boolean missed = true;

    @ViewById
    public TextView responseCount;

    @Pref public static MyPrefs_ preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO:
        // Remove this line after enhancing MainActivity
        // But only if you pass the layout to the annotation tag
        setContentView(R.layout.activity_main);

        // TODO:
        // Move this line to a method annotated with @AfterViews
        // This is necessary because when you get elements with @ViewById
        // They aren't accessible at the time onCreate is being called
        responseCount.setText(""+ preferences.responseCount().get());

    }


    // TODO:
    // The @AfterViews annotation designates methods that will be called
    // after the views are laid out
    // Implement this method to
    @AfterViews
    public void initViews() {
    }


    // Option menu actions
    @OptionsItem
    void action_settings() {
        Intent i = new Intent(this, Settings_.class);
        startActivity(i);
    }


    @Override
    public void onStop() {
        super.onStop();
        finish();
    }

    public static void increment() {
        if (missed) {
            int count = preferences.responseCount().get();
            preferences.responseCount().put(count + 1);
            missed = false;
        }
    }

    public static int getCount() {
        return preferences.responseCount().get();
    }

}

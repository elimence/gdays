package com.gdays.app.ext;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by c3po on 2/17/14.
 */


@SharedPref(value=SharedPref.Scope.UNIQUE)
public interface MyPrefs {

    @DefaultString("Hi, I'm busy at the moment and can't answer your call. I'll get in touch later, thanks")
    String responseText();

    @DefaultBoolean(true)
    boolean autoResponse();

    @DefaultInt(0)
    int responseCount();

    boolean firstTime();

}
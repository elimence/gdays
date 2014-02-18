package com.gdays.app.test;

import android.test.ActivityInstrumentationTestCase2;

import com.gdays.app.MainActivity_;
import com.robotium.solo.Solo;

public class MiscTest extends
        ActivityInstrumentationTestCase2<MainActivity_> {
    private Solo solo;

    public MiscTest() {
        super(MainActivity_.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        int timeout = 5;

       // TODO
       // Print out log messages in the Settings Activity and
       // use robotium to check that they are printed
       // an example follows

        // Assert that: Log Message 'Entered Settings Activity' is shown
        assertTrue(
                "Log message - 'Entered Settings Activity' is not shown!",
                solo.waitForLogMessage("Entered Settings Activity",
                        timeout));
    }
}

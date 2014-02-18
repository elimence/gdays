package com.gdays.app.test;

import android.test.ActivityInstrumentationTestCase2;

import com.gdays.app.R;
import com.gdays.app.Settings_;
import com.robotium.solo.*;
import com.gdays.app.MainActivity_;

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

        solo.clickOnMenuItem("Settings");
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("some message", Settings_.class);

        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.responder_text));

        solo.enterText((android.widget.EditText) solo
                .getView(R.id.responder_text), "test");
        // Hide the soft keyboard
        solo.hideSoftKeyboard();

        // Click on Enter
        solo.clickOnView(solo.getView(R.id.btn_save_text));
        // Wait for Toast to appear
        assertTrue("Failed to save edits", solo.searchText("Response text saved"));


        // Click on SWitch to deactivate
        solo.clickOnView(solo.getView(R.id.responder));
        solo.waitForText("Auto responder deactivated");
        // Click second time to activate
        solo.clickOnView(solo.getView(R.id.responder));
        assertTrue("Failed to toggle switch", solo.searchText("Auto responder active"));

        // Go back to MainActivity_
        solo.goBack();
        // Start Settings_ again
        solo.clickOnMenuItem("Settings");
        solo.sleep(1000); // give it time to change activity
        // Check if text change persisted
        assertTrue("Text failed to persist after change to 'test'", solo.waitForView(solo
                .getView(R.id.responder_text)));


    }
}

package com.gdays.app.test;

import android.test.ActivityInstrumentationTestCase2;

import com.gdays.app.R;
import com.robotium.solo.*;
import com.gdays.app.MainActivity_;

public class SettingsTest extends
        ActivityInstrumentationTestCase2<MainActivity_> {
	private Solo solo;

	public SettingsTest() {
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

		// Wait for activity: 'com.gdays.app.MainActivity_'
		assertTrue(
				"com.gdays.app.MainActivity_ is not found!",
				solo.waitForActivity(MainActivity_.class));

		// Click on Explicit Activation
		solo.clickOnView(solo
				.getView(R.id.change));

	}
}

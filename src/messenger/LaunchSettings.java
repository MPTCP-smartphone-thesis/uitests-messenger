package messenger;

import utils.Utils;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LaunchSettings extends UiAutomatorTestCase {
	private static String ID_MESSAGE_TEXT = "com.facebook.orca:id/edit_text";
	private static String ID_TO_FIELD = "com.facebook.orca:id/contact_picker_autocomplete_input";
	private static String ID_SMILEY_BUTTON = "com.facebook.orca:id/stickers_button";
	private static String ID_SMILEY_SMILE = "com.facebook.orca:id/sticker_image";
	private static String ID_ACTION_CONTAINER = "com.facebook.orca:id/composer_actions_container";
	private static String ID_PANEL_EMOJI = "com.facebook.orca:id/emoji_category_container";

	/*
	 * not used for now, typing does not work as expected.
	 * assertTrue("Unable to display message", Utils.click(ID_SNAP_PICTURE));
	 * sleep(1000);
	 * 
	 * assertTrue("Unable to set picture message",
	 * Utils.setText(ID_SNAP_MESSAGE, message)); getUiDevice().pressBack();
	 * sleep(1000);
	 */

	private void messengerTest() {
		initializeMessenger();
		spamMessenger();
	}

	private void initializeMessenger() {
		sleep(1000);
		UiObject obj = new UiObject(
				new UiSelector().descriptionContains("Send a new message"));
		while (!obj.exists()) {
			getUiDevice().pressBack();
			UiObject buttonDiscard = new UiObject(new UiSelector().className(
					"android.widget.Button").text("Discard"));
			if (buttonDiscard.exists()) {
				assertTrue("Cannot dismiss message",
						Utils.clickAndWaitForNewWindow(buttonDiscard));
			}
			obj = new UiObject(
					new UiSelector().descriptionContains("Send a new message"));
		}
		sleep(1000);
		assertTrue("Compose button not here",
				Utils.click(new UiObject(
						new UiSelector().descriptionContains("Send a new message"))));
		sleep(1000);
		Utils.clickAndWaitForNewWindow(new UiObject(
				new UiSelector().className(
						"android.widget.RelativeLayout").instance(0)));
		sleep(1000);
		assertTrue("Cannot send message",
				Utils.setText(
						new UiObject(new UiSelector().className(
								"android.widget.EditText").instance(1)),
						"Hello, this is a test"));
		sleep(1000);
		assertTrue(
				"Cannot send the message",
				Utils.click(new UiObject(
						new UiSelector().resourceId(ID_ACTION_CONTAINER).childSelector(
								new UiSelector()
								.className("android.widget.ImageButton")
								.instance(5)))));
		sleep(1000);
	}

	public void spamMessenger() {
		// let's spam

		// Now we can do always the same
		while (true) {
			// Send text
			assertTrue(
					"Cannot send the message",
					Utils.click(new UiObject(
							new UiSelector().resourceId(ID_ACTION_CONTAINER)
							.childSelector(
									new UiSelector().className(
											"android.widget.ImageButton")
											.instance(0)))));
			sleep(1000);
			assertTrue("Cannot send another message", Utils.setText(
					new UiObject(new UiSelector().className(
							"android.widget.EditText").instance(0)),
					"This is a textual message"));
			sleep(1000);
			assertTrue(
					"Cannot send the message",
					Utils.click(new UiObject(
							new UiSelector().resourceId(ID_ACTION_CONTAINER)
							.childSelector(
									new UiSelector().className(
											"android.widget.ImageButton")
											.instance(5)))));
			sleep(1000);
			// Send smiley
			assertTrue(
					"Cannot see smileys",
					Utils.click(new UiObject(
							new UiSelector().resourceId(ID_ACTION_CONTAINER)
							.childSelector(
									new UiSelector().className(
											"android.widget.ImageButton")
											.instance(3)))));
			sleep(1000);
			// Sometimes bug here...
			Utils.click(new UiObject(
					new UiSelector().resourceId(
							ID_PANEL_EMOJI)
							.childSelector(
									new UiSelector().className(
											"android.widget.FrameLayout")
											.instance(1))));

			assertTrue(
					"Cannot send smiley",
					Utils.click(new UiObject(
							new UiSelector()
							.className("android.widget.GridView")
							.instance(0)
							.childSelector(
									new UiSelector()
									.resourceId(ID_SMILEY_SMILE)
									.instance(0)))));
			sleep(1000);
			// Send photo
			assertTrue(
					"Cannot see photo tab",
					Utils.click(new UiObject(
							new UiSelector().resourceId(ID_ACTION_CONTAINER)
							.childSelector(
									new UiSelector().className(
											"android.widget.ImageButton")
											.instance(1)))));
			sleep(1000);
			assertTrue(
					"Cannot send photo",
					Utils.click(new UiObject(
							new UiSelector().descriptionContains(
									"Hold send button for video, tap for photo"))));
			sleep(1000);
		}
	}

	public void testDemo() throws UiObjectNotFoundException {
		assertTrue("OOOOOpps",
				Utils.openApp(this, "Messenger", "com.facebook.orca"));
		messengerTest();
	}

}
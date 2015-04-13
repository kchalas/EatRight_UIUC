package com.example.kchal_000.eatright_uiuc;
//Import the uiautomator libraries

//import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;

import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
/**
 * Created by Clarence Elliott on 4/12/2015.
 */
public class UITest extends UiAutomatorTestCase{

    public void testAppOpen() throws UiObjectNotFoundException {
        // First we testing the press of the HOME button.
        getUiDevice().pressHome();

        //the "applications" has the content-desc “Apps"
        UiObject Applications = new UiObject(new UiSelector().description("Apps"));

        // testing the click to bring up the All Apps screen.
        Applications.clickAndWaitForNewWindow();

        UiObject apps = new UiObject(new UiSelector().text("Apps"));

        apps.click(); //In the Apps Tab

        // All the applications are in a scrollable list so first we need to get a reference to that list

        UiScrollable ListOfapplications = new UiScrollable(new UiSelector().scrollable(true));

        // and then trying to find the application with the name "MenuOfRestaurant"

        UiObject EatRight = ListOfapplications.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"MenuOfRestaurant");
        EatRight.clickAndWaitForNewWindow();

    }

    public void testAppHomeScreen() throws UiObjectNotFoundException{
        testAppOpen();
        UiObject BackToRestaurantScreen = new UiObject(new UiSelector().resourceId("com.example.kchal_000.eatright_uiuc:id/buttonBack"));
        BackToRestaurantScreen.click();

    }

    public void testRestaurantSelection1() throws UiObjectNotFoundException{
        testAppOpen();
        testAppHomeScreen();

        UiObject Maize = new UiObject(new UiSelector().className("android.widget.Button").index(1));
        Maize.click();

    }



    /*
    public void testRestaurantSelection2() throws UiObjectNotFoundException{
        testAppOpen();
        testAppHomeScreen();

        UiObject Sakanaya = new UiObject(new UiSelector().className("android.widget.Button").index(3));
        Sakanaya.click();

    }

    public void testRestaurantSelection3() throws UiObjectNotFoundException{
        testAppOpen();
        testAppHomeScreen();

        UiObject Cravings = new UiObject(new UiSelector().className("android.widget.Button").index(6));
        Cravings.click();

    }
    */
}

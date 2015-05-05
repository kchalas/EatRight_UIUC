package com.example.kchal_000.eatright_uiuc;
//Import the uiautomator libraries

//import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;

import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
/**
 * Created by Clarence Elliott on 4/12/2015. A testing suite which tests the user interface
 * of the EatRight Application. All aspects of the UI are tested and checked if responding
 * properly.
 */

public class UITest extends UiAutomatorTestCase{


    public void testAppOpen() throws UiObjectNotFoundException {
        // First we test the press of the HOME button.
        getUiDevice().pressHome();
        getUiDevice().pressHome();

        //the "applications" has the content-desc “Apps"
        UiObject Applications = new UiObject(new UiSelector().description("Apps"));

        // testing the click to bring up the All Apps screen.
        Applications.clickAndWaitForNewWindow();

        UiObject apps = new UiObject(new UiSelector().description("Apps"));

        apps.click(); //In the Apps Tab

        // All the applications are in a scrollable list so first we need to get a reference to that list

        UiScrollable ListOfapplications = new UiScrollable(new UiSelector().scrollable(true));

        // and then trying to find the application with the name "EatRight_UIUC"

        UiObject EatRight = ListOfapplications.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"EatRight_UIUC");
        EatRight.clickAndWaitForNewWindow();

    }

    /*
    public void testAppHomeScreen() throws UiObjectNotFoundException{

        testAppOpen();
        UiObject BackToRestaurantScreen = new UiObject(new UiSelector().resourceId("com.example.kchal_000.eatright_uiuc:id/buttonBack"));
        BackToRestaurantScreen.click();

    } */

    public void testRestaurantSelection1() throws UiObjectNotFoundException{
        testAppOpen();

        UiObject Maize = new UiObject(new UiSelector().className("android.widget.Button").index(1));
        Maize.click();

    }

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
    

    public void testDisplayLowCalItem() throws UiObjectNotFoundException{
        testRestaurantSelection1();

        UiObject expandLowCal = new UiObject( new UiSelector().className("android.widget.TextView")
                                                               .textContains("Low Cal - 249 or less"));
        expandLowCal.click();

        UiObject expandPepsi = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Chicken Nuggets"));
        expandPepsi.click();




    }

    public void testExpandMenuList() throws UiObjectNotFoundException{
        testRestaurantSelection1();

        UiObject expandLowCal = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Low Cal - 249 or less"));
        expandLowCal.click();

        UiObject expandMedCal = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Medium Cal - 250 to 499"));
        expandMedCal.click();

        UiObject expandVeryHighCal = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Very High Cal - 750 or more "));
        expandVeryHighCal.click();

    }

    public void testRetractMenuList() throws UiObjectNotFoundException{
        testExpandMenuList();


        UiObject retractLowCal = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Low Cal - 249 or less"));
        retractLowCal.click();

        UiObject retractMedCal = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Medium Cal - 250 to 499"));
        retractMedCal.click();

        UiObject retractVeryHighCal = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Very High Cal - 750 or more "));
        retractVeryHighCal.click();
    }

    public void testCheckBoxSelection() throws UiObjectNotFoundException{
            testDisplayLowCalItem();

        UiObject expandPepsi = new UiObject( new UiSelector().className("android.widget.TextView")
                .textContains("Chicken Nuggets"));

       UiObject pepsiCheck =  expandPepsi.getFromParent(new UiSelector().className("android.widget.CheckBox"));
       pepsiCheck.click();
    }

    public void testVisualization() throws UiObjectNotFoundException{

        testRestaurantSelection1();
        UiObject seeVisual = new UiObject( new UiSelector().resourceId("com.example.kchal_000.eatright_uiuc:id/buttonToViz"));
        seeVisual.click();


    }

    public void testAddItem() throws UiObjectNotFoundException {

        testVisualization();

        UiObject contentScreen = new UiObject( new UiSelector().resourceId("android:id/content"));
        UiObject addItem = contentScreen.getChild(new UiSelector().className("android.widget.ImageButton").index(5));
        addItem.click();

    }

    public void testCombination() throws UiObjectNotFoundException{
        testVisualization();
        UiObject contentScreen = new UiObject( new UiSelector().resourceId("android:id/content"));
        UiObject combo = contentScreen.getChild(new UiSelector().className("android.widget.ImageButton").index(5));
        combo.click();

        combo.click();

    }

    public void testViewDetails() throws UiObjectNotFoundException{
        testVisualization();
        UiObject contentScreen = new UiObject( new UiSelector().resourceId("android:id/content"));
        UiObject details = contentScreen.getChild(new UiSelector().className("android.widget.ImageButton").index(14));
        details.click();

        UiObject goBack = contentScreen.getChild(new UiSelector().className("android.widget.Button").textContains("Back"));
        goBack.click();


    }

    public void resetDeviceUI() throws UiObjectNotFoundException{
        getUiDevice().pressHome();
    }

    
   
}

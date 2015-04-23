package com.tests;

import android.test.ActivityInstrumentationTestCase2;
import com.example.kchal_000.eatright_uiuc.*;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import junit.framework.Assert;
import junit.framework.*;
import java.util.ArrayList;


import information.*;
import com.example.kchal_000.eatright_uiuc.*;

/**
 * Created by siddharthbhaduri on 4/2/15.
 */

public class DetailActivityTest
        extends ActivityInstrumentationTestCase2<DetailActivity> {

    private DetailActivity mFirstTestActivity;
    private Button mFirstTestText;

    public DetailActivityTest() {
        super(DetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFirstTestActivity = getActivity();
       // mFirstTestText = (Button) mFirstTestActivity .findViewById(R.id.buttonBack);
    }


    public void testPreconditions() {
        assertNotNull("mFirstTestActivity is null", mFirstTestActivity);
        //assertNotNull("mFirstTestText is null", mFirstTestText);
    }


    //public void testMyFirstTestTextView_labelText() {
      //  final String expected =
        //        mFirstTestActivity.getString(R.string.title_activity_detail);
        //final String actual = mFirstTestText.getText().toString();
       // assertEquals(expected, actual);
   // }
}
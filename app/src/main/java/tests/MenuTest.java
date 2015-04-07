package tests;

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

import api.apiInterface;
import information.*;

/**
 * Created by siddharthbhaduri on 4/2/15.
 */

public class MenuTest extends InstrumentationTestCase{

    // test all getter methods of menu class
    public void testMealFiber() throws Exception{

        Meal m = new Meal(0.4f, 0.8f, 0.6f, "cookies");

        assertEquals(0.4f, m.getFiber() );
    }
//get protein method
    public void testMealProtein() throws Exception{

        Meal m = new Meal(0.2f, 0.1f, 0.3f, "bacon");

        assertEquals(0.1f, m.getProtein() );
    }

    //get calories method
    public void testMealCalories() throws Exception{

        Meal m = new Meal(0.7f, 0.3f, 0.9f, "pulled-pork");

        assertEquals(0.9f, m.getCalories() );

    }

    // boolean equals method
    public void testNotEqualMeal() throws Exception{

        Meal m = new Meal(0.2f, 0.3f, 0.7f, "chocolate-cake");

        Meal z = new Meal(0.2f, 0.3f, 0.7f, "vanilla-cake");

        assertFalse(m.equals(z));

    }

    public void testEqualMeal() throws Exception{

        Meal r = new Meal(0.2f, 0.3f, 0.7f, "chocolate-cake");

        Meal t = new Meal(0.2f, 0.3f, 0.7f, "chocolate-cake");

        assertTrue(r.equals(t));

    }


}

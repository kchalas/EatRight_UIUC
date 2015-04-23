package com.tests;

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
public class MealCombinationTest extends InstrumentationTestCase{
    public void testMealCombination() throws Exception{
        Meal m = new Meal(0.4f, 0.8f, 0.6f, "steak");

        //MealCombination b = new MealCombination(0.2f, 0.2f, 0.3f, m);
        //how to test meal combinations...
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");
        assertTrue(m.equals(z) );
        //TO DO
    }

    public void MCombinationFiberTest() throws Exception{

        float fiber = 0.2f;          //x position of the point
        float protein = 0.1f;          //y position of the point
        float calories = 0.3f;
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");

        MealCombination r = new MealCombination(fiber, protein, calories, z);
        float correct_fiber = r.mealCombinationGetFiber(r);

        assertEquals(0.6f, correct_fiber, 0.05);
    }

    public void MCombinationProteinTest() throws Exception{

        float fiber = 0.2f;          //x position of the point
        float protein = 0.1f;          //y position of the point
        float calories = 0.3f;
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");

        MealCombination r = new MealCombination(fiber, protein, calories, z);
        float correct_protein = r.mealCombinationGetProtein(r);

        assertEquals(0.9f, correct_protein, 0.05);
    }

    public void MCombinationCaloriesTest() throws Exception{

        float fiber = 0.2f;          //x position of the point
        float protein = 0.1f;          //y position of the point
        float calories = 0.3f;
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");

        MealCombination r = new MealCombination(fiber, protein, calories, z);
        float correct_calories = r.mealCombinationGetCalories(r);

        assertEquals(0.9f, correct_calories, 0.05);
    }
}


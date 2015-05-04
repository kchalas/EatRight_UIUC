package tests;

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

        Meal y = new Meal(0.2f, 0.1f, 0.3f, "base");
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");

        MealCombination r = new MealCombination(y);
        r.addDropMeal(z);
        float correct_fiber = r.getFiber();

        assertEquals(0.6f, correct_fiber, 0.05);
    }

    public void MCombinationProteinTest() throws Exception{

        Meal y = new Meal(0.2f, 0.1f, 0.3f, "base");
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");

        MealCombination r = new MealCombination(y);
        r.addDropMeal(z);
        float correct_protein = r.getProtein();

        assertEquals(0.9f, correct_protein, 0.05);
    }

    public void MCombinationCaloriesTest() throws Exception{

        Meal y = new Meal(0.2f, 0.1f, 0.3f, "base");
        Meal z = new Meal(0.4f, 0.8f, 0.6f, "steak");

        MealCombination r = new MealCombination(y);
        r.addDropMeal(z);
        float correct_calories = r.getCalories();

        assertEquals(0.9f, correct_calories, 0.05);
    }
}


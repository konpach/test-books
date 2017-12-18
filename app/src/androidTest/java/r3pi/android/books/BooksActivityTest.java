package r3pi.android.books;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import r3pi.android.books.ui.screens.books.BooksActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by konpach on 18/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class BooksActivityTest {

    @Rule
    public  final  ActivityRule<BooksActivity> main=new ActivityRule<>(BooksActivity.class);


    @Test
    public void screenLaunched(){
        onView(withId(R.id.search_view)).check(matches(isDisplayed()));
    }
    @Test
    public void recipesInputStartsFocused(){
        onView(withId(R.id.search_view)).check(matches(hasFocus()));
    }

    @Test
    public void checkThatHasResults() {
        onView(withId(R.id.search_view)).perform(typeText("greece"), closeSoftKeyboard());

    }
}

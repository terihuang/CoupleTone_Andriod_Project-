package com.example.khor_000.testapp;

import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import com.example.khor_000.testapp.MapsActivity;
import com.example.khor_000.testapp.R;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;
/**
 * Created by hboch_000 on 6/3/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivitytest {
    @Rule
    public ActivityTestRule<MapsActivity> mActivityRule = new ActivityTestRule<>(MapsActivity.class);
    @Test
    public void checkHistoryList()
    {
        onView(withId(R.id.hlbut)).perform(click());
        ViewInteraction expectedView = onView(withId(R.id.histlist));
        onView(withId(R.id.histlist)).check(matches(isDisplayed()));
        //onView(withId(R.id.hlbut)).perform(typeText(""));
        /*onData(hasToString(startsWith("Item Text")))
                .inAdapterView(withId(R.id.histlist))
                .perform(click()).check;*/
        //onView(withId(R.id.hlbut)).check(matches(withText()));
    }
}

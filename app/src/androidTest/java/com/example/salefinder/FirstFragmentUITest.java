package com.example.salefinder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FirstFragmentUITest {
    public static final String testString1 = "test1";
    public static final String testString2 = "test2";

//     Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
//     after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddItemToList() {
//        R.id.content

//        // Type text and then press the button.
//        onView(withId(R.id.editTextItem))
//                .perform(typeText(testString1), closeSoftKeyboard());
//        onView(withId(R.id.addItemBtn)).perform(click());
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged)).check(matches(withText(testString1)));
    }
}

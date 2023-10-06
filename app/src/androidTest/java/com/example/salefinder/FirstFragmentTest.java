package com.example.salefinder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.salefinder.util.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FirstFragmentTest {
    public static final String testString1 = "test1";
    public static final String testString2 = "test2";

//     Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
//     after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddItemToListByButton() {
        // Type text and then press the button.
        onView(withId(R.id.edit_text_item))
                .perform(typeText(testString1), closeSoftKeyboard());
        onView(withId(R.id.add_item_btn)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.item_name)).check(matches(withText(testString1)));
    }

    @Test
    public void testAddItemToListByEnterKey() {
        // Type text and then press the button.
        onView(withId(R.id.edit_text_item))
                .perform(typeText(testString1), pressImeActionButton());

        // Check that the text was changed.
        onView(withId(R.id.item_name)).check(matches(withText(testString1)));
    }

    @Test
    public void addMultipleItemsToList() {
        // Type text and then press the button.
        onView(withId(R.id.edit_text_item))
                .perform(typeText(testString1), closeSoftKeyboard());
        onView(withId(R.id.add_item_btn)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.item_name)).check(matches(withText(testString1)));

        // Type text and then press the button.
        onView(withId(R.id.edit_text_item))
                .perform(typeText(testString2), closeSoftKeyboard());
        onView(withId(R.id.add_item_btn)).perform(click());

        // Check that the text was changed.
        onView(new RecyclerViewMatcher(R.id.recycler_added_items)
                .atPositionOnView(0, R.id.item_name))
                .check(matches(withText(testString2)));
        onView(new RecyclerViewMatcher(R.id.recycler_added_items)
                .atPositionOnView(1, R.id.item_name))
                .check(matches(withText(testString1)));
    }

    @Test
    public void testAddItemToListInMemory() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            assertEquals(0, activity.listItems.size());
        });

        onView(withId(R.id.edit_text_item))
                .perform(typeText(testString1), closeSoftKeyboard());
        onView(withId(R.id.add_item_btn)).perform(click());

        activityScenarioRule.getScenario().onActivity(activity -> {
            assertEquals(1, activity.listItems.size());
            assertEquals(testString1, activity.listItems.get(0).getName());
        });
    }
}

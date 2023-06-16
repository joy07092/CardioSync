package com.example.cardiosync;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This is UI test for whole the application
     * It includes adding a new record in the list
     * then watching the details of that record
     * then updating that record
     * then deleting the record
     */
    @Test
    public void testAddButton() {
        //checking the first activity
        onView(withId(R.id.Mainactivity)).check(matches(isDisplayed()));

        //adding new record and going to data_entry activity

        onView(withId(R.id.AddBUttonId)).perform(click());
        onView(withId(R.id.dataentry)).check(matches(isDisplayed()));
        onView(withId(R.id.dateValue)).perform(ViewActions.typeText("2021-02-02"));
        onView(withId(R.id.timeValue)).perform(ViewActions.typeText("10:19"));
        onView(withId(R.id.systolicValue)).perform(ViewActions.typeText("120"));
        pressBack();
        onView(withId(R.id.diastolicValue)).perform(ViewActions.typeText("90"));
        pressBack();
        onView(withId(R.id.heartRateValue)).perform(ViewActions.typeText("80"));
        pressBack();
        onView(withId(R.id.commentValue)).perform(ViewActions.typeText("UI test data insert"));
        pressBack();
        onView(withId(R.id.addButton)).perform(click());

        //coming back to mainactivity
        onView(withId(R.id.Mainactivity)).check(matches(isDisplayed()));


        // looking for details and coming back to mainactivity
        onView(ViewMatchers.withId(R.id.recyclarView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.details)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.Mainactivity)).check(matches(isDisplayed()));

        // updating a record and coming back to mainactivity
        onView(withId(R.id.recyclarView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, ProjectViewAction.clickChildViewWithId(R.id.Edit_buttonId)));
        onView(withId(R.id.updating)).check(matches(isDisplayed()));
        onView(withId(R.id.UdateValue)).perform(ViewActions.clearText());
        onView(withId(R.id.UdateValue)).perform(ViewActions.typeText("2021-02-02"));
        onView(withId(R.id.UtimeValue)).perform(ViewActions.clearText());
        onView(withId(R.id.UtimeValue)).perform(ViewActions.typeText("10:19"));
        onView(withId(R.id.UsystolicValue)).perform(ViewActions.clearText());
        onView(withId(R.id.UsystolicValue)).perform(ViewActions.typeText("110"));
        pressBack();
        onView(withId(R.id.UdiastolicValue)).perform(ViewActions.clearText());
        onView(withId(R.id.UdiastolicValue)).perform(ViewActions.typeText("80"));
        pressBack();
        onView(withId(R.id.UheartRateValue)).perform(ViewActions.clearText());
        onView(withId(R.id.UheartRateValue)).perform(ViewActions.typeText("70"));
        pressBack();
        onView(withId(R.id.UcommentValue)).perform(ViewActions.clearText());
        onView(withId(R.id.UcommentValue)).perform(ViewActions.typeText("Updated"));
        pressBack();
        onView(withId(R.id.UpdateButtonId)).perform(click());
        onView(withId(R.id.Mainactivity)).check(matches(isDisplayed()));

        //deleting a record and refreshing the mainactivity

        onView(withId(R.id.recyclarView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0,ProjectViewAction.clickChildViewWithId(R.id.DeleteBUttonId)));

        RecordList.mcl.clear();
        new Utils().saveData(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withId(R.id.Mainactivity)).check(matches(isDisplayed()));

    }
}
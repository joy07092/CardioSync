package com.example.cardiosync;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class Splash_screenTest {
    @Rule
    public ActivityScenarioRule<Splash_screen> splash_screenActivityScenarioRule = new ActivityScenarioRule<Splash_screen>(Splash_screen.class);

    @Test
    public void testSplashScreen() {
        onView(withId(R.id.splashScreen)).check(matches(isDisplayed()));
    }
}


package com.example.cardiosync;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

/**
 * This method is used for doing the internal actions for each record inside recordlist in the UI Test
 *
 */
public class ProjectViewAction {  //

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {//pressing buttons in each recordfield
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

}

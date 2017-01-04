package com.igormelo.teste;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by root on 03/01/17.
 */
@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);


    @Test
    public void testarSeVazia(){
        onView(withId(R.id.button)).perform(longClick());
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void quandoOsDoisCamposSaoChamados(){

        onView(withId(R.id.editText)).perform(typeText("igor"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("dani"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Logged")));
    }
    @Test
    public void testarSeErrado() {
        onView(withId(R.id.editText)).perform(typeText("dani"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("dani"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }

    /*
    @Test
    public void LoginComCampoErrado(){
        Intents.init();
        onView(withId(R.id.editText)).perform(typeText("hacker"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("hacker"), closeSoftKeyboard());
        Matcher<Intent> matcher = hasComponent(MainActivity.class.getName());

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);
        intending(matcher).respondWith(result);

        onView(withId(R.id.button)).perform(click());
        intended(matcher);
        Intents.release();
    }*/


}

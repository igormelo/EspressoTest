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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;


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
    private int i;
    private int j;
    private int k;


    @Test
    public void verificaSeOutraActivityFoiAberta(){
        Intents.init();
        onView(withId(R.id.editText)).perform(typeText("igor"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("dani"), closeSoftKeyboard());

        //verifica que o matcher passado como parâmetro é o que a activity em teste irá lançar,
        //garantindo também que esta intent seja única, ou seja, a Activity que sera iniciada
        Matcher<Intent> matcher = hasComponent(Main2Activity.class.getName());

        //Simula o resultado da activity, ou seja, a activity2 nao abre porque esse metodo simula sua abertura
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        //Devolve um resultado (NO CASO OK) assim que a intent for lançada
        intending(matcher).respondWith(result);

        onView(withId(R.id.button)).perform(click());
        intended(matcher);

        //limpa o estado das intents
        Intents.release();


        //TODO Resumindo: quando esta intent for lançada, responda com um Resultado e nao com a Activity
    }


    @Test
    public void checkInitialState(){
        onView(withId(R.id.editText)).check(matches(isDisplayed()));
        onView(withId(R.id.editText2)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }

    @Test
    public void testarSenhaEmBranco(){
        testeLogin(String.valueOf(R.id.editText));
    }

    @Test
    public void testarLoginEmBranco(){
        testeSenha(String.valueOf(R.id.editText2));
    }

    @Test
    public void testarSeOsCamposEstaoVazios(){
        onView(withId(R.id.button)).perform(click());
        onView(withText("login e senha em branco")).check(matches(isDisplayed()));
        onView(withText("Ok")).perform(click());
        //onView(withId(R.id.button)).perform(click());

    }

    @Test
    public void quandoOsDoisCamposEstaoCorretos(){
        onView(withId(R.id.editText)).perform(typeText("igor"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("dani"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.logged)).check(matches(withText("Logged")));
    }
    @Test
    public void testarSeOsCamposEstaoErrados() {
        onView(withId(R.id.editText)).perform(typeText("dani"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("dani"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }


    @Test
    public void LoopLoginComCampoErrado() {
        for (i = 0; i < 3; i++) {
            testarSeOsCamposEstaoErrados();
        }
    }

    private void testeLogin(String login){
            onView(withId(R.id.editText)).perform(typeText(login),closeSoftKeyboard());
            onView(withId(R.id.button)).perform(click());
    }

    private void testeSenha(String senha){
            onView(withId(R.id.editText2)).perform(typeText(senha),closeSoftKeyboard());
            onView(withId(R.id.button)).perform(click());
    }



}

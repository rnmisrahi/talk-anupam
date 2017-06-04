package com.maatayim.talklet.screens.mainscreen.generalticket;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.maatayim.talklet.R;
import com.maatayim.talklet.TestActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Sophie on 6/4/2017.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class GeneralTipFragmentTest {

    @Rule
    public ActivityTestRule<TestActivity> fragmentActivityTestRule = new ActivityTestRule<>(TestActivity
            .class);
    private GeneralTipFragment fragment;


    @Before
    public void setUp() throws Exception {
        fragment = new GeneralTipFragment();

        fragmentActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(1, fragment, null).commit();


    }

    @Test
    public void confirmImageExists(){
        Espresso.onView(ViewMatchers.withId(R.id.question_or_assertion))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
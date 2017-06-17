package com.maatayim.talklet.screens.mainactivity.mainscreen;

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
 * Created by Sophie on 6/4/2017
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class GeneralFragmentTest {


    @Rule
    public ActivityTestRule<TestActivity> fragmentActivityTestRule = new ActivityTestRule<>(TestActivity
            .class);
    private GeneralFragment fragment;


    @Before
    public void setUp() throws Exception {
        fragment = new GeneralFragment();
        fragmentActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.test_frame_container, fragment, null).commit();
    }

    @Test
    public void confirmImageExists(){
        Espresso.onView(ViewMatchers.withId(R.id.recording_mic))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
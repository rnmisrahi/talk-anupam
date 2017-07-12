package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.wordsoftheday;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.DaysWordsObj;

import java.util.List;

/**
 * Created by Sophie on 6/26/2017
 */

public class WordOfTheDayAdapter extends FragmentPagerAdapter {

    private List<DaysWordsObj> words;

    public WordOfTheDayAdapter(FragmentManager fm, List<DaysWordsObj> words) {
        super(fm);
        this.words = words;
    }

    @Override
    public Fragment getItem(int position) {
        return WordOfTheDayFragment.newInstance(words.get(position));
    }

    @Override
    public int getCount() {
        return words.size();
    }

}
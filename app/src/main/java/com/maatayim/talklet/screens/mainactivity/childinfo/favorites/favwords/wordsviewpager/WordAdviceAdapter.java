package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;

import java.util.List;

/**
 * Created by Sophie on 6/26/2017.
 */

public class WordAdviceAdapter extends FragmentPagerAdapter {

    private List<SpecialWords> words;

    public WordAdviceAdapter(FragmentManager fm, List<SpecialWords> words) {
        super(fm);
        this.words = words;
    }

    @Override
    public Fragment getItem(int position) {
        return WordAdviceFragment.newInstance(words.get(position));
    }

    @Override
    public int getCount() {
        return words.size();
    }

}
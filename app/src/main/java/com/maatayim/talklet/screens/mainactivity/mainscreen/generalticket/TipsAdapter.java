package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Sophie on 5/26/2017.
 */

public class TipsAdapter extends FragmentPagerAdapter {

    private List<TipTicket> generalTicketList;

    public TipsAdapter(FragmentManager fm, List<TipTicket> generalTicketList) {
        super(fm);
        this.generalTicketList = generalTicketList;
    }

    @Override
    public Fragment getItem(int position) {
        return TipsFragment.newInstance(generalTicketList.get(position));
    }

    @Override
    public int getCount() {
        return generalTicketList.size();
    }

}
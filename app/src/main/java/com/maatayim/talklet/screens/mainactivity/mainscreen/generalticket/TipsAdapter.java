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

    private final FragmentManager fm;
    private List<TipTicket> generalTicketList;
    private final boolean isRec;
    private boolean isMoreThanOneChild;

    public TipsAdapter(FragmentManager fm, List<TipTicket> generalTicketList, boolean isRec, boolean isMoreThanOneChild) {
        super(fm);
        this.fm = fm;
        this.generalTicketList = generalTicketList;
        this.isRec = isRec;
        this.isMoreThanOneChild = isMoreThanOneChild;
    }

    @Override
    public Fragment getItem(int position) {
        return TipsFragment.newInstance(generalTicketList.get(position), isRec, isMoreThanOneChild);
    }

    @Override
    public int getCount() {
        return generalTicketList.size();
    }

}

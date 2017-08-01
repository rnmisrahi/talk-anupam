package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophie on 5/26/2017
 */

public class TipsAdapter extends FragmentPagerAdapter {

    private final FragmentManager fm;
    private List<TipTicket> generalTicketList;
    private final boolean isRec;
    private boolean isMoreThanOneChild;
    private int baseId = 0;

    public TipsAdapter(FragmentManager fm, List<TipTicket> generalTicketList, boolean isRec, boolean isMoreThanOneChild) {
        super(fm);
        this.fm = fm;
        this.generalTicketList = new ArrayList<>(generalTicketList);
        this.isRec = isRec;
        this.isMoreThanOneChild = isMoreThanOneChild;
    }

    @Override
    public Fragment getItem(int position) {
        return TipsFragment.newInstance(this.generalTicketList.get(position), isRec, isMoreThanOneChild);
    }


    public List<TipTicket> getDataSet() {
        return generalTicketList;
    }

    @Override
    public int getCount() {
        return generalTicketList.size();
    }


    public void setDataSet(List<TipTicket> dataSet) {
        generalTicketList = dataSet;
    }

    @Override
    public long getItemId(int position) {
        return baseId + position;
    }

    public void updateBaseId(int n){
        baseId += getCount() + n;
    }

}

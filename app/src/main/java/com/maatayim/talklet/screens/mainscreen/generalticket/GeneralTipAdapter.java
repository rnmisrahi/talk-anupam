package com.maatayim.talklet.screens.mainscreen.generalticket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralTipAdapter extends FragmentStatePagerAdapter {

    private List<GeneralTipTicket> generalTicketList;

    public GeneralTipAdapter(FragmentManager fm, List<GeneralTipTicket> generalTicketList){
        super(fm);
        this.generalTicketList = generalTicketList;
    }

    @Override
    public Fragment getItem(int position) {
        return GeneralTipFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return generalTicketList.size();
    }
}

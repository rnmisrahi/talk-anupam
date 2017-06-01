package com.maatayim.talklet.screens.general.generalticket;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sophie on 5/28/2017.
 */

public class GeneralTipPresenter implements GeneralTipContract.Presenter{


    private GeneralTipContract.View view;

    @Inject
    public GeneralTipPresenter(GeneralTipContract.View view){

        this.view = view;
    }


    @Override
    public void getData(int position) {
        view.setTip(mockData().get(position));
    }


    private List<GeneralTipTicket> mockData() {
        List<GeneralTipTicket> tipsList = new ArrayList<>();
        tipsList.add(new GeneralTipTicket("Try to ask about the kids day", false));
        tipsList.add(new GeneralTipTicket("bla bla bla bla", true));
        tipsList.add(new GeneralTipTicket("aaaaa aaaaaaaaa aaaaaaaaa", false));
        tipsList.add(new GeneralTipTicket("bbbbbbbbbbbbbbbbbbb bbb bbbb", true));

        return tipsList;
    }
}

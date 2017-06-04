package com.maatayim.talklet.screens.mainscreen.generalticket;


import com.maatayim.talklet.baseline.BaseContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sophie on 5/28/2017.
 */

public class GeneralTipPresenter implements GeneralTipContract.Presenter{


    private GeneralTipContract.View view;
    private BaseContract.Repository repository;

    @Inject
    public GeneralTipPresenter(GeneralTipContract.View view, BaseContract.Repository repository){
        this.view = view;
        this.repository = repository;
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

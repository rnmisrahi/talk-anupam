package com.maatayim.talklet.screens.general;


import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.general.generalticket.GeneralTipTicket;

import java.util.List;

import dagger.Component;


/**
 * Created by Sophie on 5/26/2017.
 */

@Component
public interface GeneralContract {

    interface Presenter extends BaseContract.Presenter{
        void getData();
    }

    interface View extends BaseContract.View{
        void onDataReceived(List<GeneralTipTicket> ticketList);

    }
}

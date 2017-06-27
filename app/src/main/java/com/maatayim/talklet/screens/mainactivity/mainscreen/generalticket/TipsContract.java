package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;

import com.maatayim.talklet.baseline.BaseContract;

import dagger.Component;


/**
 * Created by Sophie on 5/28/2017.
 */

@Component
public interface TipsContract {


    interface View extends BaseContract.View{
        void setTip(TipTicket ticket);
    }
}

package com.maatayim.talklet.screens.general.generalticket;

import com.maatayim.talklet.baseline.BaseContract;

import dagger.Component;


/**
 * Created by Sophie on 5/28/2017.
 */

@Component
public interface GeneralTipContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(int position);

    }

    interface View extends BaseContract.View{
        void setTip(GeneralTipTicket ticket);
    }
}

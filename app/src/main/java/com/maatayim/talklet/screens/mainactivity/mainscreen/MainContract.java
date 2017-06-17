package com.maatayim.talklet.screens.mainactivity.mainscreen;


import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipTicket;

import java.util.List;

import dagger.Component;


/**
 * Created by Sophie on 5/26/2017
 */

@Component
public interface MainContract {

    interface Presenter extends BaseContract.Presenter{
        void getData();



    }

    interface View extends BaseContract.View{
        void updateTipsViewPager(List<GeneralTipTicket> ticketList);

        void updateWordsCount(int numOfWords, int maxNumOfWords);

        void onChildLoadError();

        void onDisplayChildrenError();

        void onWordsCountLoadError();

        void onTipsLoadError();

        void setChildrenRecyclerView(List<Child> childrenList);
    }
}

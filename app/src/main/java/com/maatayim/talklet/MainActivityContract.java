package com.maatayim.talklet;

import com.maatayim.talklet.baseline.BaseContract;

/**
 * Created by Sophie on 7/5/2017
 */

public interface MainActivityContract {

    interface View extends BaseContract.View{

    }

    interface Presenter extends BaseContract.Presenter{
        void downloadData();
    }
}

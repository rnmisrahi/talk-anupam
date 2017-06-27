package com.maatayim.talklet.screens.mainactivity.record;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;

import java.util.List;


/**
 * Created by Sophie on 6/27/2017.
 */

public interface RecordContract {

    interface View extends BaseContract.View{

        void onDataReceived(List<Child> children);
    }

    interface Presenter extends BaseContract.Presenter{

        void getData();

    }
}

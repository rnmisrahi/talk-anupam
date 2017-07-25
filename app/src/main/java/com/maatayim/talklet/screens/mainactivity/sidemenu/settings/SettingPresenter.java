package com.maatayim.talklet.screens.mainactivity.sidemenu.settings;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/18/2017.
 */

public class SettingPresenter implements SettingContract.Presenter {


    private final SettingContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    @Inject
    public SettingPresenter(SettingContract.View view, BaseContract.Repository repo, Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }


    @Override
    public void getData() {
        getChildrenList();
    }



    private void getChildrenList() {
        repo.getSettingChildList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<List<SettingChild>>() {
                    @Override
                    public void onSuccess(@NonNull List<SettingChild> settingChildren) {
                        view.onDataReceived(settingChildren);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onChildrenLoadError();
                    }
                });

    }


}

package com.maatayim.talklet.screens.general;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.general.generalticket.GeneralTipTicket;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralPresenter implements GeneralContract.Presenter {

    private GeneralContract.View view;
    private BaseContract.Repository repository;

    @Inject
    public GeneralPresenter(GeneralContract.View view, BaseContract.Repository repository) {

        this.view = view;
        this.repository = repository;
    }


    @Override
    public void getData() {
        repository.getTipsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<GeneralTipTicket>>() {
            @Override
            public void onNext(@NonNull List<GeneralTipTicket> generalTipTickets) {
                view.onDataReceived(generalTipTickets);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


//        view.onDataReceived(tipsList);

//
//                .
//                .subscribe(new DisposableCompletableObserver() {
//                    @Override
//                    public void onComplete() {
//                        view.onDataReceived(mockData());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//                });

    }


}

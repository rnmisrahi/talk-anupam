package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/11/2017.
 */

public class ByDatePresenter implements ByDateContract.Presenter {


    private final ByDateContract.View view;
    private BaseContract.Repository repository;
    private final Scheduler scheduler;
    private String callendarData;

    @Inject
    public ByDatePresenter (ByDateContract.View view, BaseContract.Repository repository, Scheduler scheduler){

        this.view = view;
        this.repository = repository;
        this.scheduler = scheduler;
    }


    @Override
    public void getData(int id) {
//        setWordsTypesData(id);
        setCalendarData(id);
    }



    private void setCalendarData(int id) {

        repository.getChildWordsByDate(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<List<CalendarWordsObj>>() {
                    @Override
                    public void onSuccess(@NonNull List<CalendarWordsObj> dateObjs) {
                        view.loadCalendarData(dateObjs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.errorOnLoadCalendarData();
                    }
                });
//        repository.getCalendarData(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(scheduler)
//                .subscribeWith(new DisposableObserver<List<CalendarWordsObj>>() {
//                    @Override
//                    public void onNext(@NonNull List<CalendarWordsObj> calendarList) {
//                        view.loadCalendarData(calendarList);
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        view.errorOnLoadCalendarData();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        view.onComplete();
//                    }
//                });

    }
}
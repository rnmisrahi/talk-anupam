package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maatayim.talklet.baseline.BaseContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/18/2017.
 */

public class AboutYouPresenter implements AboutYouContract.Presenter {


    private final AboutYouContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;

    public static final String TAG = "AboutYouPresenter";

    @Inject
    public AboutYouPresenter(AboutYouContract.View view, BaseContract.Repository repo,
                             Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }

    @Override
    public void getData() {
        getUserProperties();

    }

    @Override
    public void setLanguageList(final View layout, final TextView view) {
        repo.getLanguageList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<String>>() {
                    @Override
                    public void onNext(@NonNull List<String> languages) {
                        addTextViewToLayout((LinearLayout) layout, languages, view);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateAboutYouData(AboutUserObj aboutUserObj) {
        repo.updateUsersData(aboutUserObj)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: save users data success");
                        view.onUpdateDataSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


    }


    private void addTextViewToLayout(LinearLayout linearLayout, List<String> languages, TextView textView){
        for (int i=0; i < languages.size(); i++){
            TextView valueTV = new TextView(linearLayout.getContext());
            valueTV.setText(languages.get(i));
            valueTV.setId(i+1);
            valueTV.setPadding(30, 30, 30, 30);
            valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(valueTV);
            view.setLanguageOnView(linearLayout, valueTV.getId(), textView);
        }
    }

    private void getUserProperties(){
        repo.getUserDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<AboutUserObj>() {
                    @Override
                    public void onSuccess(@NonNull AboutUserObj aboutUserObj) {
                        view.loadUserDetails(aboutUserObj);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onLoadUserDetailsFailure();
                    }
                });



    }
}

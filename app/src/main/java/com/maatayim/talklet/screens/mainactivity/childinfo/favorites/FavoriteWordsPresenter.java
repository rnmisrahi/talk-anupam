package com.maatayim.talklet.screens.mainactivity.childinfo.favorites;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 6/22/2017.
 */

public class FavoriteWordsPresenter implements FavoriteWordsContract.Presenter {

    private final FavoriteWordsContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;
    private String id;

    @Inject
    public FavoriteWordsPresenter(FavoriteWordsContract.View view, BaseContract.Repository repo,
                                  Scheduler scheduler){

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
    }


    @Override
    public void getData(String id) {
        this.id = id;
        getFavoritesWords(id);
        getNewWords(true);
    }



    private void getFavoritesWords(String id) {
        repo.getFavoritesWords(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<FourWordsObj>>() {
                    @Override
                    public void onNext(@NonNull List<FourWordsObj> favoriteWords) {
                        view.initFavoriteWordsRecyclerView(favoriteWords);
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
    public void getNewWords(final boolean forDisplay) {

        repo.getNewWords(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<SpecialWords>>() {
                    @Override
                    public void onNext(@NonNull List<SpecialWords> specialWords) {
                        if(forDisplay){
                            view.initializeViewPager(specialWords);
                        }else{
                            view.initTableWords(specialWords);
                        }

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
    public void getAdvanceWords() {

        repo.getAdvanceWords(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<SpecialWords>>() {
                    @Override
                    public void onNext(@NonNull List<SpecialWords> specialWords) {
                        view.initTableWords(specialWords);
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
    public void getOtherWords() {

        repo.getOtherWords(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableObserver<List<SpecialWords>>() {
                    @Override
                    public void onNext(@NonNull List<SpecialWords> specialWords) {
                        view.initTableWords(specialWords);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.injection;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.FavoriteWordsContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.FavoriteWordsPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class FavoriteWordsModule {

    private final FavoriteWordsContract.View view;

    public FavoriteWordsModule(FavoriteWordsContract.View view) {
        this.view = view;
    }

    @Provides
    public FavoriteWordsContract.Presenter providePresenter(FavoriteWordsContract.View view, BaseContract.Repository repo, Scheduler scheduler) {
        return new FavoriteWordsPresenter(view, repo, scheduler);
    }


    @Provides
    public FavoriteWordsContract.View provideView() {
        return view;
    }

}
package com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.ChoosePhotoContract;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.ChoosePhotoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sophie on 5/21/2017.
 */

@Module
public class ChoosePhotoModule {

    private final ChoosePhotoContract.View view;

    public ChoosePhotoModule(ChoosePhotoContract.View view) {
        this.view = view;
    }

    @Provides
    public ChoosePhotoContract.Presenter providePresenter(ChoosePhotoContract.View view, BaseContract.Repository repo) {
        return new ChoosePhotoPresenter(view, repo);
    }


    @Provides
    public ChoosePhotoContract.View provideView() {
        return view;
    }
}

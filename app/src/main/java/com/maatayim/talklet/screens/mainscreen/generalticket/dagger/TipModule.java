package com.maatayim.talklet.screens.mainscreen.generalticket.dagger;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipContract;
import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class TipModule {

    private final GeneralTipContract.View view;

    public TipModule(GeneralTipContract.View view) {
        this.view = view;
    }

    @Provides
    public GeneralTipContract.Presenter providePresenter(GeneralTipContract.View view, BaseContract.Repository repo) {
        return new GeneralTipPresenter(view, repo);
    }


    @Provides
    public GeneralTipContract.View provideView() {
        return view;
    }

}

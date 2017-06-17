package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.dagger;

import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipContract;

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
    public GeneralTipContract.View provideView() {
        return view;
    }

}

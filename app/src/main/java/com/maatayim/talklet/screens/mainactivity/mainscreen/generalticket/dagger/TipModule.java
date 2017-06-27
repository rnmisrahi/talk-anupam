package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.dagger;

import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipsContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class TipModule {

    private final TipsContract.View view;

    public TipModule(TipsContract.View view) {
        this.view = view;
    }


    @Provides
    public TipsContract.View provideView() {
        return view;
    }

}

package com.maatayim.talklet.screens.general.dagger;

import com.maatayim.talklet.screens.general.GeneralContract;

import dagger.Component;

/**
 * Created by Sophie on 5/28/2017.
 */



@Component(modules = GeneralModule.class)
public interface GeneralComponent {

    void inject(GeneralContract.Presenter generalPresenter);
}

package com.maatayim.talklet.screens.mainscreen.generalticket.dagger;

import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipFragment;
import com.maatayim.talklet.screens.loginactivity.login.injection.PerFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/28/2017.
 */


@PerFragment
@Subcomponent(modules = TipModule.class)
public interface TipComponent {

    void inject(GeneralTipFragment generalTipFragment);
}

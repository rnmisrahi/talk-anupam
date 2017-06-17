package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.ChildGeneralFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/28/2017.
 */


@PerFragment
@Subcomponent(modules = ChildGeneralModule.class)
public interface ChildGeneralComponent {

    void inject(ChildGeneralFragment childGeneralFragment);
}

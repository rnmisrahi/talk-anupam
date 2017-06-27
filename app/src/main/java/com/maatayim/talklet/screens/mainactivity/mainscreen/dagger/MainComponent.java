package com.maatayim.talklet.screens.mainactivity.mainscreen.dagger;

import com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment;
import com.maatayim.talklet.injection.PerFragment;
import dagger.Subcomponent;

/**
 * Created by Sophie on 5/28/2017
 */


@PerFragment
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

    void inject(MainFragment generalFragment);
}

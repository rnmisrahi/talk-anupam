package com.maatayim.talklet.screens.mainactivity.childinfo.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.ChildFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/28/2017.
 */


@PerFragment
@Subcomponent(modules = ChildModule.class)
public interface ChildComponent {

    void inject(ChildFragment childFragment);
}

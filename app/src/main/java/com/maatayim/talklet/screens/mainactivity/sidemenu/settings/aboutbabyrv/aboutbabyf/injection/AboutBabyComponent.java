package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.AboutBabyFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = AboutBabyModule.class)
public interface AboutBabyComponent {

    void inject(AboutBabyFragment fragment);
}
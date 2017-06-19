package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutYouFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = AboutYouModule.class)
public interface AboutYouComponent {

    void inject(AboutYouFragment fragment);
}
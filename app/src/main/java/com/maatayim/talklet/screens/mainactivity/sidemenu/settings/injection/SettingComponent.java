package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = SettingModule.class)
public interface SettingComponent {

    void inject(SettingFragment fragment);
}
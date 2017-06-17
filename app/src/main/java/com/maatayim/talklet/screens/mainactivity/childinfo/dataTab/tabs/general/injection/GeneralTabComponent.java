package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.GeneralTabFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = GeneralTabModule.class)
public interface GeneralTabComponent {

    void inject(GeneralTabFragment fragment);
}
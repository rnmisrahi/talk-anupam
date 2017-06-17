package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.DataTabFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = DataTabModule.class)
public interface DataTabComponent {

    void inject(DataTabFragment fragment);
}
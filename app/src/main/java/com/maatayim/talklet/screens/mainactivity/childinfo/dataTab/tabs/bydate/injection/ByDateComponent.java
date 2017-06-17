package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.ByDateFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = ByDateModule.class)
public interface ByDateComponent {

    void inject(ByDateFragment fragment);
}
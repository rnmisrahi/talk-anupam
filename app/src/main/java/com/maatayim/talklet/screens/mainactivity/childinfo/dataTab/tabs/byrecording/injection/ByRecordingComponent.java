package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.ByRecordingFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = ByRecordingModule.class)
public interface ByRecordingComponent {

    void inject(ByRecordingFragment fragment);
}
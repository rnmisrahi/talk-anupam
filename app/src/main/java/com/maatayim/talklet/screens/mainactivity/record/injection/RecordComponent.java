package com.maatayim.talklet.screens.mainactivity.record.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.record.RecordingFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = RecordModule.class)
public interface RecordComponent {

    void inject(RecordingFragment fragment);
}
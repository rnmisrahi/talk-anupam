package com.maatayim.talklet;

import com.maatayim.talklet.injection.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
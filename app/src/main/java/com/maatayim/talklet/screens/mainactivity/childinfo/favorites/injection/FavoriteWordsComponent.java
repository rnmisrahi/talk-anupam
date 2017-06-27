package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.injection;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.FavoriteWordsFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = FavoriteWordsModule.class)
public interface FavoriteWordsComponent {

    void inject(FavoriteWordsFragment fragment);
}
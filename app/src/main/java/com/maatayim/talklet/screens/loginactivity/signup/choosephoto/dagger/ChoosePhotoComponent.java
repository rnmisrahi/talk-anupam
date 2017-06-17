package com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.ChoosePhotoFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/21/2017.
 */

@PerFragment
@Subcomponent(modules = {ChoosePhotoModule.class})
public interface ChoosePhotoComponent {

    void inject(ChoosePhotoFragment choosePhotoFragment);
}
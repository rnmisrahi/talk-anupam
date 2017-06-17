package com.maatayim.talklet.screens.loginactivity.signup.dagger;

import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.loginactivity.signup.SignupFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/21/2017.
 */

@PerFragment
@Subcomponent(modules = {SignupModule.class})
public interface SignupComponent {

    void inject(SignupFragment signupFragment);
}
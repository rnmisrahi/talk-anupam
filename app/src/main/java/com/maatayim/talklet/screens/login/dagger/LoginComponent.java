package com.maatayim.talklet.screens.login.dagger;
import com.maatayim.talklet.screens.login.LoginFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/21/2017.
 */

@PerFragment
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginFragment loginFragment);
}
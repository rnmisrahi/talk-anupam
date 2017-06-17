package com.maatayim.talklet.screens.loginactivity.login.injection;
import com.maatayim.talklet.injection.PerFragment;
import com.maatayim.talklet.screens.loginactivity.login.LoginFragment;

import dagger.Subcomponent;

/**
 * Created by Sophie on 5/21/2017.
 */

@PerFragment
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginFragment loginFragment);
}
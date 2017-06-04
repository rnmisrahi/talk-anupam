package com.maatayim.talklet.screens.loginactivity.login.injection;

import com.facebook.AccessToken;

/**
 * Created by Sophie on 6/4/2017.
 */

public class AccessTokenWrapper {


    public AccessTokenWrapper(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    private AccessToken accessToken;

    public String getToken() {
        return accessToken.getToken();
    }

    public boolean isNull() {
        return accessToken == null;
    }
}

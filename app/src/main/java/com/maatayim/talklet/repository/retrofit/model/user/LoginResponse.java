package com.maatayim.talklet.repository.retrofit.model.user;

/**
 * Created by Sophie on 7/4/2017.
 */

public class LoginResponse {

    private String token;
private boolean signedUp;

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public boolean isSignedUp() {
        return signedUp;
    }
}

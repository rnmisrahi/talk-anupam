package com.maatayim.talklet.repository.retrofit.model.user;

/**
 * Created by Sophie on 7/4/2017.
 */

public class LoginResponse {

    private String token;
    private boolean signedUp;

    public LoginResponse() {
    }

    public LoginResponse(String token, boolean signedUp) {
        this.token = token;
        this.signedUp = signedUp;
    }

    public String getToken() {
        return token;
    }

    public boolean isSignedUp() {
        return signedUp;
    }
}

package com.maatayim.talklet.repository.retrofit.model.user;

/**
 * Created by Sophie on 7/4/2017
 */

public class LoginFacebookResponse {

    private String token;

    public LoginFacebookResponse() {
    }

    public LoginFacebookResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}

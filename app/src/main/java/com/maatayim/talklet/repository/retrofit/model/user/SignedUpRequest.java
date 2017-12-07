package com.maatayim.talklet.repository.retrofit.model.user;

/**
 * Created by Sophie on 11/13/2017.
 */

public class SignedUpRequest {

    private String token;

    public SignedUpRequest(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

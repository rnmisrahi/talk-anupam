package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 9/18/2017
 */

public class RealmToken extends RealmObject {

    private static final String BEARER = "Bearer ";

    @PrimaryKey
    private String token;

    public RealmToken() {
    }

    public RealmToken(String token) {
        this.token = BEARER + token;
//        this.token = token;
    }

    public RealmToken(RealmToken copy) {
        if (copy != null) {
            this.token = copy.getToken();
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

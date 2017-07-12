package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;

/**
 * Created by Sophie on 7/12/2017
 */

public class RealmFacebookId extends RealmObject {

    private String facebookId;

    public RealmFacebookId() {
    }

    public RealmFacebookId(String id) {
        if (id == null){
            this.facebookId = "";
        }else{
            this.facebookId = id;
        }
    }


    public String getFacebookId() {
        return facebookId;
    }
}

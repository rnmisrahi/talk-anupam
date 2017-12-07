package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/4/2017
 */

public class RealmTip extends RealmObject {

    @PrimaryKey
    private int id;

    private String tipText;
    private String type;
    private int childId;

    public RealmTip() {
    }

    public RealmTip(int id, String tipText, String type, int childID) {
        this.id = id;
        this.tipText = tipText;
        this.type = type;
        this.childId = childID;
    }

    public int getId() {
        return id;
    }

    public RealmTip(RealmTip copy) {
        this.id = copy.getId();
        this.tipText = copy.getTipText();
        this.type = copy.getType();
        this.childId = copy.getChildId();
    }


    public String getTipText() {
        return tipText;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}

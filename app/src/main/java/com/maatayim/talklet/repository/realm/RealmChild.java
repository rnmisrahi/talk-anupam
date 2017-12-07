package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/5/2017
 */

public class RealmChild extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;
    private long birthday;
    private String image;


    public RealmChild(){

    }

    public RealmChild(int id, String name, long birthday, String image) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.image = image;
    }

    public RealmChild(RealmChild child){
        this.id = child.getId();
        this.name = child.getName();
        this.birthday = child.getBirthday();
        this.image = child.getImage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

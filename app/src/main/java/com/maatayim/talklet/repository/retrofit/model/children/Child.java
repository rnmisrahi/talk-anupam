package com.maatayim.talklet.repository.retrofit.model.children;

/**
 * Created by Sophie on 5/28/2017.
 */

public class Child {


    private String id;
    private String name;
    private long birthday;
    private String image;

    public Child(String id, String name, long birthday, String image){

        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

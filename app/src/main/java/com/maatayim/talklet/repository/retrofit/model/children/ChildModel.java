package com.maatayim.talklet.repository.retrofit.model.children;

/**
 * Created by Sophie on 5/28/2017.
 */

public class ChildModel {


    private String id;
    private String name;
    private String birthday;
    private String image;

    public ChildModel(String id, String name, String birthday, String image){

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

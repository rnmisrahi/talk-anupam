package com.maatayim.talklet.repository.retrofit.model.children;

/**
 * Created by Sophie on 5/28/2017
 */

public class ChildModel {


    private int childId;
    private String name;
    private String birthday;
    private String image;

    public ChildModel(int childId, String name, String birthday, String image){

        this.childId = childId;
        this.name = name;
        this.birthday = birthday;
        this.image = image;
    }


    public int getId() {
        return childId;
    }

    public void setId(int id) {
        this.childId = id;
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

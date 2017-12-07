package com.maatayim.talklet.repository.retrofit.model.children;

/**
 * Created by Sophie on 9/19/2017
 */

public class CreateNewChild {


    private String name;
    private String birthday;
    private String image;

    public CreateNewChild(String name, String birthday, String image){

        this.name = name;
        this.birthday = birthday;
        this.image = image;
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

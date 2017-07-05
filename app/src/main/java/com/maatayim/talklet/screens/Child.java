package com.maatayim.talklet.screens;

import android.net.Uri;

import java.util.Date;


/**
 * Created by Sophie on 6/4/2017.
 */

public class Child {

    private final String id;
    private final String name;
    private Date birthday;
    private final String img;
    private boolean isSelected;
    private int count;
    private int total;

    public Child(String id, String name, Date birthday, String img, boolean isSelected){

        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.img = img;
        this.isSelected = isSelected;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }
}

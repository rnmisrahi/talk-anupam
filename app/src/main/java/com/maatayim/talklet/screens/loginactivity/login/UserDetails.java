package com.maatayim.talklet.screens.loginactivity.login;

/**
 * Created by Sophie on 6/29/2017
 */

public class UserDetails {

    private final String id;
    private final String name;
    private final String lastName;
    private final String birthday;
    private String email;
    private String gender;
    private String language1;
    private String language2;
    private String language3;

    public UserDetails(String id, String name, String lastName, String birthday,
                       String email, String gender){


        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.language1 = null;
        this.language2 = null;
        this.language3 = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLanguage1() {
        return language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public String getLanguage3() {
        return language3;
    }


    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public void setLanguage3(String language3) {
        this.language3 = language3;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }
}

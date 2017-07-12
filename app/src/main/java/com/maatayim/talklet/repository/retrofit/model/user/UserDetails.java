package com.maatayim.talklet.repository.retrofit.model.user;

import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutUserObj;

/**
 * Created by Sophie on 7/12/2017
 */

public class UserDetails {

    private String profileId;
    private final String firstName;
    private final String lastName;
    private final String birthday;
    private String email;
    private String gender;
    private String language1;
    private String language2;
    private String language3;

    public UserDetails(String profileId, String firstName, String lastName, String birthday, String email, String gender, String language1, String language2, String language3) {

        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.language1 = language1;
        this.language2 = language2;
        this.language3 = language3;
    }


    public UserDetails(com.maatayim.talklet.screens.loginactivity.login.UserDetails userDetails){
        this.profileId = userDetails.getProfileId();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
        this.birthday = userDetails.getBirthday();
        this.email = userDetails.getEmail();
        this.gender = userDetails.getGender();
        this.language1 = userDetails.getLanguage1();
        this.language2 = userDetails.getLanguage2();
        this.language3 = userDetails.getLanguage3();
    }

    public UserDetails(AboutUserObj aboutUserObj){
        this.profileId = null;
        this.firstName = aboutUserObj.getFirstName();
        this.lastName = aboutUserObj.getLastName();
        this.birthday = String.valueOf(aboutUserObj.getBirthday().getTime());
        this.email = null;
        this.gender = null;
        this.language1 = aboutUserObj.getLanguage1();
        this.language2 = aboutUserObj.getLanguage2();
        this.language3 = aboutUserObj.getLanguage3();
    }





    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
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

    public String getProfileId() {
        return profileId;
    }
}

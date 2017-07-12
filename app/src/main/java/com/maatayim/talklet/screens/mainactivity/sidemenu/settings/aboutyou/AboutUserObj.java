package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;

import java.util.Date;

/**
 * Created by Sophie on 7/12/2017
 */

public class AboutUserObj {

    private final String firstName;
    private final String lastName;
    private final Date birthday;
    private final String languageOne;
    private final String languageTwo;
    private final String languageThree;

    public AboutUserObj(String firstNameStr, String lastNameStr, Date birthdayStr, String languageOne, String languageTwo, String languageThree){

        this.firstName = firstNameStr;
        this.lastName = lastNameStr;
        this.birthday = birthdayStr;
        this.languageOne = languageOne;
        this.languageTwo = languageTwo;
        this.languageThree = languageThree;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayStr() {
        return String.valueOf(birthday.getTime());
    }

    public String getLanguage1() {
        return languageOne;
    }

    public String getLanguage2() {
        return languageTwo;
    }

    public String getLanguage3() {
        return languageThree;
    }
}

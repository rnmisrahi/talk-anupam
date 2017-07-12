package com.maatayim.talklet.repository.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sophie on 7/12/2017
 */

public class RealmUser extends RealmObject {

    @PrimaryKey
    private String id;

    private String firstName;
    private String lastName;
    private String birthday;
    private String language1;
    private String language2;
    private String language3;




    public RealmUser() {
        this.id = "0";
    }


    public RealmUser(String firstName, String lastName, String birthday, String languageOne, String languageTwo, String languageThree) {
        this.id = "0";
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.language1 = languageOne;
        this.language2 = languageTwo;
        this.language3 = languageThree;

    }

    public RealmUser(RealmUser user){
        this.id = "0";
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthday = user.getBirthday();
        this.language1 = user.getLanguage1();
        this.language2 = user.getLanguage2();
        this.language3 = user.getLanguage3();


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

    public String getLanguage1() {
        return language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public String getLanguage3() {
        return language3;
    }

    public String getId() {
        return id;
    }
}

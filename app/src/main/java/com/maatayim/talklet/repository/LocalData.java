package com.maatayim.talklet.repository;

import android.net.Uri;
import android.support.v4.util.Pair;

import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipTicket;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Sophie on 5/28/2017.
 */

public class LocalData {
    //temp child DB
    String babysName;
    Date birthday;



//    public static final String TEMP_TOKEN = "EAALUm1y1RtcBAAfZCaA91aV9yvbKZCW940qo8gVdGSe1TkVNEgnaRnQt4dgiJft1hvNSs6EfPkLpKqg4MdMLzbT5Api0jY1C9wFP7EmuiJVHf8KejYZBcZAwZAF64wpvfxrZAS5YE2wBbV6SzVyP2gZAOXfsET4JDZCebcF9YJliRw0nCaFZBn24f0mUgYSJ45ql73w1o0YsF7oDMY4RLcC4Q";
    public static final String TEMP_TOKEN = "TEST_TOKEN";
    private Uri babysPhoto = null;
    private static LoginResult loginToken;


    public Completable savePersonalSignupDetails(String name, Date birthday) {
        this.babysName = name;
        this.birthday = birthday;
        return Completable.complete();
    }

    public Completable saveBabysPhoto(Uri photo) {
        this.babysPhoto = photo; //temp
        return Completable.complete();
    }


    public Completable saveFacebookLoginToken(LoginResult loginResult) {
        loginToken = loginResult;
        return Completable.complete();
    }


//    Getters

    public Observable<String> getName(String id) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Sophie";
            }
        });
    }

    public Observable<Date> getBirthday(String id) {
        return Observable.fromCallable(new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return new Date();
            }
        });
    }

    public Observable<Uri> getBaybsPhoto(String id) {
        return Observable.fromCallable(new Callable<Uri>() {
            @Override
            public Uri call() throws Exception {
                return babysPhoto;
            }
        });
    }


    public Observable<List<GeneralTipTicket>> getTipsList() {
        return Observable.fromCallable(new Callable<List<GeneralTipTicket>>() {
            @Override
            public List<GeneralTipTicket> call() throws Exception {
                return generalTipList();
            }
        });
    }

    private List<GeneralTipTicket> generalTipList() {
        List<GeneralTipTicket> tipsList = new ArrayList<>();
        tipsList.add(new GeneralTipTicket("Try to ask about the kids day", false));
        tipsList.add(new GeneralTipTicket("bla bla bla bla", true));
        tipsList.add(new GeneralTipTicket("aaaaa aaaaaaaaa aaaaaaaaa", false));
        tipsList.add(new GeneralTipTicket("bbbbbbbbbbbbbbbbbbb bbb bbbb", true));

        return tipsList;
    }


    public Observable<String> getFacebookLoginToken() {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                return loginToken.getAccessToken().getToken();
                return TEMP_TOKEN;
            }
        });
    }

    public Observable<Pair<Integer, Integer>> getSaidWordsCount(String id) {

        return Observable.fromCallable(new Callable<Pair<Integer, Integer>>() {
            @Override
            public Pair<Integer, Integer> call() throws Exception {
                return new Pair<>(new Integer(403), new Integer(500));
            }
        });
    }

    public Observable<List<Child>> getChildrenList() {
        return Observable.fromCallable(new Callable<List<Child>>() {
            @Override
            public List<Child> call() throws Exception {
                return mockChildrenList();
            }
        });
    }

    private List<Child> mockChildrenList() {
        List<Child> childrenList = new ArrayList<>();
        childrenList.add(new Child("1111", babysName, birthday, babysPhoto));
        return childrenList;
    }
}

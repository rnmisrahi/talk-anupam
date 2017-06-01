package com.maatayim.talklet.repository;

import android.net.Uri;

import com.maatayim.talklet.screens.general.generalticket.GeneralTipTicket;
import com.facebook.AccessToken;
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

    private Uri babysPhoto = null;
    private LoginResult loginToken;


    public Completable savePersonalSignupDetails(String name, Date birthday) {
        return Completable.complete();
    }

    public Completable saveBabysPhoto(Uri photo) {
        babysPhoto = photo; //temp
        return Completable.complete();
    }



    public Completable saveFacebookLoginToken(LoginResult loginResult) {
        this.loginToken = loginResult;
        return Completable.complete();
    }



//    Getters

    public Observable<String> getName(long id) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Sophie";
            }
        });
    }

    public Observable<Date> getBirthday(long id) {
        return Observable.fromCallable(new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return new Date();
            }
        });
    }

    public Observable<Uri> getBaybsPhoto(long id) {
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


    public Observable<AccessToken> getFacebookLoginToken() {
        return Observable.fromCallable(new Callable<AccessToken>() {
            @Override
            public AccessToken call() throws Exception {
                return loginToken.getAccessToken();
            }
        });
    }
}

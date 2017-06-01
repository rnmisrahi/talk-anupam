package com.maatayim.talklet.repository;

import android.net.Uri;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.general.generalticket.GeneralTipTicket;
import com.facebook.AccessToken;
import com.facebook.login.LoginResult;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Sophie on 5/24/2017.
 */

public class RepositoryImpl implements BaseContract.Repository {

    private LocalData localRepo;
    private RemoteData remoteRepo;

    @Inject
    public RepositoryImpl(LocalData localRepo, RemoteData remoteRepo){
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;

    }

//    Saves

    public Completable saveSignupDetails(String name, Date birthday) {
        return localRepo.savePersonalSignupDetails(name, birthday);
    }


    @Override
    public Completable saveBabysPhoto(Uri photo) {
        return localRepo.saveBabysPhoto(photo);
    }

    @Override
    public Completable saveFacebookLoginToken(LoginResult loginResult) {
        return localRepo.saveFacebookLoginToken(loginResult);
    }




//    Getters

    @Override
    public Observable<String> getName(long id) {
        return localRepo.getName(id);
    }

    @Override
    public Observable<Date> getBirthday(long id) {
        return localRepo.getBirthday(id);
    }

    @Override
    public Observable<Uri> getBaybsPhoto(long id) {
        return localRepo.getBaybsPhoto(id);
    }

    @Override
    public Observable<List<GeneralTipTicket>> getTipsList() {
        return localRepo.getTipsList();
    }

    @Override
    public Observable<AccessToken> getFacebookLoginToken() {
        return localRepo.getFacebookLoginToken();
    }
}

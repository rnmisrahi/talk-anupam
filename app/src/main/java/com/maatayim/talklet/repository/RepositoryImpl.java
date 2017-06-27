package com.maatayim.talklet.repository;

import android.net.Uri;
import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
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
        return localRepo.savePersonalSignUpDetails(name, birthday);
    }


    @Override
    public Completable saveBabysPhoto(Uri photo) {
        return localRepo.saveBabysPhoto(photo);
    }

    @Override
    public Completable saveFacebookLoginToken(LoginResult loginResult) {
        return localRepo.saveFacebookLoginToken(loginResult);
    }

    @Override
    public Observable<Boolean> checkIfSignedUp() {
        return localRepo.checkIfSignedUp();
    }

    @Override
    public Observable<List<RecordingObj>> getRecordings(String id) {
        return localRepo.getRecordings(id);
    }

    @Override
    public Observable<WordsCount> getTotalWordsCount(String id) {
        return localRepo.getTotalWordsCount(id);
    }

    @Override
    public Observable<List<CalendarWordsObj>> getCalendarData(String id) {
        return localRepo.getCalendarData(id);
    }

    @Override
    public Observable<List<String>> getLanguageList() {
        return localRepo.getLanguagesList();
    }

    @Override
    public Observable<List<FourWordsObj>> getFavoritesWords(String id) {
        return localRepo.getFavoriteWords(id);
    }

    @Override
    public Observable<List<SpecialWords>> getNewWords(String id) {
        return localRepo.getNewWords(id);
    }

    @Override
    public Observable<List<SpecialWords>> getAdvanceWords(String id) {
        return localRepo.getAdvanceWords(id);
    }

    @Override
    public Observable<List<SpecialWords>> getOtherWords(String id) {
        return localRepo.getOtherWords(id);
    }


//    Getters

    @Override
    public Observable<Child> getChild(String id) {
        return localRepo.getChild(id);
    }


    @Override
    public Observable<String> getName(String id) {
        return localRepo.getName(id);
    }

    @Override
    public Observable<Date> getBirthday(String id) {
        return localRepo.getBirthday(id);
    }

    @Override
    public Observable<Uri> getBaybsPhoto(String id) {
        return localRepo.getBaybsPhoto(id);
    }

    @Override
    public Observable<List<TipTicket>> getTipsList(String id) {
        return localRepo.getTipsList();
    }

    @Override
    public Observable<Pair<Integer, Integer>> getWordsCount(String id) {
        return localRepo.getSaidWordsCount(id);
    }

    @Override
    public Observable<String> getFacebookLoginToken() {
        return localRepo.getFacebookLoginToken();
    }

    @Override
    public Observable<List<Child>> getChildrenList() {
        return localRepo.getChildrenList();
    }

    @Override
    public Observable<Child> getLastConnectionChild() {
        return localRepo.getLastConnectionChild();
    }
}

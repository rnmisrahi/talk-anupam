package com.maatayim.talklet.repository;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.repository.realm.RealmTip;
import com.maatayim.talklet.repository.retrofit.model.children.ChildModel;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.general.Tip;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.loginactivity.login.UserDetails;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Sophie on 5/24/2017
 */

public class RepositoryImpl implements BaseContract.Repository {

    private LocalData localRepo;
    private RemoteData remoteRepo;

    @Inject
    public RepositoryImpl(LocalData localRepo, RemoteData remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;

    }

//    Saves

    public Completable saveSignupChildDetails(String name, Date birthday) {
        return localRepo.savePersonalSignUpDetails(name, birthday);
    }


    @Override
    public Completable saveBabysPhoto(String photo) {
        return localRepo.saveBabysPhoto(photo);
    }

    @Override
    public Completable saveFacebookLoginToken(LoginResult loginResult) {
        return localRepo.saveFacebookLoginToken(loginResult);
    }

    @Override
    public Completable saveUserFBDetails(UserDetails userDetails) {
        return localRepo.saveUserFBDetails(userDetails);
    }

    @Override
    public Single<LoginResponse> login() {
        return localRepo.getFacebookId().flatMap(new Function<String, SingleSource<LoginResponse>>() {
            @Override
            public SingleSource<LoginResponse> apply(@NonNull String facebookId) throws Exception {
                return remoteRepo.login(facebookId);
            }
        });
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
    public Observable<String> getBaybsPhoto(String id) {
        return localRepo.getBaybsPhoto(id);
    }

    @Override
    public Observable<List<TipTicket>> getTipsList(String id) {
        return null;//localRepo.getTipsListRx(id);
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
    public Single<List<MainScreenChild>> getChildrenListWithTips() {
        return localRepo.getChildrenList()
                .flatMapObservable(new Function<List<Child>, ObservableSource<Child>>() {
                    @Override
                    public ObservableSource<Child> apply(@NonNull List<Child> children) throws Exception {
                        return Observable.fromIterable(children);
                    }
                })
                .flatMap(new Function<Child, ObservableSource<MainScreenChild>>() {
                    @Override
                    public ObservableSource<MainScreenChild> apply(@NonNull final Child child) throws Exception {
                        return localRepo.getTipsListRx(child.getId()).map(new Function<List<RealmTip>, MainScreenChild>() {
                            @Override
                            public MainScreenChild apply(@NonNull List<RealmTip> realmTips) throws Exception {
                                List<MainScreenChild.Tip> tips = new ArrayList<>();
                                for (RealmTip realmTip : realmTips) {
                                    tips.add(new MainScreenChild.Tip(realmTip.getTipText(), realmTip.isAssertion()));
                                }
                                return new MainScreenChild(child.getId(), child.getImg(), child.getCount(), child.getTotal(), tips);
                            }
                        });
                    }
                })
                .toList();


    }

    @Override
    public Single<List<Child>> getChildrenList() {

        return localRepo.getChildrenList();
    }

    @Override
    public Observable<Child> getLastConnectionChild() {
        return localRepo.getLastConnectionChild();
    }


    @Override
    public Observable<UserDetails> getUserDetails() {
        return localRepo.getUserDetails();
    }


    @Override
    public Observable<List<TipTicket>> getAllChildrenTips() {
        return null;
    }


    public Completable downloadKids() {
        return localRepo.getToken()
                .flatMap(new Function<String, SingleSource<ChildrenListWrapper>>() {
                    @Override
                    public SingleSource<ChildrenListWrapper> apply(@NonNull String token) throws Exception {
                        return remoteRepo.downloadKids(token);
                    }
                })
                .flatMapObservable(new Function<ChildrenListWrapper, ObservableSource<ChildModel>>() {
                    @Override
                    public ObservableSource<ChildModel> apply(@NonNull ChildrenListWrapper childrenListWrapper) throws Exception {
                        return Observable.fromIterable(childrenListWrapper.getChildren());
                    }
                })
                .flatMapCompletable(new Function<ChildModel, Completable>() {
                    @Override
                    public Completable apply(@NonNull ChildModel childModel) throws Exception {
                        return localRepo.saveChildRx(childModel);
                    }
                });

    }


    public Completable downloadTips() {
        return localRepo.getToken()
                .flatMap(new Function<String, SingleSource<TipsWrapper>>() {
                    @Override
                    public SingleSource<TipsWrapper> apply(@NonNull String token) throws Exception {
                        return remoteRepo.downloadTips(token);
                    }
                })
                .flatMapObservable(new Function<TipsWrapper, ObservableSource<Tip>>() {
                    @Override
                    public ObservableSource<Tip> apply(@NonNull TipsWrapper tipsWrapper) throws Exception {
                        return Observable.fromIterable(tipsWrapper.getTips());
                    }
                })
                .flatMapCompletable(new Function<Tip, Completable>() {
                    @Override
                    public Completable apply(@NonNull Tip tip) throws Exception {
                        return localRepo.saveTipRx(tip.getId(), tip.getText(), tip.isAssertion(), tip.getChildId());
                    }
                });
    }


//    public void xxx()  //this is in the presenter
//    {
//        downloadKids().subscribeWith(new DisposableSingleObserver<ChildrenListWrapper>() {
//            @Override
//            public void onSuccess(@NonNull ChildrenListWrapper o) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//        });
//    }


}

package com.maatayim.talklet.repository;

import android.content.Context;
import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.repository.retrofit.model.general.Tip;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
import com.maatayim.talklet.repository.retrofit.model.worddata.WordData;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.loginactivity.login.UserDetails;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.GeneralTabChildObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.facebook.login.LoginResult;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
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
    public Completable saveFacebookLoginToken(LoginResult loginResult, Context context) {
        return localRepo.saveFacebookLoginToken(loginResult, context);
    }

    @Override
    public Completable saveUserFBDetails(UserDetails userDetails) {
        return localRepo.saveUserFBDetails(userDetails);
    }

    @Override
    public Single<LoginResponse> login(Context context) {
        return localRepo.getFacebookIdRx(context).flatMap(new Function<String, SingleSource<LoginResponse>>() {
            @Override
            public SingleSource<LoginResponse> apply(String facebookId) throws Exception {
                if (facebookId.isEmpty()){
                    return Single.just(new LoginResponse(null, false));
                }else{
                    return remoteRepo.login(facebookId);
                }
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

//    @Override
//    public Single<RealmChild> getChild(String id) {
//        return localRepo.getChildRx(id);
//    }


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
        return localRepo.getChildrenListRx()
                .flatMapObservable(Observable::fromIterable)
                .map(realmChild -> new MainScreenChild(realmChild.getId(), realmChild.getImage(), 0, 0, null))
                .flatMapSingle(child -> localRepo.getTipsListRx(child.getId())
                        .flatMapObservable(Observable::fromIterable)
                        .map(realmTip -> new MainScreenChild.Tip(realmTip.getTipText(), true))  //// TODO: 7/9/2017 get assertion
                        .toList()
                        .map(tipTickets -> {
                            child.setTips(tipTickets);
                            return child;
                        }))
                .toList();
    }

    @Override
    public Single<GeneralTabChildObj> getChildTipsAndWords(String id) {
        return localRepo.getChildRx(id)
                .map(realmChild -> new GeneralTabChildObj(realmChild.getName(), null, null, null, realmChild.getImage()))
                .flatMap(generalTabChildObj -> localRepo.getTipsListRx(id)
                        .flatMapObservable(Observable::fromIterable)
                        .map(Mapper.getTipMapperFunction(generalTabChildObj.getChildPhoto()))
                        .toList()
                        .map(tipTickets -> {
                            generalTabChildObj.setTips(tipTickets);
                            return generalTabChildObj;
                        })
                )
                .flatMap(generalTabChildObj -> localRepo.getWordsOfTheDayListRx(id)
                        .flatMapObservable(Observable::fromIterable)
                        .map(Mapper.getWordOfTheDayMapperFunction())
                        .toList()
                        .map(daysWordsObjs -> {
                            generalTabChildObj.setWordsOfTheDay(daysWordsObjs);
                            return generalTabChildObj;

                        }));
    }

    @Override
    public Completable logout(Context context) {
        return localRepo.logout(context);
    }


    @Override
    public Single<Child> getChild(String id) {
        return localRepo.getChildRx(id)
                .map(realmChild -> new Child(realmChild.getId(), realmChild.getName(), new Date(realmChild.getBirthday()), realmChild.getImage(), false));
    }


    @Override
    public Single<List<Child>> getChildrenList() {
        return null;
//        return localRepo.getChildrenListRx();
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
                .flatMap(token -> remoteRepo.downloadKids(token))
                .flatMapObservable(childrenListWrapper -> Observable.fromIterable(childrenListWrapper.getChildren()))
                .flatMapCompletable(childModel -> localRepo.saveChildRx(childModel));
    }


    public Completable downloadTips() {
        return localRepo.getToken()
                .flatMap(token -> remoteRepo.downloadTips(token))
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


    public Completable downloadWordsOfTheDay(final String childId) {
        return localRepo.getToken()
                .flatMap(new Function<String, Single<WordData>>() {
                    @Override
                    public Single<WordData> apply(@NonNull String token) throws Exception {
                        return remoteRepo.downloadWordsOfTheDay(childId, token);
                    }
                })
                .flatMapObservable(wordData -> Observable.fromIterable(wordData.getWordsOfTheDay()))
                .flatMapCompletable(word -> localRepo.saveWordOfDayRx(word.getId(), word.getWord(), word.getSubText(), word.getChildId(), word.getTopic()));
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

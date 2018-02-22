package com.maatayim.talklet.repository;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.Log;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.repository.realm.RealmToken;
import com.maatayim.talklet.repository.retrofit.model.children.CreateNewChild;
import com.maatayim.talklet.repository.retrofit.model.user.LoginFacebookResponse;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.loginactivity.login.UserDetails;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv
        .CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.GeneralTabChildObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.facebook.login.LoginResult;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingChild;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutUserObj;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.maatayim.talklet.repository.Mapper.mapRealmRecordingListToRecordsObj;
import static com.maatayim.talklet.utils.Utils.convertLongDateToServerFormat;
import static com.maatayim.talklet.utils.Utils.parserDate;

/**
 * Created by Sophie on 5/24/2017
 */

public class RepositoryImpl implements BaseContract.Repository {

    public static final int EMPTY_ID = -1;

    private LocalData localRepo;

    private RemoteData remoteRepo;

    @Inject
    public RepositoryImpl(LocalData localRepo, RemoteData remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;

    }

//    Saves

    public Completable addChild(String name, Date birthday, String babysPhoto) {

        return localRepo.getTokenRx()
                        .flatMap(token -> remoteRepo.addChild(token.getToken(),
                                new CreateNewChild(name, convertLongDateToServerFormat(birthday
                                        .getTime()), babysPhoto)))
                        .flatMapCompletable(newChild -> localRepo.saveChildRx(newChild));

    }


    @Override
    public Completable saveBabysPhoto(String photo) {
        return localRepo.saveBabysPhoto(photo);
    }

    @Override
    public Completable saveFacebookLoginToken(LoginResult loginResult, Context context) {
        return localRepo.saveFacebookLoginToken(loginResult, context);
    }

    private static final String TAG = "RepositoryImpl";

    @Override
    public Completable saveUserFBDetails(UserDetails userDetails) {

//        userDetails.setLanguage1("English");
//        userDetails.setLanguage2("Russian");
//        userDetails.setLanguage3("Hebrew");


        return localRepo.getTokenRx()
                        .flatMapCompletable(token -> remoteRepo.sendUsersData(token.getToken(),
                                new com.maatayim.talklet.repository.retrofit.model.user
                                        .UserDetails(userDetails)))
                        .andThen(localRepo.updateUsersDataRx(userDetails.getFirstName(), userDetails
                                        .getLastName(),
                                userDetails.getBirthday(), userDetails.getLanguage1(),
                                userDetails.getLanguage2(),
                                userDetails.getLanguage3()))
                ;

    }

    @Override
    public Single<Boolean> checkIfSignedUp(Context context) {


        return localRepo.isSignedUp()
                        .flatMap(aBoolean -> {
                            if (!aBoolean) {
                                return localRepo.getTokenRx().flatMap(realmToken -> remoteRepo
                                        .isSignedUp(realmToken.getToken()));
                            }
                            return Single.just(true);
                        });


//        return localRepo.getTokenRx()
//
//                        .flatMap(realmToken -> checkIfSignedUp(realmToken))
//                .


//        return localRepo.getFacebookIdRx(context)
//                        .flatMap(facebookId -> {
//                            if (facebookId.isEmpty()) {
//                                return Single.just(new LoginFacebookResponse(null, false));
//                            } else {
//                                return remoteRepo.loginWithFacebook(facebookId)
//                                                 .flatMap(loginResponse -> localRepo
//                                                         .saveTokenRx(loginResponse.getToken())
//                                                         .andThen(Single.just(loginResponse)));
//                            }
//                        });

    }


    @Override
    public Observable<List<RecordingObj>> getRecordings(int id) {
        return localRepo.getRecordings(id);
    }

    @Override
    public Observable<WordsCount> getTotalWordsCount(int id) {
        return localRepo.getTotalWordsCount(id);
    }

    @Override
    public Observable<List<CalendarWordsObj>> getCalendarData(int id) {
        return localRepo.getCalendarData(id);
    }

    @Override
    public Observable<List<String>> getLanguageList() {
        return localRepo.getLanguagesList();
    }

    @Override
    public Observable<List<FourWordsObj>> getFavoritesWords(int id) {
        return localRepo.getFavoriteWords(id);
    }

    @Override
    public Observable<List<SpecialWords>> getNewWords(int id) {
        return localRepo.getNewWords(id);
    }

    @Override
    public Observable<List<SpecialWords>> getAdvanceWords(int id) {
        return localRepo.getAdvanceWords(id);
    }

    @Override
    public Observable<List<SpecialWords>> getOtherWords(int id) {
        return localRepo.getOtherWords(id);
    }


//    Getters

//    @Override
//    public Single<RealmChild> getChild(String id) {
//        return localRepo.getChildRx(id);
//    }


    @Override
    public Observable<String> getName(int id) {
        return localRepo.getName(id);
    }

    @Override
    public Observable<Date> getBirthday(int id) {
        return localRepo.getBirthday(id);
    }

    @Override
    public Observable<String> getBaybsPhoto(int id) {
        return localRepo.getBaybsPhoto(id);
    }

    @Override
    public Observable<List<TipTicket>> getTipsList(int id) {
        return null;//localRepo.getTipsListRx(id);
    }

    @Override
    public Observable<Pair<Integer, Integer>> getWordsCount(int id) {
        return localRepo.getSaidWordsCount(id);
    }

    @Override
    public Observable<String> getFacebookLoginToken() {
        return localRepo.getFacebookLoginToken();
    }

    @Override
    public Single<List<MainScreenChild>> getMainScreenChildrenList() {
        return localRepo.getChildrenListRx()
                        .flatMapObservable(Observable::fromIterable)
                        .map(realmChild -> new MainScreenChild(String.valueOf(realmChild.getId()), realmChild
                                .getImage(), 0, 0, null))
                        .flatMapSingle(child -> localRepo.getTipsListRx(Integer.parseInt(child.getId()))
                                                         .flatMapObservable
                                                                 (Observable::fromIterable)
                                                         .map(realmTip -> new MainScreenChild.Tip
                                                                 (realmTip
                                                                         .getTipText(), realmTip
                                                                         .getType()))
                                                         .toList()
                                                         .map(tipTickets -> {
                                                             child.setTips(tipTickets);
                                                             return child;
                                                         }))
                        .flatMapSingle(child -> localRepo.getWordsCountRx(Integer.parseInt(child.getId()))
                                                         .map(countData -> {
                                                             child.setWordCount(countData
                                                                     .getWordCount());
                                                             child.setTotal(countData
                                                                     .getExpectedWordCount());
                                                             return child;
                                                         })
                        ).toList();
    }

    @Override
    public Single<GeneralTabChildObj> getChildTipsAndWords(int id) {
        return localRepo.getChildRx(id)
                        .map(realmChild -> new GeneralTabChildObj(realmChild.getName(), null,
                                null, null, realmChild
                                .getImage()))
                        .flatMap(generalTabChildObj -> localRepo.getTipsListRx(id)
                                                                .flatMapObservable
                                                                        (Observable::fromIterable)
                                                                .map(Mapper.getTipMapperFunction
                                                                        (generalTabChildObj
                                                                                .getChildPhoto()))
                                                                .toList()
                                                                .map(tipTickets -> {
                                                                    generalTabChildObj.setTips
                                                                            (tipTickets);
                                                                    return generalTabChildObj;
                                                                })
                        )
                        .flatMap(generalTabChildObj -> localRepo.getWordsOfTheDayListRx(id)
                                                                .flatMapObservable
                                                                        (Observable::fromIterable)
                                                                .map(Mapper
                                                                        .getWordOfTheDayMapperFunction())
                                                                .toList()
                                                                .map(daysWordsObjs -> {
                                                                    generalTabChildObj
                                                                            .setWordsOfTheDay
                                                                                    (daysWordsObjs);
                                                                    return generalTabChildObj;

                                                                }));
    }

    @Override
    public Completable logout(Context context) {
        return localRepo.logout(context);
    }

    @Override
    public Completable updateUsersData(AboutUserObj aboutUserObj) {

        return localRepo.getTokenRx()
                        .flatMapCompletable(token -> remoteRepo.sendUsersData(token.getToken(),
                                new com.maatayim.talklet.repository.retrofit.model.user
                                        .UserDetails(aboutUserObj)))
                        .andThen(localRepo.updateUsersDataRx(aboutUserObj.getFirstName(),
                                aboutUserObj
                                        .getLastName(),
                                String.valueOf(aboutUserObj.getBirthday()
                                                           .getTime()), aboutUserObj.getLanguage1(),
                                aboutUserObj.getLanguage2(), aboutUserObj.getLanguage3()));


    }

    @Override
    public Single<Integer> getNumOfChildren() {
        return localRepo.countChildren();
    }

    @Override
    public Single<Boolean> checkIfTokenFound(Context context) {
        return localRepo.getTokenRx()
                        .flatMap((Function<RealmToken, SingleSource<? extends Boolean>>)
                                realmToken -> {
                            if (realmToken == null) {
                                return Single.just(false);
                            } else {
                                return Single.just(true);
//                                return remoteRepo.verifyToken(realmToken);
                            }
                        });

    }

    @Override
    public Completable sendFacebookID(LoginResult loginResult) {
        return remoteRepo.loginWithFacebook(loginResult.getAccessToken().getUserId())
                         .observeOn(AndroidSchedulers.mainThread())
                         .doOnSuccess(loginFacebookResponse -> {
                             if (loginFacebookResponse.getToken().equals("")) {
                                 throw new IllegalAccessException("Error 01: Illegal Facebook" +
                                         " ID:");
                             }
                         })
                         .observeOn(Schedulers.io())
                         .flatMapCompletable(loginResponse
                                 ->
                                 localRepo.saveTokenRx(loginResponse.getToken()));

    }


    @Override
    public Single<Child> getChild(int id) {
        return localRepo.getChildRx(id)
                        .map(realmChild -> new Child(realmChild.getId(), realmChild.getName(),
                                new Date(realmChild
                                        .getBirthday()), realmChild.getImage(), false));
    }


    @Override
    public Single<List<SettingChild>> getSettingChildList() {
        return localRepo.getChildrenListRx()
                        .flatMapObservable(Observable::fromIterable)
                        .map(realmChild -> new SettingChild(realmChild.getId(), realmChild
                                .getName()))
                        .toList();
    }

    @Override
    public Observable<Child> getLastConnectionChild() {
        return localRepo.getLastConnectionChild();
    }


    @Override
    public Single<AboutUserObj> getUserDetails() {
        return localRepo.getUserDetailsRx().map(user -> new AboutUserObj(user.getFirstName(),
                user.getLastName(), new Date(Long.valueOf(user.getBirthday())), user.getLanguage1
                (), user
                .getLanguage2(),
                user.getLanguage3()));
    }

    @Override
    public Single<List<CalendarWordsObj>> getChildWordsByDate(int id) {
        return localRepo.getChildRx(id)
                        .flatMapObservable(realmChild -> localRepo.getWordsCountByDateRx
                                        (realmChild.getId())
                                                                  .flatMapObservable
                                                                          (Observable::fromIterable)
                                                                  .map(countData -> new
                                                                          CalendarWordsObj(new
                                                                          Date(countData
                                                                          .getDate()), countData
                                                                          .getWordCount(),
                                                                          countData
                                                                                  .getExpectedWordCount(), false, mapRealmRecordingListToRecordsObj(countData
                                                                          .getRecordings())))
                                //// TODO: 7/23/2017 insert recordings
                        ).toList();
    }


    @Override
    public Observable<List<TipTicket>> getAllChildrenTips() {
        return null;
    }


    public Completable downloadKids() {
        return localRepo.getTokenRx()
                        .flatMap(token -> remoteRepo.downloadKids(token.getToken()))
                        .flatMapObservable(childrenListWrapper -> Observable.fromIterable
                                (childrenListWrapper
                                        .getChildren()))
                        .flatMapCompletable(childModel -> localRepo.saveChildRx(childModel));
    }


    public Completable downloadTips() {
        return localRepo.getTokenRx()
                        .flatMap(token -> remoteRepo.downloadTips(token.getToken()))
                        .doOnSuccess(tipsWrapper -> Log.d("a", "A"))
                        .flatMapObservable(tipsWrapper -> Observable.fromIterable(tipsWrapper
                                .getTips()))
                        .flatMapCompletable(tip -> localRepo.saveTipRx(tip.getId(), tip.getText()
                                , tip
                                        .getType(), tip
                                        .getChildId()));
    }


    public Completable downloadCountData() {
        return localRepo.getTokenRx()
                        .flatMap(token -> remoteRepo.downloadAllWordCountData(token.getToken()))
                        .flatMapObservable(allWordCountResponse -> Observable.fromIterable
                                (allWordCountResponse
                                        .getWordCountList()))
                        .flatMap(childWordModel -> Observable.fromIterable(childWordModel.getDays
                                ()))
                        .flatMapCompletable(dailyWords -> localRepo.saveCountDataRx(dailyWords
                                        .getId(), dailyWords
                                        .getChildId(),
                                dailyWords.getWordCount(), dailyWords.getExpectedWordCount(),
                                parserDate(dailyWords
                                        .getDate()), dailyWords.getRecordings()));
    }


    public Completable downloadWordsOfTheDay(final int childId) {
        return localRepo.getTokenRx()
                        .flatMap(token -> remoteRepo.downloadWordsOfTheDay(childId, token
                                .getToken()))
                        .flatMapObservable(wordData -> Observable.fromIterable(wordData
                                .getWordsOfTheDay()))
                        .flatMapCompletable(word -> localRepo.saveWordOfDayRx(word.getId(), word
                                        .getWord(), word
                                        .getSubText(), word.getChildId(), word.getTopic(),
                                word.getInfoList(), word.getQuestionList(), word
                                        .getActivitiesList(), word
                                        .getOurFaveList()));
    }


}

package com.maatayim.talklet.baseline;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;

import com.maatayim.talklet.repository.realm.RealmChild;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
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
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutUserObj;

import java.util.Date;
import java.util.List;

import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Sophie on 5/21/2017.
 */

@Module
public interface BaseContract {

    interface View {
    }

    interface Presenter {
    }

    interface Repository
    {
        Completable saveSignupChildDetails(String name, Date birthday);
        Completable saveBabysPhoto(String photo);
        Completable saveFacebookLoginToken(LoginResult loginResult, Context context);
        Completable saveUserFBDetails(UserDetails userDetails);


        Single<Child> getChild(String id);
        Observable<String> getName(String id);
        Observable<Date> getBirthday(String id);
        Observable<String> getBaybsPhoto(String id);
        Observable<List<TipTicket>> getTipsList(String id);
        Observable<Pair<Integer, Integer>> getWordsCount(String id);
        Observable<String> getFacebookLoginToken();
        Single<List<MainScreenChild>> getChildrenListWithTips();
        Single<List<Child>> getChildrenList();
        Observable<Child> getLastConnectionChild();
        Single<LoginResponse> login(Context context);


        Observable<List<RecordingObj>> getRecordings(String id);

        Observable<WordsCount> getTotalWordsCount(String id);

        Observable<List<CalendarWordsObj>> getCalendarData(String id);

        Observable<List<String>> getLanguageList();

        Observable<List<FourWordsObj>> getFavoritesWords(String id);

        Observable<List<SpecialWords>> getNewWords(String id);

        Observable<List<SpecialWords>> getAdvanceWords(String id);

        Observable<List<SpecialWords>> getOtherWords(String id);

        Single<AboutUserObj> getUserDetails();



        Observable<List<TipTicket>> getAllChildrenTips();

//        Single<ChildrenListWrapper> downloadKids();
        Completable downloadKids();

        Completable downloadTips();

        Completable downloadWordsOfTheDay(final String childId);


        Single<GeneralTabChildObj> getChildTipsAndWords(String id);

        Completable logout(Context context);

        Completable updateUsersData(AboutUserObj aboutUserObj);
    }



}

package com.maatayim.talklet.baseline;

import android.content.Context;
import android.support.v4.util.Pair;

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
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingChild;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutUserObj;

import java.util.Date;
import java.util.List;

import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Sophie on 5/21/2017
 */

@Module
public interface BaseContract {

    interface View {
    }

    interface Presenter {
    }

    interface Repository
    {
        Completable addChild(String name, Date birthday, String babysPhoto);
        Completable saveBabysPhoto(String photo);
        Completable saveFacebookLoginToken(LoginResult loginResult, Context context);
        Completable saveUserFBDetails(UserDetails userDetails);


        Single<Child> getChild(int id);
        Observable<String> getName(int id);
        Observable<Date> getBirthday(int id);
        Observable<String> getBaybsPhoto(int id);
        Observable<List<TipTicket>> getTipsList(int id);
        Observable<Pair<Integer, Integer>> getWordsCount(int id);
        Observable<String> getFacebookLoginToken();
        Single<List<MainScreenChild>> getMainScreenChildrenList();
        Single<List<SettingChild>> getSettingChildList();
        Observable<Child> getLastConnectionChild();
        Single<Boolean> checkIfSignedUp(Context context);


        Observable<List<RecordingObj>> getRecordings(int id);

        Observable<WordsCount> getTotalWordsCount(int id);

        Observable<List<CalendarWordsObj>> getCalendarData(int id);

        Observable<List<String>> getLanguageList();

        Observable<List<FourWordsObj>> getFavoritesWords(int id);

        Observable<List<SpecialWords>> getNewWords(int id);

        Observable<List<SpecialWords>> getAdvanceWords(int id);

        Observable<List<SpecialWords>> getOtherWords(int id);

        Single<AboutUserObj> getUserDetails();

        Single<List<CalendarWordsObj>> getChildWordsByDate(int id);

        Observable<List<TipTicket>> getAllChildrenTips();

//        Single<ChildrenListWrapper> downloadKids();
        Completable downloadKids();

        Completable downloadTips();

        Completable downloadCountData();

        Completable downloadWordsOfTheDay(final int childId);


        Single<GeneralTabChildObj> getChildTipsAndWords(int id);

        Completable logout(Context context);

        Completable updateUsersData(AboutUserObj aboutUserObj);

        Single<Integer> getNumOfChildren();

        Single<Boolean> checkIfTokenFound(Context context);

        Completable sendFacebookID(LoginResult loginResult);
    }



}

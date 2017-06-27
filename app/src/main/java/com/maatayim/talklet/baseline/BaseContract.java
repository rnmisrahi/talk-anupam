package com.maatayim.talklet.baseline;

import android.net.Uri;
import android.support.v4.util.Pair;

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

import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;

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
        Completable saveSignupDetails(String name, Date birthday);
        Completable saveBabysPhoto(Uri photo);
        Completable saveFacebookLoginToken(LoginResult loginResult);


        Observable<Child> getChild(String id);
        Observable<String> getName(String id);
        Observable<Date> getBirthday(String id);
        Observable<Uri> getBaybsPhoto(String id);
        Observable<List<TipTicket>> getTipsList(String id);
        Observable<Pair<Integer, Integer>> getWordsCount(String id);
        Observable<String> getFacebookLoginToken();
        Observable<List<Child>> getChildrenList();
        Observable<Child> getLastConnectionChild();
        Observable<Boolean> checkIfSignedUp();


        Observable<List<RecordingObj>> getRecordings(String id);

        Observable<WordsCount> getTotalWordsCount(String id);

        Observable<List<CalendarWordsObj>> getCalendarData(String id);

        Observable<List<String>> getLanguageList();

        Observable<List<FourWordsObj>> getFavoritesWords(String id);

        Observable<List<SpecialWords>> getNewWords(String id);

        Observable<List<SpecialWords>> getAdvanceWords(String id);

        Observable<List<SpecialWords>> getOtherWords(String id);
    }



}

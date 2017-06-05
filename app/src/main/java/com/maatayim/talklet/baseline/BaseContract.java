package com.maatayim.talklet.baseline;

import android.net.Uri;
import android.support.v4.util.Pair;

import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipTicket;
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

        Observable<String> getName(String id);
        Observable<Date> getBirthday(String id);
        Observable<Uri> getBaybsPhoto(String id);
        Observable<List<GeneralTipTicket>> getTipsList(String id);
        Observable<Pair<Integer, Integer>> getWordsCount(String id);



        Observable<String> getFacebookLoginToken();

        Observable<List<Child>> getChildrenList();
        Observable<Integer> getLastConnectionChild();
    }



}

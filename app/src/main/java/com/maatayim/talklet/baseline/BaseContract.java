package com.maatayim.talklet.baseline;

import android.net.Uri;

import com.maatayim.talklet.screens.general.generalticket.GeneralTipTicket;
import com.facebook.AccessToken;
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

        Observable<String> getName(long id);
        Observable<Date> getBirthday(long id);
        Observable<Uri> getBaybsPhoto(long id);
        Observable<List<GeneralTipTicket>> getTipsList();


        Observable<AccessToken> getFacebookLoginToken();
    }



}

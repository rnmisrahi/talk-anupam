package com.maatayim.talklet.repository;

import com.maatayim.talklet.repository.retrofit.api.BackEndApi;
import com.maatayim.talklet.repository.retrofit.model.children.ChildModel;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.LoginRequest;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.UserDetails;
import com.maatayim.talklet.repository.retrofit.model.wordcountdata.AllWordCountResponse;
import com.maatayim.talklet.repository.retrofit.model.wordcountdata.WordCountResponse;
import com.maatayim.talklet.repository.retrofit.model.worddata.WordData;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutUserObj;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Sophie on 5/28/2017
 */

public class RemoteData {



    Single<LoginResponse> login(String facebookId) {
        return BackEndApi.getApi().login(new LoginRequest(facebookId));
    }


    Single<ChildrenListWrapper> downloadKids(String token) {
        return BackEndApi.getApi().getChildrenList(token);
    }


    public Single<TipsWrapper> downloadTips(String token) {
        return BackEndApi.getApi().getGeneralData();
    }

    public Single<WordData> downloadWordsOfTheDay(String childId, String token) {
        return BackEndApi.getApi().getWordData(childId, token);
    }

//    public Single<WordCountResponse> downloadWordCountData(String childId, String token) {
//        return BackEndApi.getApi().getWordCountData(childId, token);
//    }

    public Single<AllWordCountResponse> downloadAllWordCountData(String token) {
        return BackEndApi.getApi().getAllWordsCountData(token);
    }




    public Completable sendUsersData(String token, UserDetails userDetails) {
        return BackEndApi.getApi().sendUserDetails(token, userDetails);
    }

    public Single<ChildModel> addChild(String token, ChildModel child) {
        return BackEndApi.getApi().postCreateChild(token, child);
    }


}

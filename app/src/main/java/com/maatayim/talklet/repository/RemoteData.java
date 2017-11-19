package com.maatayim.talklet.repository;

import com.maatayim.talklet.repository.retrofit.api.BackEndApi;
import com.maatayim.talklet.repository.retrofit.model.user.SignUpResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildModel;
import com.maatayim.talklet.repository.retrofit.model.children.CreateNewChild;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.LoginFacebookResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.SignedUpRequest;
import com.maatayim.talklet.repository.retrofit.model.user.UserDetails;
import com.maatayim.talklet.repository.retrofit.model.wordcountdata.AllWordCountResponse;
import com.maatayim.talklet.repository.retrofit.model.worddata.WordData;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 5/28/2017
 */

public class RemoteData {

    private static final String TAG = "RemoteData";

//    Single<LoginFacebookResponse> checkIfSignedUp(String facebookId) {
//        return BackEndApi.getApi().checkIfSignedUp(new LoginFacebookRequest(facebookId));
//    }

    Single<LoginFacebookResponse> loginWithFacebook(String facebookId) {
        return BackEndApi.getApi().loginWithFacebook(facebookId);
    }


    Single<ChildrenListWrapper> downloadKids(String token) {
        return BackEndApi.getApi().getChildrenList(token).subscribeOn(Schedulers.io());
    }


    public Single<TipsWrapper> downloadTips(String token) {
        return BackEndApi.getApi().getGeneralData(token).subscribeOn(Schedulers.io());
    }

    public Single<WordData> downloadWordsOfTheDay(int childId, String token) {
        return BackEndApi.getApi().getWordData(String.valueOf(childId), token);
    }

//    public Single<WordCountResponse> downloadWordCountData(String childId, String token) {
//        return BackEndApi.getApi().getWordCountData(childId, token);
//    }

    public Single<AllWordCountResponse> downloadAllWordCountData(String token) {
        return BackEndApi.getApi().getAllWordsCountData(token).subscribeOn(Schedulers.io());
    }


    public Completable sendUsersData(String token, UserDetails userDetails) {
        return BackEndApi.getApi()
                         .sendUserDetails(token, userDetails);
    }

    public Single<ChildModel> addChild(String token, CreateNewChild child) {
        return BackEndApi.getApi().postCreateChild(token, child);
    }


    public Single<Boolean> isSignedUp(String token) {
        return BackEndApi.getApi().getSignedUpStatus(token).map
                (SignUpResponse::getSignedup);
    }
}

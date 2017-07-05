package com.maatayim.talklet.repository;

import com.maatayim.talklet.repository.retrofit.api.BackEndApi;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.user.LoginRequest;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;

import io.reactivex.Single;

/**
 * Created by Sophie on 5/28/2017.
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
}

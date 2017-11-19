package com.maatayim.talklet.repository.retrofit.api;

import com.maatayim.talklet.repository.retrofit.model.user.SignUpResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildModel;
import com.maatayim.talklet.repository.retrofit.model.children.CreateNewChild;
import com.maatayim.talklet.repository.retrofit.model.user.LoginFacebookResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.recording.RecordingNotificationResponse;
import com.maatayim.talklet.repository.retrofit.model.user.SignedUpRequest;
import com.maatayim.talklet.repository.retrofit.model.user.UserDetails;
import com.maatayim.talklet.repository.retrofit.model.wordcountdata.AllWordCountResponse;
import com.maatayim.talklet.repository.retrofit.model.worddata.WordData;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Sophie on 5/28/2017
 */

public interface RetrofitApi {



    @POST("loginFacebook")
    @FormUrlEncoded
    Single<LoginFacebookResponse> loginWithFacebook(
            @Field("facebookid") String facebookId);

    //    Children
    @GET("children")
    Single<ChildrenListWrapper> getChildrenList(
            @Header("Authorization") String authorization
    );



    @POST("children")
    Single<ChildModel> postCreateChild(
            @Header("Authorization") String authorization,
            @Body CreateNewChild body
    );

//    @DELETE("children/{id}")
//    void deleteChild(
//            @Header("Authorization") String authorization
//    );




    @GET("recording")
    Single<RecordingNotificationResponse> getRecordingNotification(
    );


    //    General
    @GET("generaldata")
    Single<TipsWrapper> getGeneralData(
            @Header("Authorization") String authorization
    );



    @GET("worddata/{user}")
    Single<WordData> getWordData(
            @Path("user") String user,
            @Header("Authorization") String authorization);



    @POST("senduserdetails")
    Completable sendUserDetails(
            @Header("Authorization") String authorization,
            @Body UserDetails body
    );

    @GET("allcountdata")
    Single<AllWordCountResponse> getAllWordsCountData(
            @Header("Authorization") String authorization
    );

    @GET("signedup/status")
    Single<SignUpResponse> getSignedUpStatus(
            @Header("Authorization") String authorization
    );



}

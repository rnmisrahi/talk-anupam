package com.maatayim.talklet.repository.retrofit.api;

import com.maatayim.talklet.repository.retrofit.model.user.LoginRequest;
import com.maatayim.talklet.repository.retrofit.model.user.LoginResponse;
import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.general.CreateRecordingRequest;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.recording.RecordingMetadata;
import com.maatayim.talklet.repository.retrofit.model.recording.RecordingNotificationResponse;
import com.maatayim.talklet.repository.retrofit.model.general.ChildDataResponse;
import com.maatayim.talklet.repository.retrofit.model.user.UserDetails;
import com.maatayim.talklet.repository.retrofit.model.wordcountdata.WordCountResponse;
import com.maatayim.talklet.repository.retrofit.model.worddata.WordData;

import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Sophie on 5/28/2017
 */

public interface RetrofitApi {

    //    User
    @POST("login")
    Single<LoginResponse> login(
            @Body LoginRequest body
    );

    //    Children
    @GET("children")
    Single<ChildrenListWrapper> getChildrenList(
            @Header("Authorization") String authorization
    );


    @PUT("children/{id}")
    Single<ChildrenListWrapper> putUpdateChild(
    );

    @POST("children")
    Single<ChildrenListWrapper> postCreateChild(
    );

    @DELETE("children/{id}")
    void deleteChild(
            @Header("Authorization") String authorization
    );


    //    RecordingObj
    @POST("recording")
    void postRecordingMetadata(
            @Header("Authorization") String authorization,
            @Body RecordingMetadata body
    );


    @GET("recording")
    Single<RecordingNotificationResponse> getRecordingNotification(
    );


    //    General
    @GET("generaldata")
    Single<TipsWrapper> getGeneralData();


    @GET("childdata")
    Single<ChildDataResponse> getChildData(
            @Query("child") String child,
            @Header("Authorization") String authorization
    );

    @POST("recordings")
    void postCreateRecording(
            @Header("Authorization") String authorization,
            @Body CreateRecordingRequest body
    );


    //    Word Data
    @GET("worddata")
    Single<WordData> getWordData(
            @Query("child") String child,
            @Header("Authorization") String authorization
    );


    //    Word Count Data
    @GET("wordcountdata")
    Single<WordCountResponse> getWordCountData(
            @Query("child") String child,
            @Header("Authorization") String authorization
    );

    @POST("senduserdetails")
    Completable sendUserDetails(
            @Header("Authorization") String authorization,
            @Body UserDetails body
    );
}

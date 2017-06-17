package com.maatayim.talklet.repository.retrofit.api;

import com.maatayim.talklet.repository.retrofit.model.children.ChildrenListWrapper;
import com.maatayim.talklet.repository.retrofit.model.general.CreateRecordingRequest;
import com.maatayim.talklet.repository.retrofit.model.general.TipsWrapper;
import com.maatayim.talklet.repository.retrofit.model.recording.RecordingMetadata;
import com.maatayim.talklet.repository.retrofit.model.recording.RecordingNotificationResponse;
import com.maatayim.talklet.repository.retrofit.model.general.ChildDataResponse;
import com.maatayim.talklet.repository.retrofit.model.wordcountdata.WordCountResponse;
import com.maatayim.talklet.repository.retrofit.model.worddata.WordData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Sophie on 5/28/2017.
 */

public interface RetrofitApi {

//    User
    @GET("login")
    Observable<String> getLogin(
    );


//    RecordingObj
    @POST("recording")
    void postRecordingMetadata(
            @Header("Authorization") String authorization,
            @Body RecordingMetadata body
    );


    @GET("recording")
    Observable<RecordingNotificationResponse> getRecordingNotification(
    );


//    Children
    @GET("children")
    Observable<ChildrenListWrapper> getChildrenList(
    );


    @PUT("children/{id}")
    Observable<ChildrenListWrapper> putUpdateChild(
    );

    @POST("children")
    Observable<ChildrenListWrapper> postCreateChild(
    );

    @DELETE("children/{id}")
    void deleteChild(
            @Header("Authorization") String authorization
    );


//    General
    @GET("generaldata")
    Observable<TipsWrapper> getGeneralData(
    );


    @GET("childdata")
    Observable<ChildDataResponse> getChildData(
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
    Observable<WordData> getWordData(
            @Query("child") String child,
            @Header("Authorization") String authorization
    );


//    Word Count Data
    @GET("wordcountdata")
    Observable<WordCountResponse> getWordCountData(
            @Query("child") String child,
            @Header("Authorization") String authorization
    );



}

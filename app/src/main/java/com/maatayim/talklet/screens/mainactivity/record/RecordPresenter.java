package com.maatayim.talklet.screens.mainactivity.record;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static android.media.MediaRecorder.AudioSource.MIC;

/**
 * Created by Sophie on 6/27/2017
 */

public class RecordPresenter implements RecordContract.Presenter {

    private static final String THREE_GPP_TYPE = ".3gpp";
    static final String FILE_PATH = "file-path";

    private final RecordContract.View view;
    private final BaseContract.Repository repo;
    private final Scheduler scheduler;
    private MediaRecorder mediaRecorder;

    private PublishSubject<List<String>> selectedChildren = PublishSubject.create();
    private PublishSubject<Long> timer = PublishSubject.create();
    private DisposableObserver<Long> disposableObserver;

    @Override
    public void updateChildren(List<ChildRecObj> childRecObjs) {
        ArrayList<String> childrenIds = new ArrayList<>();
        for (ChildRecObj childRecObj : childRecObjs) {
            childrenIds.add(String.valueOf(childRecObj.getId()));
        }
        selectedChildren.onNext(childrenIds);
        disposableObserver.dispose();
        disposableObserver = Observable
                .timer(10000, TimeUnit.MILLISECONDS)
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        timer.onNext(aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Inject
    public RecordPresenter(RecordContract.View view, BaseContract.Repository repo,
                           Scheduler scheduler) {

        this.view = view;
        this.repo = repo;
        this.scheduler = scheduler;
        this.mediaRecorder = new MediaRecorder();
    }

    @Override
    public void getData() {
        repo.getMainScreenChildrenList()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribeWith(new DisposableSingleObserver<List<MainScreenChild>>() {
                    @Override
                    public void onSuccess(@NonNull List<MainScreenChild> mainScreenChildren) {
                        view.onDataReceived(mainScreenChildren);

                        List<TipTicket> tickets = new ArrayList<TipTicket>();

                        for (MainScreenChild child : mainScreenChildren) {
                            for (MainScreenChild.Tip tip : child.getTips()) {
                                tickets.add(new TipTicket(tip.getText(), tip.getTipType(), child.getUrl()));
                            }
                        }

                        view.initViewpager(tickets, mainScreenChildren.size() > 1);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.onTipsLoadError();
                    }
                });


    }


    public void getAllChildrenTips() {
//        repo.getRecotdTipsList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(scheduler)
//                .subscribeWith(new DisposableObserver<List<TipTicket>>() {
//                    @Override
//                    public void onNext(@NonNull List<TipTicket> TipTickets) {
//                        view.initViewpager(TipTickets);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        view.onDataLoadError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }


    public void startFtpService(String filePath) {
        Intent intent = new Intent(view.getViewContext(), FtpService.class);
        Bundle extras = new Bundle();
        extras.putString(FILE_PATH, filePath);
        intent.putExtras(extras);
        view.getViewContext().startService(intent);
    }

    @Override
    public void startRecording() {
        selectedChildren.firstOrError().doOnSuccess(x -> {
            initMediaRecorder(generateAbsolutePath(generateFileName(x)));
            mediaRecorder.start();
        }).subscribe();

        Observable.combineLatest(timer, selectedChildren,
                (aLong, strings) -> generateFileName(strings))
                .doOnNext(newFileName -> {
                    mediaRecorder.stop();
                    String absolutePath = generateAbsolutePath(newFileName);
                    initMediaRecorder(absolutePath);
                    mediaRecorder.start();
                    startFtpService(absolutePath);
                })
                .subscribe();
    }

    private void initMediaRecorder(String absoluteFilePath) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setOutputFile(absoluteFilePath);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
    }

    private String generateFileName(List<String> selectedChildren) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(System.currentTimeMillis()));
        for (String selectedChild : selectedChildren) {
            builder.append("-");
            builder.append(selectedChild);
        }
        builder.append(THREE_GPP_TYPE);
        return builder.toString();
    }

    private String generateAbsolutePath(String fileName) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File file = new File(path, File.separator + fileName);
        return file.getAbsolutePath();
    }

}

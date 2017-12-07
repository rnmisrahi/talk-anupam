package com.maatayim.talklet.screens.mainactivity.record;

import com.maatayim.talklet.baseline.BaseContract;

import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Scheduler;

/**
 * Created by Omri on 11/12/2017
 */
public class RecordPresenterTest {

    @Mock
    RecordContract.View view;

    @Mock
    BaseContract.Repository repository;

    @Mock
    Scheduler scheduler;


    @Test
    public void newFileCreatedOnSaveRecordingStream() throws Exception {
        RecordPresenter recordPresenter = new RecordPresenter(view, repository, scheduler);
        recordPresenter.saveRecordingStream();
    }
}
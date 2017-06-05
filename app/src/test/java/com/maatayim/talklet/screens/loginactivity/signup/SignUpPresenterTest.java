package com.maatayim.talklet.screens.loginactivity.signup;


import com.maatayim.talklet.baseline.BaseContract;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sophie on 6/5/2017.
 */
public class SignUpPresenterTest {


    public static final String TEST_STR = "Some string";
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SignupContract.View view;

    @Mock
    BaseContract.Repository repository;

    @Mock
    Date birthday;

    private SignUpPresenter presenter;


    @Before
    public void setUp() throws Exception {
        presenter = new SignUpPresenter(view, repository, Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }


    @Test
    public void saveSignUpDetails_repositorySuccess() throws Exception {
        when(repository.saveSignupDetails(any(String.class), any(Date.class))).thenReturn(Completable.complete());

        presenter.saveSignUpDetails(TEST_STR, birthday);
        verify(view, only()).onDataSaveSuccess();
    }

    @Test
    public void saveSignUpDetails_repositoryFailure() throws Exception {
        when(repository.saveSignupDetails(any(String.class), any(Date.class))).thenReturn(Completable.error(new Exception()));
        presenter.saveSignUpDetails(TEST_STR, birthday);
        verify(view, only()).onDataSaveFailure();
    }

}
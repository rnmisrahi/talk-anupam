package com.maatayim.talklet.screens.loginactivity.login;

import android.content.Context;

//import com.maatayim.talklet.TestComponent;
import com.facebook.login.LoginResult;
import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.loginactivity.login.injection.AccessTokenWrapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sophie on 5/24/2017
 */

public class LoginPresenterTest {
    public static final String INVALID_TOKEN = "INVALID_TOKEN";
    public static final String VALID_TOKEN = "VALID_TOKEN";

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private LoginContract.View view;

    @Mock
    Context context;

    @Mock
    AccessTokenWrapper accessToken;

    @Mock
    LoginResult loginResult;

    @Mock
    BaseContract.Repository repository;


    LoginPresenter presenter;

    @Mock
    EventBus eventBus;


    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, context, repository, accessToken, Schedulers.trampoline(), eventBus);
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Test
    public void checkIfLoggedIn_whenAccessTokenValid() throws Exception {

        when(accessToken.getToken()).thenReturn(VALID_TOKEN);

        when(accessToken.isNull()).thenReturn(false);

        when(repository.getFacebookLoginToken()).thenReturn(Observable.just(VALID_TOKEN));

        presenter.checkIfLoggedIn();

        verify(view, only()).setConnection();
    }


    @Test
    public void checkIfLoggedIn_whenAccessTokenInvalid() throws Exception {

        when(accessToken.getToken()).thenReturn(INVALID_TOKEN);

        when(accessToken.isNull()).thenReturn(false);

        when(repository.getFacebookLoginToken()).thenReturn(Observable.just(VALID_TOKEN));

        presenter.checkIfLoggedIn();

        verify(view, only()).onInvalidToken();

//        verify(eventBus).post(ArgumentMatchers.any());
    }


    @Test
    public void checkIfLoggedIn_whenAccessTokenIsNull() throws Exception {

        when(accessToken.getToken()).thenReturn(INVALID_TOKEN);

        when(accessToken.isNull()).thenReturn(true);

        when(repository.getFacebookLoginToken()).thenReturn(Observable.just(VALID_TOKEN));

        presenter.checkIfLoggedIn();

        verify(view, only()).onInvalidToken();
    }

    @Test
    public void saveToken_repositorySuccess() throws Exception {
        when(repository.saveFacebookLoginToken(any(LoginResult.class))).thenReturn(Completable.complete());
        presenter.saveToken(loginResult);
        verify(view, only()).onFacebookLoginSuccess();
    }


    @Test
    public void saveToken_repositoryFailure() throws Exception {
        when(repository.saveFacebookLoginToken(any(LoginResult.class))).thenReturn(Completable.error(new Exception()));
        presenter.saveToken(loginResult);
        verify(view, only()).onFacebookLoginFailed();
    }
}
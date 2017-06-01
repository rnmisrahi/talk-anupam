package com.maatayim.talklet.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.application.TalkletApplication;
import com.maatayim.talklet.baseline.TalkletFragment;
import com.maatayim.talklet.screens.login.dagger.LoginModule;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sophie on 5/21/2017
 */

public class LoginFragment extends TalkletFragment implements LoginContract.View {

    private static final String TAG = "LoginFragment";
    public static final int LOGIN_SUCCESS = 123;

    @Inject
    LoginContract.Presenter presenter;

    @BindView(R.id.connect_with_fb_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new LoginModule(this)).inject(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        presenter.checkIfLoggedIn(AccessToken.getCurrentAccessToken());
        return view;
    }




    @Override
    public void displayConnectionDialog() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            setConnection();
        }

    }

    @Override
    public void displayFacebookLoginInterface() {

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                presenter.saveToken(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    public void setConnection() {
        Toast.makeText(getContext(), "You are already logged in", Toast.LENGTH_SHORT).show();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @OnClick(R.id.connect_with_fb_button)
    public void onFacebookClick() {
        displayFacebookLoginInterface();
//
    }


//    @Override
//    public void displayConnectionDialog() {
//
//
//        String token;
//
//        presenter.swaveFacebookToken()
//    }

//    @Override
//    public void displayFacebookLoginInterface() {
//        EventBus.getDefault().post(new AddLoginFragmentEvent(new SignupFragment()));
//    }

//    @Subscribe
//    public void onLoginResult(FacebookLoginActivityResultEvent event) {
//        if (event.getResultCode() == RESULT_OK) {
////            presenter.saveToken(loginResult);
//            setConnection();
//        }
//    }

}

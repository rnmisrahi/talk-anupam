package com.maatayim.talklet.screens.loginactivity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.baseline.events.AddLoginFragmentEvent;
import com.maatayim.talklet.screens.loginactivity.login.injection.LoginModule;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.maatayim.talklet.screens.loginactivity.signup.SignupFragment;
import com.maatayim.talklet.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static final int LOGIN_REQUEST_CODE = 64206;
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String GENDER = "gender";
    public static final String BIRTHDAY = "birthday";

    @Inject
    LoginContract.Presenter presenter;

    @Inject EventBus eventBus;


    @BindView(R.id.connect_with_fb_button)
    LoginButton loginButton;

    @BindView(R.id.connect_text)
    TextView connectText;

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
//        loginButton.setFragment(this);

//        loginButton.registerCallback(callbackManager, mFacebookCallback);
        presenter.checkIfLoggedIn();
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK & requestCode == LOGIN_REQUEST_CODE) {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }

    }


    @Override
    public void displayFacebookLoginInterface() {

        loginButton.setReadPermissions(Arrays.asList("user_birthday", "public_profile", "email", "user_friends"));



//        loginButton.setReadPermissions(EMAIL);

        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                presenter.saveToken(loginResult);


                AccessToken accessToken = loginResult.getAccessToken();

//                LoginManager.getInstance().logInWithReadPermissions(
//                        getActivity(),
//                        Arrays.asList("birthday"));

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                    Log.i("Response", response.toString());
                                    String email = object.getString(EMAIL);
                                    String birthday = object.getString("birthday");
                                    Profile profile = Profile.getCurrentProfile();
                                    String id = profile.getId();
                                    String firstName = object.getString(FIRST_NAME);
                                    String lastName = object.getString(LAST_NAME);
                                    String gender = object.getString(GENDER);
//                                    profile.getProfilePictureUri()

                                    UserDetails userDetails = new UserDetails(
                                            id, firstName, lastName, Utils.parseDateFromFB(birthday), email, gender);

                                    presenter.saveUserFBDetails(userDetails);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
                                    LoginManager.getInstance().logOut();
                                    isDisplayLoginButton(true);
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", ID + "," + EMAIL + "," + FIRST_NAME + "," + LAST_NAME + "," + GENDER + "," + BIRTHDAY);
                request.setParameters(parameters);
                request.executeAsync();

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

    private void isDisplayLoginButton(boolean isDisplay){
        if(isDisplay){
            loginButton.setVisibility(View.VISIBLE);
            connectText.setVisibility(View.VISIBLE);
        }else{
            loginButton.setVisibility(View.GONE);
            connectText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFacebookLoginSuccess(){
        Log.d(TAG, "onFacebookLoginSuccess: ");

    }

    public void onAlreadySignedUpFailed(){
        EventBus.getDefault().post(new AddLoginFragmentEvent(SignupFragment.newInstance(true)));

    }

    public void onSignedUpSuccess(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onSaveUserFBDataSuccess() {
        presenter.checkIfSignedUp();
    }

    @Override
    public void onSaveUserFBDataFailed() {
        Log.d(TAG, "onSaveUserFBDataFailed: ");
    }

    @Override
    public void unlockLoginProcess() {
        LoginManager.getInstance().logOut();
        isDisplayLoginButton(true);
    }

    @Override
    public void alreadyLogedIn() {
        presenter.checkIfSignedUp();
    }

    @Override
    public void onFacebookLoginFailed() {
        Toast.makeText(getContext(), "Facebook login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setConnection() {
        Toast.makeText(getContext(), "You are already logged in", Toast.LENGTH_SHORT).show();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_birthday"));

        loginButton.setClickable(false);
        eventBus.post(new AddLoginFragmentEvent(SignupFragment.newInstance(true)));
    }

    @OnClick(R.id.connect_with_fb_button)
    public void onFacebookClick() {
        displayFacebookLoginInterface();
        isDisplayLoginButton(false);

    }

    @Override
    public void onInvalidToken() {
        LoginManager.getInstance().logOut();
    }



}

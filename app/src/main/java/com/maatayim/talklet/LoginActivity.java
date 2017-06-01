package com.maatayim.talklet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.maatayim.talklet.application.TalkletApplication;
import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.baseline.events.AddLoginFragmentEvent;
import com.maatayim.talklet.screens.login.LoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Sophie on 5/22/2017.
 */

public class LoginActivity extends AppCompatActivity implements BaseContract.View {

//    CallbackManager callbackManager;

//    @Inject
//    RepositoryImpl repositoryImpl;
//    @Inject LoginView view;
//    @Inject LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ((TalkletApplication) getApplication()).getAppComponent().inject(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Avenir-Black.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

//        callbackManager = CallbackManager.Factory.create();
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });
//
//        LoginButton loginButton = (LoginButton) findViewById(R.id.connect_with_fb_button);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { ... });
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {...});

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new AddLoginFragmentEvent(new LoginFragment()));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Subscribe
    public void addFragment(AddLoginFragmentEvent event) {
        addFragment(event.getFragment(), false);
    }


    protected void addFragment(Fragment fragment, boolean replace) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        if (replace) {
            ft.replace(R.id.no_bar_fragment_container, fragment, fragment.getClass().getName());
        } else {
            ft.add(R.id.no_bar_fragment_container, fragment, fragment.getClass().getName());
        }

        ft.addToBackStack(fragment.getClass().getName());

        ft.commit();
        if (fm.getBackStackEntryCount() == 0) {
        }

    }

    @Override
    protected void onDestroy() {
        // Unregister
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_TAKE_PHOTO || requestCode == REQUEST_CODE_PICK_FROM_GALLERY){
//            EventBus.getDefault().post(new ActivityResultEvent(requestCode, resultCode, data));
//        }else{
//            EventBus.getDefault().post(new FacebookLoginActivityResultEvent(requestCode, resultCode, data));
//        }

    }
}

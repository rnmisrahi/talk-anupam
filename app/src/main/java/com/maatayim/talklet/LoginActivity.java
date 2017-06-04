package com.maatayim.talklet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.baseline.events.AddLoginFragmentEvent;
import com.maatayim.talklet.screens.loginactivity.login.LoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Sophie on 5/22/2017.
 */

public class LoginActivity extends AppCompatActivity implements BaseContract.View {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ((TalkletApplication) getApplication()).getAppComponent().inject(this);



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

    }
}

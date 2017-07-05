package com.maatayim.talklet;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.SideMenuFragment;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.MainModule;
import com.maatayim.talklet.screens.mainactivity.sidemenu.DrawerHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {


    public static final String EMPTY_TITLE = "";
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 29;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2222;
    //    protected ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view_drawer)
    NavigationView navigationView;

    @BindView(R.id.drawer_menu_layout)
    ConstraintLayout drawerMenuLayout;

    private DrawerHandler drawerHandler;

    @Inject
    MainActivityContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TalkletApplication) getApplication()).getAppComponent().inject(this);
//        ((TalkletApplication) getApplication()).getAppComponent().plus(new MainActivityModule(this)).inject(this);

        ButterKnife.bind(this);
        drawerHandler = new DrawerHandler(this);
        initToolbar(EMPTY_TITLE);
        initFragmentManager();

        presenter.downloadData();

        EventBus.getDefault().register(this);
        addFragment(new MainFragment(), true, null);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    protected void initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);

        final ImageView hamburgerView = ((ImageView) toolbar.findViewById(R.id.drawer_hamburger));
        drawerHandler.setHamburgerView(hamburgerView);
        hamburgerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerHandler.isHamburger()){
                    drawerHandler.menuClick();
                }else{
                    onBackPressed();
                }

            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    @Override
    protected void onDestroy() {
        // Unregister
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void addFragment(AddFragmentEvent event) {
        addFragment(event.getFragment(), false, null);
    }

    public void addFragment(SideMenuFragment sideMenuFragment) {
        addFragment(sideMenuFragment, true, null);
    }

    @Subscribe
    public void addFragment(AddFragmentWithSharedElementEvent event) {
        addFragment(event.getFragment(), false, event.getView());
    }


    protected void addFragment(TalkletFragment fragment, boolean replace, View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (replace) {
            ft.replace(R.id.frame_layout, fragment, fragment.getClass().getName());
            if(isHomeFragment()){
                ft.addToBackStack(fragment.getClass().getName());
            }
        } else {
            ft.add(R.id.frame_layout, fragment, fragment.getClass().getName());

            if(view != null){
                ft.addSharedElement(view, ViewCompat.getTransitionName(view));
            }

            drawerHandler.onAddFragment();
            ft.addToBackStack(fragment.getClass().getName());
        }

        ft.commit();

    }



    protected TalkletFragment getCurrentFragment() {

        FragmentManager fm = getSupportFragmentManager();

        int index = fm.getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = fm.getBackStackEntryAt(index);
        String tag = backEntry.getName();
        Fragment fragment = fm.findFragmentByTag(tag);
        return (TalkletFragment) fragment;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        getCurrentFragment().onBackPressed();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (lastFragment())
            finish();
        else
            drawerHandler.onBackPressChangeHamburgerIcon(fm, false);
        super.onBackPressed();


    }


    private boolean isHomeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        int index = fm.getBackStackEntryCount();
        return index == 1 || index == 0;
    }


    private boolean lastFragment() {
        FragmentManager fm = getSupportFragmentManager();
        int index = fm.getBackStackEntryCount() - 1;
        return index == 0;
    }


    protected void initFragmentManager() {
        FragmentManager fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                TalkletFragment fragment = getCurrentFragment();
                if (fragment == null)
                    return;
//                toolbar.getMenu().clear();
                displayTitle(fragment.getTitle());

            }
        });

    }

    protected void displayTitle(String title) {
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);
    }







}

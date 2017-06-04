package com.maatayim.talklet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.SideMenuFragment;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.screens.TempFragment;
import com.maatayim.talklet.screens.mainscreen.GeneralFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.maatayim.talklet.R.id.nav_view_drawer;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {


    public static final String EMPTY_TITLE = "";
    protected ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TalkletApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        initToolbar(EMPTY_TITLE);
        initDrawer();
        initFragmentManager();

        EventBus.getDefault().register(this);
        addFragment(new GeneralFragment(), false);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    protected void initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);
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
        addFragment(event.getFragment(), false);
    }

    private void addFragment(SideMenuFragment sideMenuFragment) {
        addFragment(sideMenuFragment, false);
    }


    protected void addFragment(TalkletFragment fragment, boolean replace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (replace) {
            ft.replace(R.id.frame_layout, fragment, fragment.getClass().getName());
        } else {
            ft.add(R.id.frame_layout, fragment, fragment.getClass().getName());
        }
        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
//        if (fm.getBackStackEntryCount() == 0) {
//        }

    }

    ////////////////////////// drawer ///////////////////


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(nav_view_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        // Change the color of "logout" item specifically
//        changeMenuItemColor(navigationView);


    }


    private void setNewSidemenuFragment(SideMenuFragment sideMenuFragment, MenuItem item) {
        sideMenuFragment.setMenuItem(item);
        addFragment(sideMenuFragment);
//        setHomeAsUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //// TODO: 5/24/2017 complete this navigation
        int id = item.getItemId();

        switch (id) {
            case R.id.schedule_recordings_item:
                setNewSidemenuFragment(new TempFragment(), item);
                break;
            case R.id.setting_item:
                setNewSidemenuFragment(new TempFragment(), item);
                break;
            case R.id.notification_item:
                setNewSidemenuFragment(new TempFragment(), item);
                break;
            case R.id.website_item:
                setNewSidemenuFragment(new TempFragment(), item);
                break;
            case R.id.logout_item:
                return false;
            case R.id.help:
                setNewSidemenuFragment(new TempFragment(), item);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void uncheckElementsInSideMenu() {
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view_drawer);
        int menuSize = mNavigationView.getMenu().size();
        for (int i = 0; i < menuSize; i++) {
            mNavigationView.getMenu().getItem(i).setChecked(false);
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////

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

        uncheckElementsInSideMenu();

        getCurrentFragment().onBackPressed();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }


        if (lastFragment())
            finish();
        else
            super.onBackPressed();


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

package com.maatayim.talklet;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.SideMenuFragment;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.DrawerHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    public static final String EMPTY_TITLE = "";
//    protected ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view_drawer)
    NavigationView navigationView;

//    @BindView(R.id.drawer_linear_layout)
//    LinearLayout drawerLinearLayout;

    @BindView(R.id.drawer_menu_layout)
    ConstraintLayout drawerMenuLayout;
    private DrawerHandler drawerHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TalkletApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);
        drawerHandler = new DrawerHandler(this);
        initToolbar(EMPTY_TITLE);
        initFragmentManager();

        EventBus.getDefault().register(this);
        addFragment(new MainFragment(), true);

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
        addFragment(event.getFragment(), false);
    }

    public void addFragment(SideMenuFragment sideMenuFragment) {
        addFragment(sideMenuFragment, true);
    }


    protected void addFragment(TalkletFragment fragment, boolean replace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (replace) {
            ft.replace(R.id.frame_layout, fragment, fragment.getClass().getName());
            if(isHomeFragment()){
                ft.addToBackStack(fragment.getClass().getName());
            }
        } else {
            ft.add(R.id.frame_layout, fragment, fragment.getClass().getName());
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

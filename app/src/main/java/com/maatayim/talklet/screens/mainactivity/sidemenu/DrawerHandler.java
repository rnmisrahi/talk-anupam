package com.maatayim.talklet.screens.mainactivity.sidemenu;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.fragments.SideMenuFragment;
import com.maatayim.talklet.screens.TempFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maatayim.talklet.R.id.add;
import static com.maatayim.talklet.R.id.schedule_recordings_item;
import static com.maatayim.talklet.R.id.toolbar;

/**
 * Created by Sophie on 6/13/2017.
 */

public class DrawerHandler {


    private final MainActivity activity;
    private final DrawerLayout drawerLayout;

    @BindView(R.id.nav_view_drawer)
    NavigationView navigationView;

    @BindView(R.id.schedule_recordings_item)
    TextView shcedule;

    @BindView(R.id.setting_item)
    TextView setting;

    @BindView(R.id.notification_item)
    TextView notification;

    @BindView(R.id.website_item)
    TextView website;

    @BindView(R.id.logout_item)
    TextView logout;

    @BindView(R.id.help_item)
    TextView help;

    List<TextView> sideMenuItems = new ArrayList<>();

    private int checkedItemIndex = -1;
    private ImageView hamburgerView;
    private boolean isHamburger = true;


    public DrawerHandler(MainActivity activity) {
        this.activity = activity;
        ButterKnife.bind(this, activity.findViewById(android.R.id.content));
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);

        sideMenuItems.add(shcedule);
        sideMenuItems.add(setting);
        sideMenuItems.add(notification);
        sideMenuItems.add(website);
        sideMenuItems.add(logout);
        sideMenuItems.add(help);


    }


    @OnClick(R.id.schedule_recordings_item)
    public void onScheduleClick(TextView view){
        onItemCheck(view, new TempFragment());
    }

    @OnClick(R.id.setting_item)
    public void onSettingsClick(TextView view){
        onItemCheck(view, new SettingFragment());
    }


    @OnClick(R.id.notification_item)
    public void onNotificationClick(TextView view){
        onItemCheck(view, new TempFragment());
    }

    @OnClick(R.id.website_item)
    public void onWebsiteClick(TextView view){
        onItemCheck(view, new TempFragment());
    }


    @OnClick(R.id.logout_item)
    public void onLogoutClick(TextView view){
        EventBus.getDefault().post(new LogoutEvent());
    }

    @OnClick(R.id.help_item)
    public void onHelpClick(TextView view){
        onItemCheck(view, new TempFragment());
    }



    public SpannableStringBuilder setTextViewWordCount(String drawerItemTitle, boolean isBold) {
        SpannableStringBuilder txt = new SpannableStringBuilder();
        txt.append(drawerItemTitle);
        if(isBold){
            txt.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                    0, txt.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }else{
            txt.setSpan(new StyleSpan(Typeface.NORMAL),
                    0, txt.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        return txt;

    }

    private void onItemCheck(TextView view, SideMenuFragment fragment){
        int newItemLocation = sideMenuItems.indexOf(view);

        //setCheck the new item
        view.setBackgroundColor(activity.getResources().getColor(R.color.navigation_drawer_padding));
        view.setText(setTextViewWordCount(view.getText().toString(), true));

        uncheckCurrentSidemenuSelection();


        if(newItemLocation != checkedItemIndex){
            activity.addFragment(fragment);
//            onBackPressChangeHamburgerIcon(null, true);
            checkedItemIndex = newItemLocation;
        }

        closeDrawer();
    }

    private void uncheckCurrentSidemenuSelection(){
        //Uncheck the previous item
        if(checkedItemIndex != -1){
            TextView itemToUncheck = sideMenuItems.get(checkedItemIndex);
            itemToUncheck.setBackgroundColor(activity.getResources().getColor(R.color.primary_background_color));
            itemToUncheck.setText(setTextViewWordCount(itemToUncheck.getText().toString(), false));
        }
    }


    public void menuClick() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void setHamburgerView(View hamburgerView) {
        this.hamburgerView = (ImageView) hamburgerView;
    }

    public void onBackPressChangeHamburgerIcon(FragmentManager fm, boolean addScreen) {

        if(addScreen){
            hamburgerView.setImageDrawable(activity.getResources().getDrawable(R.drawable.back));
            isHamburger = false;
        }else {
            uncheckCurrentSidemenuSelection();
            if (fm.getBackStackEntryCount() == 2) {
                hamburgerView.setImageDrawable(activity.getResources().getDrawable(R.drawable.menu_grey));
                isHamburger = true;
            } else {
                hamburgerView.setImageDrawable(activity.getResources().getDrawable(R.drawable.back));
                isHamburger = false;
            }
        }
        checkedItemIndex = -1;

    }

    public boolean isHamburger(){
        return isHamburger;
    }


    public void onAddFragment() {
        hamburgerView.setImageDrawable(activity.getResources().getDrawable(R.drawable.back));
        isHamburger = false;
    }
}

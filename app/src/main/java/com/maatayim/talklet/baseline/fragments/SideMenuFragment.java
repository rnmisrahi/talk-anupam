package com.maatayim.talklet.baseline.fragments;

import android.view.MenuItem;

/**
 * Created by Sophie on 5/24/2017.
 */

public class SideMenuFragment extends TalkletFragment {

    private MenuItem menuItem;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}

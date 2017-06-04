package com.maatayim.talklet.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.fragments.SideMenuFragment;

/**
 * Created by Sophie on 5/24/2017.
 */

public class TempFragment extends SideMenuFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        setTitle("Temp Screen");
        return view;
    }
}

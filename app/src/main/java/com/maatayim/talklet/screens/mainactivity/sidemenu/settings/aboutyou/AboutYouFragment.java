package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection.AboutYouModule;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.injection.SettingModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/18/2017.
 */

public class AboutYouFragment extends TalkletFragment implements AboutYouContract.View {

    @Inject
    AboutYouContract.Presenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new AboutYouModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_you, container, false);
        ButterKnife.bind(this, view);
        setTitle(getString(R.string.about_you_title));

        return view;
    }
}

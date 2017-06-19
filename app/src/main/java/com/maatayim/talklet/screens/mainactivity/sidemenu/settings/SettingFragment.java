package com.maatayim.talklet.screens.mainactivity.sidemenu.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.baseline.fragments.SideMenuFragment;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.AboutYouFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.injection.SettingModule;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sophie on 6/18/2017.
 */

public class SettingFragment extends SideMenuFragment implements SettingContract.View {

    @Inject
    SettingContract.Presenter presenter;


    @BindView(R.id.about_child_text_view)
    TextView aboutChild1;

    @BindView(R.id.about_child2)
    CardView aboutChild2;

    @BindView(R.id.child2_text_view)
    TextView child2TextView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new SettingModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        setTitle(getString(R.string.setting));
        presenter.getData();
        return view;
    }


    @Override
    public void onDataReceived(List<Child> childrenNames) {
        aboutChild1.setText(getString(R.string.about_kid, childrenNames.get(0).getName()));
        if (childrenNames.size() > 1){
            aboutChild2.setVisibility(View.VISIBLE);
            child2TextView.setText(getString(R.string.about_kid, childrenNames.get(1).getName()));
        }
    }



    @Override
    public void onChildrenLoadError() {

    }


    @OnClick(R.id.about_you)
    public void onAboutyouClick(){
        EventBus.getDefault().post(new AddFragmentEvent(new AboutYouFragment()));
    }



    @OnClick(R.id.add_another_kid)
    public void onAddKidClick(){

    }
}

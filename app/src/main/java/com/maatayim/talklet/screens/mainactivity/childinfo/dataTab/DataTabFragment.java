package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.childinfo.ViewPagerAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.ByDateFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.ByRecordingFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.GeneralTabFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.injection.DataTabModule;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

/**
 * Created by Sophie on 6/6/2017.
 */

public class DataTabFragment extends TalkletFragment implements DataTabContract.View{

    public static final String ARG_ID = "babyId";

    @Inject
    DataTabContract.Presenter presenter;

    @BindView(R.id.tab_layout_data)
    TabLayout tabLayout;

    @BindView(R.id.view_pager_data)
    ViewPager viewPager;


    private String babyId;
    private ViewPagerAdapter adapter;


    protected static String[] TABS_TITLES_DATA;
    protected LinearLayout[] tabsTextViews_data;


    public static DataTabFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        DataTabFragment fragment = new DataTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new DataTabModule(this)).inject(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_child_data, container, false);
        ButterKnife.bind(this, view);
        presenter.getData(babyId);
        return view;
    }


    @Override
    public void onDataReceived(Child child) {
        setTitle(child.getName());

        initViewPager();
        initTabLayout();
    }



    @Override
    public void onBabyPhotoLoadError() {
        Toast.makeText(getContext(), "Baby photo load failed", Toast.LENGTH_SHORT).show();
    }


    protected void initViewPager() {
        adapter = new ViewPagerAdapter(getFragmentManager());

        adapter.addFragment(GeneralTabFragment.newInstance(babyId), "GENERAL");
        adapter.addFragment(ByDateFragment.newInstance(babyId), "BYDATE");
        adapter.addFragment(ByRecordingFragment.newInstance(babyId), "BYRECORD");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    protected void initTabLayout() {

        TABS_TITLES_DATA = getResources().getStringArray(R.array.tabs_titles_data_tab);

        tabsTextViews_data = new LinearLayout[TABS_TITLES_DATA.length];

        for (int i = 0; i < TABS_TITLES_DATA.length; i++) {
            tabLayout.getTabAt(i).setCustomView(getTabView(i));
        }

        setTabSelected(0);
    }


    private View getTabView(int i) {
        tabsTextViews_data[i] = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.custom_data_general_tab, null);
        TextView tabsTitle = (TextView) tabsTextViews_data[i].findViewById(R.id.tab_title);
        tabsTitle.setText(TABS_TITLES_DATA[i]);
//        tabsTextViews_data[i].setGravity(RelativeLayout.CENTER_IN_PARENT);
//        tabsTextViews_data[i].setGravity(RelativeLayout.TEXT_ALIGNMENT_CENTER);

        RelativeLayout layout = new RelativeLayout(getContext());
        layout.addView(tabsTextViews_data[i]);
        layout.setHorizontalGravity(RelativeLayout.CENTER_HORIZONTAL);
        return layout;
    }


    @OnPageChange(R.id.view_pager_data)
    void onPageSelected(int position) {
        setTabSelected(position);

    }


    protected void setTabSelected(int position) {
        for (int i = 0; i < TABS_TITLES_DATA.length; i++) {
            TextView tabsTitle = (TextView) tabsTextViews_data[i].findViewById(R.id.tab_title);
            tabsTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_not_selected));
        }
        TextView tabsTitle = (TextView) tabsTextViews_data[position].findViewById(R.id.tab_title);
        tabsTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color));

    }
}

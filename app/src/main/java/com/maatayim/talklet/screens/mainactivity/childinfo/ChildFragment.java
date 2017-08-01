package com.maatayim.talklet.screens.mainactivity.childinfo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.DataTabFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.ChildMainFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.injection.ChildModule;
import com.maatayim.talklet.utils.Utils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sophie on 6/6/2017
 */

public class ChildFragment extends TalkletFragment implements ChildContract.View{

    public static final String ARG_ID = "babyId";
    @Inject
    ChildContract.Presenter presenter;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.child_item)
    CircleImageView childImg;

    private String babyId;
    private ViewPagerAdapter adapter;

    protected static String[] TABS_TITLES;
    protected static int[] TABS_ICONS;
    protected static int[] TABS_ICONS_SELECTED;
    protected TextView[] tabsTextViews;


    public static ChildFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        ChildFragment fragment = new ChildFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move);
            setSharedElementEnterTransition(transition);
            setEnterTransition(transition);
        }

        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new ChildModule(this)).inject(this);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_info, container, false);
        ButterKnife.bind(this, view);
        presenter.getData(babyId);
        return view;
    }

    @Override
    public void onDataReceived(Child child) {
        setTitle(child.getName());

        Picasso.with(getContext())
                .load(child.getImg())
                .placeholder(R.drawable.pic)
                .into(childImg);

        initViewPager();
        initTabLayout();
    }





    @Override
    public void onbabyPhotoLoadError() {
        Toast.makeText(getContext(), "Baby photo load failed", Toast.LENGTH_SHORT).show();
    }


    protected void initViewPager() {
        adapter = new ViewPagerAdapter(getFragmentManager());

        adapter.addFragment(ChildMainFragment.newInstance(babyId), "GENERAL");
        adapter.addFragment(DataTabFragment.newInstance(babyId), "DATA");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    protected void initTabLayout() {

        initResources();


        for (int i = 0; i < TABS_TITLES.length; i++) {

            tabLayout.getTabAt(i).setCustomView(getTabView(i));

        }

        setTabSelected(0);
    }

    private View getTabView(int i) {
        tabsTextViews[i] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabsTextViews[i].setText(TABS_TITLES[i]);
        tabsTextViews[i].setCompoundDrawablesWithIntrinsicBounds(0, TABS_ICONS[i], 0, 0);
        if(i==0){
            tabsTextViews[i].setPadding(100,5,20,5);
        }
        if(i==1){
            tabsTextViews[i].setPadding(270,5,50,5);

        }

        RelativeLayout layout = new RelativeLayout(getContext());
        layout.addView(tabsTextViews[i]);
        return layout;
    }


    protected void initResources() {

        TABS_TITLES = getResources().getStringArray(R.array.tabs_titles);

        TABS_ICONS = Utils.getResourceIdsList(getContext(), R.array.tabs_icons_not_selected);

        TABS_ICONS_SELECTED = Utils.getResourceIdsList(getContext(), R.array.tabs_icons_selected);

        tabsTextViews = new TextView[TABS_TITLES.length];
    }


    @OnPageChange(R.id.view_pager)
    void onPageSelected(int position) {
        setTabSelected(position);

    }


    protected void setTabSelected(int position) {
        for (int i = 0; i < TABS_TITLES.length; i++) {
            tabsTextViews[i].setCompoundDrawablesWithIntrinsicBounds(0, TABS_ICONS[i], 0, 0);
            tabsTextViews[i].setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_not_selected));
            if(i==0){
                tabsTextViews[i].setPadding(100,0,20,0);
            }
            if(i==1){
                tabsTextViews[i].setPadding(270,0,50,0);
            }
        }
        tabsTextViews[position].setCompoundDrawablesWithIntrinsicBounds(0,
                TABS_ICONS_SELECTED[position], 0, 0);
        tabsTextViews[position].setTextColor(ContextCompat.getColor(getContext(), R.color.text_color));

    }
}

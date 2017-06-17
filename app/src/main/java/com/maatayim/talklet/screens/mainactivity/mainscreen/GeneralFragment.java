package com.maatayim.talklet.screens.mainactivity.mainscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.children.ChildrenAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.GeneralModule;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipTicket;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralFragment extends TalkletFragment implements GeneralContract.View{
    public static final String EMPTY_TITLE = "";
    public static final int DEFAULT_GAP = 120;
    public static final int HALF_GAP = DEFAULT_GAP / 2;
    public static final String ARG_ID = "babysId";

    @Inject
    GeneralContract.Presenter presenter;

    @BindView(R.id.tips_view_pager)
    ViewPager tipsViewPager;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicator;

    @BindView(R.id.words_progress_bar)
    ProgressBar wordsProgressBar;

    @BindView(R.id.total_words_value)
    TextView totalWords;

    @BindView(R.id.end_bar_value)
    TextView maxWordsNum;

    @BindView(R.id.children_recyclerView)
    RecyclerView childrenRecyclerView;

    private String babysId;


//    public static GeneralFragment newInstance(String id) {
//
//        Bundle args = new Bundle();
//        args.putString(ARG_ID, id);
//
//        GeneralFragment fragment = new GeneralFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            babysId = getArguments().getString(ARG_ID);
//        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new GeneralModule(this)).inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_main, container, false);
        ButterKnife.bind(this, view);
        setTitle(EMPTY_TITLE);
        presenter.getData();
        return view;

    }

    @Override
    public void updateTipsViewPager(List<GeneralTipTicket> ticketList) {
        tipsViewPager.setPadding(DEFAULT_GAP, 0, DEFAULT_GAP, 0);
        tipsViewPager.setClipToPadding(false);
        tipsViewPager.setPageMargin(HALF_GAP);

        initializeViewPager(ticketList);
    }

    @Override
    public void updateWordsCount(int numOfWords, int maxNumOfWords){
        maxWordsNum.setText(String.valueOf(maxNumOfWords));
        totalWords.setText(getString(R.string.progress_bar_value,
                String.valueOf(numOfWords),
                String.valueOf(maxNumOfWords)));

        wordsProgressBar.setProgress((numOfWords*100)/maxNumOfWords);
    }


    private void initializeViewPager(List<GeneralTipTicket> ticketList){

        GeneralTipAdapter pagerAdapter = new GeneralTipAdapter(
                getActivity().getSupportFragmentManager(), ticketList);
        tipsViewPager.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(tipsViewPager);
    }

    @Override
    public void setChildrenRecyclerView(List<Child> childrenList){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        childrenRecyclerView.setLayoutManager(linearLayoutManager);
        ChildrenAdapter vendorsAdapter = new ChildrenAdapter(childrenList);
        childrenRecyclerView.setAdapter(vendorsAdapter);
    }


    @Override
    public void onChildLoadError() {
        Toast.makeText(getContext(), "Failed load child", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisplayChildrenError() {
        Toast.makeText(getContext(), "Failed load children", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWordsCountLoadError() {
        Toast.makeText(getContext(), "Failed load words count", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTipsLoadError() {
        Toast.makeText(getContext(), "Failed load Tips", Toast.LENGTH_SHORT).show();
    }




}

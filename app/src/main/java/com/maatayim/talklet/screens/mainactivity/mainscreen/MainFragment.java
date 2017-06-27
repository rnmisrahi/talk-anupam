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
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.children.ChildrenAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.MainModule;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipsAdapter;
import com.maatayim.talklet.screens.mainactivity.record.RecordingFragment;
import com.viewpagerindicator.CirclePageIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sophie on 5/26/2017.
 */

public class MainFragment extends TalkletFragment implements MainContract.View {
    public static final String EMPTY_TITLE = "";
    public static final int DEFAULT_GAP = 120;
    public static final int HALF_GAP = DEFAULT_GAP / 2;

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.tips_view_pager)
    ViewPager tipsViewPagerMain;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicatorMain;

    @BindView(R.id.words_progress_bar)
    ProgressBar wordsProgressBar;

    @BindView(R.id.total_words_value)
    TextView totalWords;

    @BindView(R.id.end_bar_value)
    TextView maxWordsNum;

    @BindView(R.id.children_recyclerView)
    RecyclerView childrenRecyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new MainModule(this)).inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_main, container, false);
        ButterKnife.bind(this, view);
        setTitle(EMPTY_TITLE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pagerAdapter = null;
    }

    @Override
    public void updateTipsViewPager(List<TipTicket> ticketList) {
        tipsViewPagerMain.setPadding(DEFAULT_GAP, 0, DEFAULT_GAP, 0);
        tipsViewPagerMain.setClipToPadding(false);
        tipsViewPagerMain.setPageMargin(HALF_GAP);

        initializeViewPager(ticketList);
    }

    @Override
    public void updateWordsCount(int numOfWords, int maxNumOfWords) {
        maxWordsNum.setText(String.valueOf(maxNumOfWords));
        totalWords.setText(getString(R.string.progress_bar_value,
                String.valueOf(numOfWords),
                String.valueOf(maxNumOfWords)));
        wordsProgressBar.setMax(maxNumOfWords);
        wordsProgressBar.setProgress((numOfWords * 100) / maxNumOfWords);
    }

    TipsAdapter pagerAdapter;

    private void initializeViewPager(List<TipTicket> ticketList) {

        pagerAdapter = new TipsAdapter(
                getChildFragmentManager(), ticketList);
        tipsViewPagerMain.setAdapter(pagerAdapter);
        pageIndicatorMain.setViewPager(tipsViewPagerMain);

    }

    @Override
    public void setChildrenRecyclerView(List<Child> childrenList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        childrenRecyclerView.setLayoutManager(linearLayoutManager);
        ChildrenAdapter childrenAdapter = new ChildrenAdapter(childrenList);
        childrenRecyclerView.setAdapter(childrenAdapter);
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


    @OnClick(R.id.recording_mic)
    public void onRecordClick(){
        EventBus.getDefault().post(new AddFragmentEvent(new RecordingFragment()));
    }




}

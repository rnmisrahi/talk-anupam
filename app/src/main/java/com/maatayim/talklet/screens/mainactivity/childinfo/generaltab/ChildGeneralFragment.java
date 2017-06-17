package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection.ChildGeneralModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.recordings.RecordingsAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.GeneralTipTicket;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/6/2017.
 */

public class ChildGeneralFragment extends TalkletFragment implements ChildGeneralContract.View{

    public static final String ARG_ID = "babyId";
    public static final int DEFAULT_GAP = 120;
    public static final int HALF_GAP = DEFAULT_GAP / 2;

    @Inject
    ChildGeneralContract.Presenter presenter;

    private String babyId;

    @BindView(R.id.tips_view_pager_child_ata)
    ViewPager tipsViewPager;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicator;

    @BindView(R.id.recordings_recycler_view)
    RecyclerView recordingsRecyclerView;


    public static ChildGeneralFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        ChildGeneralFragment fragment = new ChildGeneralFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new ChildGeneralModule(this)).inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_child_general, container, false);
        ButterKnife.bind(this, view);

        presenter.getData(babyId);
        return view;
    }

    @Override
    public void onDataReceived() {

    }


    @Override
    public void updateTipsViewPager(List<GeneralTipTicket> ticketList) {
        tipsViewPager.setPadding(DEFAULT_GAP, 0, DEFAULT_GAP, 0);
        tipsViewPager.setClipToPadding(false);
        tipsViewPager.setPageMargin(HALF_GAP);

        initializeViewPager(ticketList);
    }

    private void initializeViewPager(List<GeneralTipTicket> ticketList){

        GeneralTipAdapter pagerAdapter = new GeneralTipAdapter(
                getActivity().getSupportFragmentManager(), ticketList);
        tipsViewPager.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(tipsViewPager);
    }

    @Override
    public void onTipsLoadError() {
        Toast.makeText(getContext(), "Failed load child's tips", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initRecordingsRecyclerView(List<RecordingObj> recordings){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recordingsRecyclerView.setLayoutManager(linearLayoutManager);
        RecordingsAdapter vendorsAdapter = new RecordingsAdapter(recordings);
        recordingsRecyclerView.setAdapter(vendorsAdapter);
    }
}

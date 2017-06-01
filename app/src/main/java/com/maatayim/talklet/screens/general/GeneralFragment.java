package com.maatayim.talklet.screens.general;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletFragment;
import com.maatayim.talklet.screens.general.generalticket.GeneralTipTicket;
import com.maatayim.talklet.screens.general.generalticket.GeneralTipAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralFragment extends TalkletFragment implements GeneralContract.View{

    @Inject
    GeneralContract.Presenter presenter;

    @BindView(R.id.tips_view_pager)
    ViewPager tipsViewPager;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicator;

    @Inject
    public void setPresenter(GeneralContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_main, container, false);
        ButterKnife.bind(this, view);

//        presenter = new GeneralPresenter(this, new RepositoryImpl(new LocalData(), new RemoteData()));
        presenter.getData();
        return view;

    }

    @Override
    public void onDataReceived(List<GeneralTipTicket> ticketList) {
        initializeViewPager(ticketList);
    }


    private void initializeViewPager(List<GeneralTipTicket> ticketList){

        GeneralTipAdapter pagerAdapter = new GeneralTipAdapter(
                getActivity().getSupportFragmentManager(), ticketList);
        tipsViewPager.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(tipsViewPager);
    }

}

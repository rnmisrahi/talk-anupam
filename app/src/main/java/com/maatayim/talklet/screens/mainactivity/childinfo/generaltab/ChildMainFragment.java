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
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FavoriteWordsAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager.WordAdviceAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.injection.ChildMainModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.recordings.RecordingsAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.wordsoftheday.WordOfTheDayAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipsAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.DEFAULT_GAP;
import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.HALF_GAP;
import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.TOP_MARGIN;

/**
 * Created by Sophie on 6/6/2017
 */

public class ChildMainFragment extends TalkletFragment implements ChildMainContract.View {

    public static final String ARG_ID = "babyId";
    public static final int DEFAULT_GAP = 120;
    public static final int HALF_GAP = DEFAULT_GAP / 2;

    @Inject
    ChildMainContract.Presenter presenter;

    private int babyId;

    @BindView(R.id.tips_view_pager_child_ata)
    ViewPager tipsViewPagerGtab;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicator;

    @BindView(R.id.recordings_recycler_view)
    RecyclerView recordingsRecyclerView;

    @BindView(R.id.words_view_pager)
    ViewPager wordsPager;


    public static ChildMainFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        ChildMainFragment fragment = new ChildMainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getInt(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new ChildMainModule(this)).inject(this);

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
    public void onDataReceived(GeneralTabChildObj generalTabChildObj) {
        updateTipsViewPager(generalTabChildObj.getTips());
        initializeWordsViewPager(generalTabChildObj.getWordsOfTheDay());

    }


    @Override
    public void updateTipsViewPager(List<TipTicket> ticketList) {
        tipsViewPagerGtab.setPadding(DEFAULT_GAP, TOP_MARGIN, DEFAULT_GAP, TOP_MARGIN);
        tipsViewPagerGtab.setClipToPadding(false);
        tipsViewPagerGtab.setPageMargin(HALF_GAP);

        initializeViewPager(ticketList);
    }

    private void initializeViewPager(List<TipTicket> ticketList) {

        TipsAdapter pagerAdapter = new TipsAdapter(
                getChildFragmentManager(), ticketList, false, false);
        tipsViewPagerGtab.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(tipsViewPagerGtab);
    }

    @Override
    public void onDataLoadError() {
        Toast.makeText(getContext(), "Failed load child's tips", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initRecordingsRecyclerView(List<RecordingObj> recordings) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recordingsRecyclerView.setLayoutManager(linearLayoutManager);
        RecordingsAdapter vendorsAdapter = new RecordingsAdapter(recordings);
        recordingsRecyclerView.setAdapter(vendorsAdapter);
    }

    @Override
    public void onDownloadError() {

    }


    private void initializeWordsViewPager(List<DaysWordsObj> wordstList) {
        wordsPager.setPadding(DEFAULT_GAP, 5, DEFAULT_GAP, 5);
        wordsPager.setClipToPadding(false);
        wordsPager.setPageMargin(HALF_GAP);

        WordOfTheDayAdapter pagerAdapter = new WordOfTheDayAdapter(
                getChildFragmentManager(), wordstList);
        wordsPager.setAdapter(pagerAdapter);

    }
}

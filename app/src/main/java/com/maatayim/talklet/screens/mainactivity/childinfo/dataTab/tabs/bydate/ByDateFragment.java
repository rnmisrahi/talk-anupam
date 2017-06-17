package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.CustomProgressBar;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CustomCalendarViewAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection.ByDateModule;
import com.maatayim.talklet.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/11/2017.
 */

public class ByDateFragment extends TalkletFragment implements ByDateContract.View {

    public static final String ARG_ID = "babyId";

    @Inject
    ByDateContract.Presenter presenter;

    @BindView(R.id.words_goal_amount)
    TextView todaysSaidWords;

    @BindView(R.id.calendar_horizontal_rv)
    RecyclerView horizontalCalendar;

    @BindView(R.id.todays_date)
    TextView todaysDate;

    @BindView(R.id.unique_progressbar)
    CustomProgressBar uniqueProgressBar;

    @BindView(R.id.new_progressbar)
    CustomProgressBar newProgressBar;

    @BindView(R.id.advance_progressbar)
    CustomProgressBar advanceProgressBar;

    private String babyId;


    public static ByDateFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        ByDateFragment fragment = new ByDateFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bydate, container, false);
        ButterKnife.bind(this, view);
        presenter.getData(babyId);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new ByDateModule(this)).inject(this);

    }


    @Override
    public void onWordsTypesDataReceived(Pair<Integer, Integer> totalWordsCount, Pair<Integer, Integer> uniqueWordsCount, Pair<Integer, Integer> newWordsCount, Pair<Integer, Integer> advanceWordsCount) {
        todaysSaidWords.setText(String.valueOf(totalWordsCount.first));

        uniqueProgressBar.initProgressBar(uniqueWordsCount.first, totalWordsCount.first, "Unique");
        newProgressBar.initProgressBar(newWordsCount.first, totalWordsCount.first, "New");
        advanceProgressBar.initProgressBar(advanceWordsCount.first, totalWordsCount.first, "Advance");
    }

    @Override
    public void loadCalendarData(List<CalendarWordsObj> calendarList) {

        todaysDate.setText(Utils.getTodaysDateStr());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalCalendar.setLayoutManager(linearLayoutManager);
        CustomCalendarViewAdapter vendorsAdapter = new CustomCalendarViewAdapter(calendarList);
        horizontalCalendar.setAdapter(vendorsAdapter);
    }


    @Override
    public void wordsCountLoadError() {
        Toast.makeText(getContext(), "Error in loading word data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorOnLoadCalendarData() {
        Toast.makeText(getContext(), "Error in loading calendar data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }
}

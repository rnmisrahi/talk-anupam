package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.CustomProgressBar;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CustomCalendarViewAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.injection.ByDateModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/11/2017
 */

public class ByDateFragment extends TalkletFragment implements ByDateContract.View {

    public static final String ARG_ID = "babyId";
    public static final int RIGHT_PADDING = 5;
    public static final int ITEM_WIDTH = 50;


    @Inject
    ByDateContract.Presenter presenter;

    @BindView(R.id.words_goal_amount)
    TextView todaysSaidWords;

    @BindView(R.id.calendar_horizontal_rv)
    RecyclerView horizontalCalendarRV;

    @BindView(R.id.todays_date)
    TextView todaysDate;

    @BindView(R.id.unique_progressbar)
    CustomProgressBar uniqueProgressBar;

    @BindView(R.id.new_progressbar)
    CustomProgressBar newProgressBar;

    @BindView(R.id.advance_progressbar)
    CustomProgressBar advanceProgressBar;

    private String babyId;
    private CustomCalendarViewAdapter calendarAdapter;
    private int prevMiddle = -1;
    private LinearLayoutManager linearLayoutManager;


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
    public void loadCalendarData(final List<CalendarWordsObj> calendarList) {

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalCalendarRV.setLayoutManager(linearLayoutManager);

        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(horizontalCalendarRV);

        calendarAdapter = new CustomCalendarViewAdapter(calendarList, true);
        horizontalCalendarRV.setAdapter(calendarAdapter);

        int offset = Utils.getCenterHorizontalScreenCoord(getActivity())-ITEM_WIDTH;
        int middle = calendarList.size()- RIGHT_PADDING-1;

        linearLayoutManager.scrollToPositionWithOffset(middle, offset);
        selectItem(calendarList, middle, true);
        changeDisplayDataByDay(middle, calendarList);

        horizontalCalendarRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    updateSelectedDay(calendarList);
                }
            }
        });

    }


    private void selectItem(List<CalendarWordsObj> calendarList, int middle, boolean isSingleItemChanged){
        calendarList.get(middle).setMiddle(true);
        prevMiddle = middle;
        if(isSingleItemChanged){
            calendarAdapter.notifyItemChanged(prevMiddle);
        }else{
            calendarAdapter.notifyDataSetChanged();
        }

    }



    private void updateSelectedDay(List<CalendarWordsObj> calendarList) {
        int firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        int lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();


        int middle = (int) Math.ceil((lastItem + firstItem) / 2);

        if (prevMiddle != -1) {
            calendarList.get(prevMiddle).setMiddle(false);
        }
        selectItem(calendarList, middle, false);

        changeDisplayDataByDay(middle, calendarList);
    }

    private void changeDisplayDataByDay(int middle, List<CalendarWordsObj> calendarList) {
        CalendarWordsObj midDate = calendarList.get(middle);

        if (midDate != null){
            updateView(midDate.getWordsCount(), (midDate.getDate()));
        }

    }

    ////////////This is for future version
//    private void updateView(Pair<Integer, Integer> totalWordsCount, Pair<Integer, Integer> uniqueWordsCount,
//                            Pair<Integer, Integer> newWordsCount, Pair<Integer, Integer> advanceWordsCount,
//                            Date date){
//        todaysSaidWords.setText(String.valueOf(totalWordsCount.first));
//        todaysDate.setText(Utils.getTodaysDateStr(date));
////        uniqueProgressBar.initProgressBar(uniqueWordsCount.first, totalWordsCount.first, "Unique");
////        newProgressBar.initProgressBar(newWordsCount.first, totalWordsCount.first, "New");
////        advanceProgressBar.initProgressBar(advanceWordsCount.first, totalWordsCount.first, "Advance");
//    }

    private void updateView(int totalWordsCount,  Date date){
        todaysSaidWords.setText(String.valueOf(totalWordsCount));
        if(date!=null){
            todaysDate.setText(Utils.getTodaysDateStr(date));
        }
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

package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording;

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
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CustomCalendarViewAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.injection.ByRecordingModule;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.recordingsrv.ByRecordingAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.utils.Utils;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.ByDateFragment.ITEM_WIDTH;
import static com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.ByDateFragment.RIGHT_PADDING;

/**
 * Created by Sophie on 6/20/2017.
 */

public class ByRecordingFragment extends TalkletFragment implements ByRecordingContract.View {


    private static final String ARG_ID = "babyId";
    private String babyId;


    @BindView(R.id.num_of_words_title)
    TextView wordsTitle;

    @BindView(R.id.calendar_horizontal_rv_by_recording)
    RecyclerView calendarRecyclerView;

    @BindView(R.id.todays_date)
    TextView todaysDate;

    @BindView(R.id.records_recycler_view)
    RecyclerView recordsRecyclerView;


    @Inject
    ByRecordingContract.Presenter presenter;

    private LinearLayoutManager linearLayoutManagerDates;
    private int prevMiddle = -1;
    private CustomCalendarViewAdapter calendarAdapter;
    private LinearLayoutManager linearLayoutManagerRecords;


    public static ByRecordingFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        ByRecordingFragment fragment = new ByRecordingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new ByRecordingModule(this)).inject(this);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_recording, container, false);
        ButterKnife.bind(this, view);
        presenter.getData(babyId);
        return view;

    }


    @Override
    public void loadCalendarData(final List<CalendarWordsObj> calendarList) {

        linearLayoutManagerDates = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        calendarRecyclerView.setLayoutManager(linearLayoutManagerDates);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(calendarRecyclerView);

        calendarAdapter = new CustomCalendarViewAdapter(calendarList, false);
        calendarRecyclerView.setAdapter(calendarAdapter);

        int offset = Utils.getCenterHorizontalScreenCoord(getActivity())-ITEM_WIDTH;
        int middle = calendarList.size()- RIGHT_PADDING-1;
        linearLayoutManagerDates.scrollToPositionWithOffset(middle, offset);
        selectItem(calendarList, middle, true);
        changeDisplayDataByDay(middle, calendarList);

        calendarRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    updateSelectedDay(calendarList);
                }
            }
        });

    }

    @Override
    public void errorOnLoadCalendarData() {

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
        int firstItem = linearLayoutManagerDates.findFirstCompletelyVisibleItemPosition();
        int lastItem = linearLayoutManagerDates.findLastCompletelyVisibleItemPosition();


        int middle = (int) Math.ceil((lastItem + firstItem) / 2);

        if (prevMiddle != -1) {
            calendarList.get(prevMiddle).setMiddle(false);
        }
        selectItem(calendarList, middle, false);

        changeDisplayDataByDay(middle, calendarList);
    }

    private void changeDisplayDataByDay(int middle, List<CalendarWordsObj> calendarList) {
        WordsCount wordsCount = calendarList.get(middle).getWordsCount();

        if (wordsCount != null){
            updateView(wordsCount.getTotalWordsCount(),
                    calendarList.get(middle).getDate(),
                    calendarList.get(middle).getRecordsList());
        }

    }


    private void updateView(Pair<Integer, Integer> totalWordsCount,
                            Date date,
                            List<RecordingObj> recordsList){
        wordsTitle.setText(getString(R.string.words_num, totalWordsCount.first));
        todaysDate.setText(Utils.getTodaysDateStr(date));
        initRecordingRecyclerView(recordsList);
    }


    private void initRecordingRecyclerView(List<RecordingObj> recordsList){
        linearLayoutManagerRecords = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recordsRecyclerView.setLayoutManager(linearLayoutManagerRecords);

        ByRecordingAdapter recordsAdapter = new ByRecordingAdapter(recordsList);
        recordsRecyclerView.setAdapter(recordsAdapter);
    }


}

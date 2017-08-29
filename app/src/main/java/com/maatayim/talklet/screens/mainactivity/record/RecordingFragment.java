package com.maatayim.talklet.screens.mainactivity.record;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipsAdapter;
import com.maatayim.talklet.screens.mainactivity.record.children.RecordChildrenAdapter;
import com.maatayim.talklet.screens.mainactivity.record.injection.RecordModule;
import com.maatayim.talklet.utils.Utils;
import com.viewpagerindicator.CirclePageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.HALF_GAP;
import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.TOP_MARGIN;

/**
 * Created by Sophie on 6/27/2017.
 */

public class RecordingFragment extends TalkletFragment implements RecordContract.View {


    public static final int RIGHT_GAP = 240;
    public static final int LEFT_GAP = 10;
    private static String ARG_RECORD = "mediaRecorder";
    @BindView(R.id.children_recyclerView)
    RecyclerView childrenRV;

    @BindView(R.id.record_time_duration)
    TextView recordTimer;

    @BindView(R.id.message_flag)
    ImageView message_flag;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;


    @BindView(R.id.tips_view_pager)
    ViewPager tips_view_pager;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicator;


    private Timer myTimer;

    @Inject
    RecordContract.Presenter presenter;

    private boolean isOpened = false;
    private long tStart;

    private MediaRecordWrapper mediaRecorder;
    private ConstraintSet constraintSet1;
    private ConstraintSet constraintSet2;
    private boolean changed;
    private RecordChildrenAdapter childrenAdapter;
    private TipsAdapter pagerAdapter;
    private static List<TipTicket> originalTipsList = new ArrayList<>();


    public static RecordingFragment newInstance(MediaRecordWrapper mediaRecorder) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_RECORD, mediaRecorder);
        RecordingFragment fragment = new RecordingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mediaRecorder = getArguments().getParcelable(ARG_RECORD);
        }
        EventBus.getDefault().register(this);

        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new RecordModule(this)).inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);
        presenter.getData();
        myTimer = new Timer();
        setTimer(myTimer);

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }


        }, 0, 1000);
        tStart = System.currentTimeMillis();

        mediaRecorder.start();

        constraintSet1 = new ConstraintSet();
        constraintSet1.clone(getContext(), R.layout.fragment_record);
        constraintSet2 = new ConstraintSet();
        constraintSet2.clone(getContext(), R.layout.fragment_record_tips_opened);

        changed = false;
        return view;
    }


    @Override
    public void onDataReceived(List<MainScreenChild> mainScreenChildren) {

        List<ChildRecObj> children = new ArrayList<>();
        for (MainScreenChild mainScreenChild : mainScreenChildren) {
            children.add(new ChildRecObj(mainScreenChild));
        }

        setChildrenRecyclerView(children);

    }


    private void setChildrenRecyclerView(List<ChildRecObj> childrenList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        childrenRV.setLayoutManager(linearLayoutManager);
        childrenAdapter = new RecordChildrenAdapter(childrenList);
        childrenRV.setAdapter(childrenAdapter);
    }


    @OnClick(R.id.stop_recording_mic)
    public void onStopRecordClick() {
        //// TODO: 6/27/2017 stop streaming
        getActivity().onBackPressed();
    }

    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        getActivity().runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here
//            Date dt = new Date();

            recordTimer.setText(Utils.getTimerStr(tStart));

        }
    };


    @OnClick({R.id.message_flag, R.id.exit_flag})
    public void onShowTipsClick() {
        if (!changed) {
//            if(childrenAdapter.getSelectedChildrenNum() == 1){
//                List<ChildRecObj> selectedChildren = childrenAdapter.getSelectedChildren();
//                List<TipTicket> tipTickets = tipsMapper(selectedChildren);
//                pagerAdapter.setDataSet(tipTickets);
//                pagerAdapter.updateBaseId(tipTickets.size());
//                tips_view_pager.setAdapter(pagerAdapter);
//
//            }else{
//                pagerAdapter.setDataSet(originalTipsList);
//                pagerAdapter.updateBaseId(originalTipsList.size());
//                tips_view_pager.setAdapter(pagerAdapter);
//            }

        }
        handleTouch();
    }

    private List<TipTicket> tipsMapper(List<ChildRecObj> selectedChildren){
        List<TipTicket> tickets = new ArrayList<>();

        for (ChildRecObj selectedChild : selectedChildren) {

            List<MainScreenChild.Tip> tips = selectedChild.getTips();

            for (MainScreenChild.Tip tip : tips) {
                tickets.add(new TipTicket(tip.getText(), tip.getTipType(), selectedChild.getUrl()));
            }
        }

        return tickets;

    }


    public void handleTouch() {
        if (changed) {
            minimize();
        } else {
            maximize();
        }
        changed = !changed;
    }

    void minimize() {
        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(200);
        TransitionManager.beginDelayedTransition(constraintLayout, transition);
        ConstraintSet constraint = constraintSet1;
        constraint.applyTo(constraintLayout);

    }


    void maximize() {
        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(200);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(@NonNull Transition transition) {

            }

            @Override
            public void onTransitionEnd(@NonNull Transition transition) {
                makeViewPagerVisible();

            }

            private void makeViewPagerVisible() {
                tips_view_pager.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionCancel(@NonNull Transition transition) {

            }

            @Override
            public void onTransitionPause(@NonNull Transition transition) {

            }

            @Override
            public void onTransitionResume(@NonNull Transition transition) {

            }
        });
        TransitionManager.beginDelayedTransition(constraintLayout, transition);
        ConstraintSet constraint = constraintSet2;
        constraint.applyTo(constraintLayout);

    }


//    @OnClick(R.id.message_flag)
//    public void onExitTipViewClick(){
//        tips_view_pager.setVisibility(View.GONE);
//        pageIndicator.setVisibility(View.GONE);
//        exit_flag.setVisibility(View.GONE);
//        viewPagerBg.setVisibility(View.GONE);
//        message_flag.setImageDrawable(getResources().getDrawable(R.drawable.message));
//
//    }


    @Override
    public void initViewpager(List<TipTicket> tipsTickets, boolean isMorethanOneChildSelected) {

        tips_view_pager.setPadding(LEFT_GAP, TOP_MARGIN, RIGHT_GAP, TOP_MARGIN);
        tips_view_pager.setClipToPadding(false);
        tips_view_pager.setPageMargin(HALF_GAP);

//        boolean isMorethanOneChildSelected = false;
//        if (childrenAdapter.getSelectedChildrenNum()>1){
//            isMorethanOneChildSelected = true;
//        }

        originalTipsList = tipsTickets;

        //// TODO: 7/4/2017 show baby img if more than one selected
        pagerAdapter = new TipsAdapter(
                getChildFragmentManager(), tipsTickets, true, isMorethanOneChildSelected);
        tips_view_pager.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(tips_view_pager);
    }

    @Override
    public void onTipsLoadError() {

    }

    @Override
    public boolean onBackPressed() {
        myTimer.cancel();
        mediaRecorder.stop();
        return super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void childSetChanged(ChildClickEvent event){
        if (event.isClickedChildSetChanged()){
            List<ChildRecObj> selectedChildren = childrenAdapter.getSelectedChildren();
            List<TipTicket> tipTickets = tipsMapper(selectedChildren);
            pagerAdapter.setDataSet(tipTickets);
            pagerAdapter.updateBaseId(tipTickets.size());
            tips_view_pager.setAdapter(pagerAdapter);
        }
    }
}

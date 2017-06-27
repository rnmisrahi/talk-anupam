package com.maatayim.talklet.screens.mainactivity.record;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.children.ChildrenAdapter;
import com.maatayim.talklet.screens.mainactivity.record.children.RecordChildrenAdapter;
import com.maatayim.talklet.screens.mainactivity.record.injection.RecordModule;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyf.injection.AboutBabyModule;
import com.maatayim.talklet.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sophie on 6/27/2017.
 */

public class RecordingFragment extends TalkletFragment implements RecordContract.View {


    @BindView(R.id.children_recyclerView)
    RecyclerView childrenRV;

    @BindView(R.id.record_time_duration)
    TextView recordTimer;

    private Timer myTimer;

    @Inject
    RecordContract.Presenter presenter;
    private long tStart;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new RecordModule(this)).inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);
        presenter.getData();
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }


        }, 0, 1000);
        tStart = System.currentTimeMillis();
        return view;
    }


    @Override
    public void onDataReceived(List<Child> children) {
        setChildrenRecyclerView(children);
    }


    private void setChildrenRecyclerView(List<Child> childrenList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        childrenRV.setLayoutManager(linearLayoutManager);
        RecordChildrenAdapter vendorsAdapter = new RecordChildrenAdapter(childrenList);
        childrenRV.setAdapter(vendorsAdapter);
    }


    @OnClick(R.id.stop_recording_mic)
    public void onStopRecordClick(){
        //// TODO: 6/27/2017 stop streaming
        myTimer.cancel();
        getActivity().onBackPressed();
    }

    private void TimerMethod()
    {
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


}

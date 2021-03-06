package com.maatayim.talklet.baseline.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.BaseContract;

import java.util.Timer;

/**
 * Created by Sophie on 5/21/2017
 */

public abstract class TalkletFragment extends Fragment implements BaseContract.View {

    private String title;
    private Timer timer;


    public TalkletFragment(){
//        EventBus.getDefault().register(this);
    }



//    @Override
//    public void onDestroy() {
//        // Unregister
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }


    public void setTimer(Timer timer) {
        this.timer = timer;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public void setTitle(String title) {
        this.title = title;
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);

    }

    public void displayTitle(){
        setTitle(title);
    }


    public void finish() {
        getActivity().onBackPressed();
    }

    public boolean onBackPressed() {
        //// TODO: 6/27/2017 stop streaming
        if (timer != null){
            timer.cancel();
        }
        hideKeyboard();
        return false;
    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public String getTitle() {
        return title;
    }
}

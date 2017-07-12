package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.wordsoftheday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.DaysWordsObj;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sophie on 6/26/2017
 */

public class WordOfTheDayFragment extends Fragment{
    public static final String TICKET = "ticket";
    private DaysWordsObj ticket;
    private PopupWindow mpopup;
    private LayoutInflater inflater;

    @BindView(R.id.words_title)
    TextView title;

    @BindView(R.id.subText)
    TextView subtext;
    private DaysWordsObj wordItem;


    public static WordOfTheDayFragment newInstance(DaysWordsObj wordItem) {
        WordOfTheDayFragment fragment = new WordOfTheDayFragment();
        Bundle args = new Bundle();
        args.putParcelable(TICKET, wordItem);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ticket = getArguments().getParcelable(TICKET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.word_of_the_day_card, container, false);
        ButterKnife.bind(this, view);
        setCard(ticket);
        return view;
    }


    public void setCard(DaysWordsObj wordItem){
        this.wordItem = wordItem;
        title.setText(wordItem.getWord());
//        subtext.setText(getString(R.string.word_subtext, wordItem.getPercentUsage(), wordItem.getAge()));
        subtext.setText(wordItem.getUsage());


    }

    @OnClick(R.id.plus_extend)
    public void onExtendClick(){


        View popUpView = inflater.inflate(R.layout.word_of_day_popup,
                null); // inflating popup layout

        TextView title = (TextView) popUpView.findViewById(R.id.title);
        RecyclerView infoRV = (RecyclerView) popUpView.findViewById(R.id.info_rv);
        RecyclerView questionRV = (RecyclerView) popUpView.findViewById(R.id.questions_rv);
        RecyclerView activitesRV = (RecyclerView) popUpView.findViewById(R.id.activities_rv);
        RecyclerView ourFaveRV = (RecyclerView) popUpView.findViewById(R.id.our_fav_rv);
        ImageView exitBotton = (ImageView) popUpView.findViewById(R.id.exit_popup);


        mpopup = new PopupWindow(popUpView, ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true); // Creation of popup
        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 10); // Displaying popup

        title.setText(wordItem.getWord());


        exitBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopup.dismiss();
            }
        });

        initializeRV(infoRV, wordItem.getInfoList());
        initializeRV(questionRV, wordItem.getQuestionList());
        initializeRV(activitesRV, wordItem.getActivitesList());
        initializeRV(ourFaveRV, wordItem.getOurFaveList());
    }


    private void initializeRV(RecyclerView rView, List<String> data){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rView.setLayoutManager(linearLayoutManager);
        PopupAdapter vendorsAdapter = new PopupAdapter(data);
        rView.setAdapter(vendorsAdapter);
    }


}

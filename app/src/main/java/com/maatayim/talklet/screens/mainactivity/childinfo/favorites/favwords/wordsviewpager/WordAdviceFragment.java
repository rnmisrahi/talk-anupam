package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager.advicerv.AdviceAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/26/2017
 */

public class WordAdviceFragment extends Fragment{
    public static final String TICKET = "ticket";
    private SpecialWords ticket;

    @BindView(R.id.words_title)
    TextView title;

    @BindView(R.id.subtext)
    TextView subtext;


    @BindView(R.id.how_to_use_word_rv)
    RecyclerView stepsRV;

    public static WordAdviceFragment newInstance(SpecialWords wordItem) {
        WordAdviceFragment fragment = new WordAdviceFragment();
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
        View view = inflater.inflate(R.layout.new_word_card, container, false);
        ButterKnife.bind(this, view);
        setCard(ticket);
        return view;
    }


    public void setCard(SpecialWords wordItem){
        title.setText(wordItem.getWord());
        subtext.setText(getString(R.string.word_subtext, wordItem.getPercentUsage(), wordItem.getAge()));
        initializeRecyclerView(wordItem.getAdviceList());


    }

    private void initializeRecyclerView(List<String> adviceList){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        stepsRV.setLayoutManager(linearLayoutManager);
        AdviceAdapter favoriteWordsAdapter = new AdviceAdapter(adviceList);
        stepsRV.setAdapter(favoriteWordsAdapter);
    }

}

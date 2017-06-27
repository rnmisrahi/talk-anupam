package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.OpenWordsTableEvent;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.TableWordsAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sophie on 6/25/2017
 */

public class CustomWordSection extends RelativeLayout {

    @BindView(R.id.new_words_section_title)
    TextView wordSectionTitle;

    @BindView(R.id.recycler_view_words)
    RecyclerView wordsListRV;

    @BindView(R.id.section_title_background)
    View sectionTitleBackground;



    private String wordsSectionTypeClose;
    private String wordsSectionTypeOpen;
    private boolean isSectionClicked = false;


    public CustomWordSection(Context context) {
        super(context);
        init(context);
    }

    public CustomWordSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        init(context, attrs);
    }

    public CustomWordSection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        handleAttrs(context, attrs);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.custom_word_section,
                this,
                true);
        ButterKnife.bind(this, view);

        if (isInEditMode()) {

        }

    }

    private void handleAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomWordSection,
                0, 0);

        try {
            wordsSectionTypeClose = typedArray.getString(R.styleable.CustomWordSection_wordSectionTitle);
            wordsSectionTypeOpen = typedArray.getString(R.styleable.CustomWordSection_wordSectionTitleOpen);

            wordSectionTitle.setText(wordsSectionTypeClose);

        } finally {
            typedArray.recycle();
        }
    }


    public void setRecyclerView(List<SpecialWords> wordsList){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        wordsListRV.setLayoutManager(linearLayoutManager);
//        wordsListRV.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        TableWordsAdapter tableWordsAdapter = new TableWordsAdapter(wordsList);
        wordsListRV.setAdapter(tableWordsAdapter);
    }

    @OnClick(R.id.section_title_background)
    public void  onSectionTitleClick(){
        if(!isSectionClicked){
            EventBus.getDefault().post(new OpenWordsTableEvent(wordsSectionTypeClose, this));
            wordsListRV.setVisibility(VISIBLE);
            wordSectionTitle.setText(wordsSectionTypeOpen);
            isSectionClicked = true;
            return;
        }else{
            wordsListRV.setVisibility(GONE);
            wordSectionTitle.setText(wordsSectionTypeClose);
            isSectionClicked = false;
            return;
        }

    }




}

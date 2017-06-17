package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.CustomProgressBar;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.injection.GeneralTabModule;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/7/2017
 */

public class GeneralTabFragment extends TalkletFragment implements GeneralTabContract.View {

    public static final String ARG_ID = "babyId";
    private static final String WORDS = "words";
    @Inject
    GeneralTabContract.Presenter presenter;


    @BindView(R.id.deco_view)
    DecoView decoView;

    @BindView(R.id.unique_progressbar)
    CustomProgressBar uniqueProgressBar;

    @BindView(R.id.new_progressbar)
    CustomProgressBar newProgressBar;

    @BindView(R.id.advance_progressbar)
    CustomProgressBar advanceProgressBar;

    @BindView(R.id.words_count_view)
    TextView wordsTextView;

    private String babyId;


    public static GeneralTabFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        GeneralTabFragment fragment = new GeneralTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new GeneralTabModule(this)).inject(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_tab_general, container, false);
        ButterKnife.bind(this, view);
        presenter.getData(babyId);
        return view;

    }

    @Override
    public void onDataReceived(Pair<Integer, Integer> totalwordsCount,
                               Pair<Integer, Integer> uniqueWordsCount,
                               Pair<Integer, Integer> newWordsCount,
                               Pair<Integer, Integer> advanceWordsCount) {

        initializePieChart(totalwordsCount.first, totalwordsCount.second);

        uniqueProgressBar.initProgressBar(uniqueWordsCount.first, totalwordsCount.first, "Unique");
        newProgressBar.initProgressBar(newWordsCount.first, totalwordsCount.first, "New");
        advanceProgressBar.initProgressBar(advanceWordsCount.first, totalwordsCount.first, "Advance");
    }




    public void initializePieChart(final int wordsSaid, final int totalWords){
//        pieChart.setCenterText(getString(R.string.words_chart, wordsSaid, totalWords));

        final SeriesItem seriesItem = new SeriesItem.Builder(getResources().getColor(R.color.primary_background_color))
                .setRange(0, totalWords, 0)
                .build();

        int backIndex = decoView.addSeries(seriesItem);


        int series1Index = decoView.addSeries(
                new SeriesItem.Builder(getResources().getColor(R.color.pie_chart_bg1),
                        getResources().getColor(R.color.pie_chart_bg2))
                        .setRange(0, totalWords, 0)
                        .setInitialVisibility(false)
                        .build());


        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                setTextViewWordCount(percentFilled*wordsSaid, totalWords);

            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        decoView.addEvent(new DecoEvent.Builder(totalWords)
                .setIndex(backIndex)
                .build());

        decoView.addEvent(new DecoEvent.Builder(wordsSaid)
                .setIndex(series1Index)
                .setDelay(50)
                .build());
    }


    public void setTextViewWordCount(float saidWords, int totalWords) {
        SpannableStringBuilder txt = new SpannableStringBuilder();
        txt.append(getString(R.string.words_chart, saidWords, totalWords));
        txt.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                0, 2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                txt.length()-WORDS.length(),
                txt.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordsTextView.setText(txt);

    }


    @Override
    public void wordsCountLoadError() {

    }


//    @Override
//    public void onStart(){
//        super.onStart();
//        getView().post(new Runnable() {
//            @Override
//            public void run() {
//                drawLineGraph();
//            }
//        });
//    }
//    /**
//     *  The code for creating the line graph
//     */
//    private void drawLineGraph(){
////        setupLineChart();
//
//        // Get the paint renderer to create the line shading.
//        Paint paint = lineChart.getRenderer().getPaintRender();
//        int height = lineChart.getHeight();
//
//        LinearGradient linGrad = new LinearGradient(0, 0, 0, height,
//                getResources().getColor(R.color.colorPrimary),
//                getResources().getColor(R.color.primary_background_color),
//                Shader.TileMode.REPEAT);
//        paint.setShader(linGrad);
//
////        setupLineChartData();
//
//        // Don't forget to refresh the drawing
//        lineChart.invalidate();
//    }



}
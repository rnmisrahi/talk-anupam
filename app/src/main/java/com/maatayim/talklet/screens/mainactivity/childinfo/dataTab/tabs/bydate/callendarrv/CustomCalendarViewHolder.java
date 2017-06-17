package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomCalendarViewHolder extends ViewHolder {

    @BindView(R.id.words_progress_bar)
    ProgressBar wordsProgressBar;

    @BindView(R.id.date_string)
    TextView dateStr;
    private final Context context;

    public CustomCalendarViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }


    public void setData(CalendarWordsObj item) {
        wordsProgressBar.setMax(item.getWordsCount().getTotalWordsCount().second);
        //// TODO: 6/17/2017 check if the itemView is in the horizontal center of the screen. if so : change color + position corrrection
//        wordsProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_vertical_accent));
        wordsProgressBar.setProgress(item.getWordsCount().getTotalWordsCount().first);
        dateStr.setText(Utils.getSpecificFormattedDate(item.getDate(), "d/M"));
    }


}
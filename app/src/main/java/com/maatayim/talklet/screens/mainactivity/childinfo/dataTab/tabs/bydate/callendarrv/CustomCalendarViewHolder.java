package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

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

    public CustomCalendarViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setData(CalendarWordsObj item) {
        wordsProgressBar.setProgress(item.getWordsCount().getTotalWordsCount().first);
        dateStr.setText(Utils.getSpecificFormattedDate(item.getDate(), "d/M"));
    }


}
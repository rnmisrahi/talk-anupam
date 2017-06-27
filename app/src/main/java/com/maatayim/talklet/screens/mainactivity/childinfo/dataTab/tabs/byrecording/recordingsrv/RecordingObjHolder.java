package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.recordingsrv;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecordingObjHolder extends ViewHolder {

    private View itemView;
    private Context context;

    @BindView(R.id.record_num)
    TextView recordNum;

    @BindView(R.id.record_details)
    TextView recordDetails;

    @BindView(R.id.words_counter)
    TextView wordsCounter;

    @BindView(R.id.words_counter_str)
    TextView wordsCounterStr;

    @BindView(R.id.words_details)
    TextView wordsDetails;

    private final List<TextView> textViews;
    private RecordingObj item;

    public RecordingObjHolder(final View itemView, final ByRecordingAdapter.OnClickListener onClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
        this.context = itemView.getContext();

        textViews = new ArrayList<>();
        textViews.add(recordNum);
        textViews.add(recordDetails);
        textViews.add(wordsCounter);
        textViews.add(recordDetails);
        textViews.add(wordsDetails);
        textViews.add(wordsCounterStr);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 6/21/2017 update all
//                itemView.setSelected(!itemView.isSelected());
                int itemPosition = RecordingObjHolder.this.getAdapterPosition();
                onClickListener.onClick(itemPosition);
            }
        });
    }


    public void setData(RecordingObj item) {
        if (item!= null) {

            this.item = item;

            recordNum.setText(context.getString(R.string.record_num, item.getNumber()));
            recordDetails.setText(context.getString(R.string.record_details,
                    item.getWordCount().first,
                    item.getWordCount().second,
                    item.getDurationStr()));

            wordsCounter.setText(String.valueOf(item.getWordCount().first));
            wordsDetails.setText(context.getString(R.string.words_details, 2, 18, 36));

            if (item.isSelected()) {
                setSelectedItem();
            } else {
                setUnSelectedItem();
            }

        }


    }

    private void setSelectedItem(){
        itemView.setBackgroundColor(context.getResources().getColor(R.color.recordings_bg));
        setAllViewsColor(R.color.Light_sea_green);
        wordsDetails.setVisibility(View.VISIBLE);
        wordsCounterStr.setTypeface(null, Typeface.BOLD);
    }

    private void setUnSelectedItem(){
        itemView.setBackgroundColor(context.getResources().getColor(R.color.white_bg));
        setAllViewsColor(R.color.text_color);
        wordsDetails.setVisibility(View.GONE);
        wordsCounterStr.setTypeface(null, Typeface.NORMAL);
    }

    private void setAllViewsColor(int color){
        for (TextView textView : textViews) {
            textView.setTextColor(context.getResources().getColor(color));
        }

    }


}
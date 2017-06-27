package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.recordings;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/7/2017.
 */

public class RecordingsHolder extends ViewHolder {

    @BindView(R.id.recording_text_view)
    TextView recordingTextView;
    private final Context context;

    public RecordingsHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }


    public void setData(RecordingObj recordingObj){
        String dateStr = Utils.getSpecificFormattedDate(recordingObj.getDate(), "dd.MM.yy");

        recordingTextView.setText(context.getString(R.string.recording_text,
                recordingObj.getNumber(),
                dateStr,
                recordingObj.getWordCount().first,
                recordingObj.getWordCount().second,
                recordingObj.getDurationStr()));
    }



}

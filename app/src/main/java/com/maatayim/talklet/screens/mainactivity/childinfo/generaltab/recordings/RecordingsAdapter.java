package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.recordings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;

import java.util.List;

/**
 * Created by Sophie on 6/7/2017.
 */
public class RecordingsAdapter extends RecyclerView.Adapter<RecordingsHolder> {
    public static final int MAX_RECORDS = 3;
    private List<RecordingObj> recordinglist;

    public RecordingsAdapter(List<RecordingObj> recordinglist) {
        this.recordinglist = recordinglist;
    }

    @Override
    public RecordingsHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recording_item, parent, false);
        return new RecordingsHolder(v);
    }

    @Override
    public void onBindViewHolder(RecordingsHolder holder, int position) {
        holder.setData(recordinglist.get(position));
    }

    @Override
    public int getItemCount() {
        if (recordinglist == null) {
            return 0;
        } if (recordinglist.size() > MAX_RECORDS){
            return MAX_RECORDS;
        }
        return recordinglist.size();
    }
}
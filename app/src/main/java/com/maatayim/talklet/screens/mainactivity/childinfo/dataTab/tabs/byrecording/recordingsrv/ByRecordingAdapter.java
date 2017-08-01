package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.byrecording.recordingsrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;

import java.util.List;

/**
 * Created by Sophie on 6/21/2017
 */
public class ByRecordingAdapter extends RecyclerView.Adapter<RecordingObjHolder> {
    public static final int MAX_VISIBLE_ITEMS = 4;
    private List<RecordingObj> recordingObjList;

    public ByRecordingAdapter(List<RecordingObj> recordingObjList) {

        if (recordingObjList.size() > MAX_VISIBLE_ITEMS){
            this.recordingObjList = recordingObjList.subList(0,4);
        }else{
            this.recordingObjList = recordingObjList;
        }
    }

    @Override
    public RecordingObjHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item_dates, parent, false);
        return new RecordingObjHolder(view, new OnClickListener(){

            @Override
            public void onClick(int itemPosition) {
                for(int i=0; i< recordingObjList.size(); i++){
//
                    if (i != itemPosition){
                        recordingObjList.get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBindViewHolder(RecordingObjHolder holder, int position) {
        holder.setData(recordingObjList.get(position));
    }


    @Override
    public int getItemCount() {
        if (recordingObjList == null) {
            return 0;
        }
        return recordingObjList.size();
    }

    interface OnClickListener{
        void onClick(int itemPosition);
    }
}
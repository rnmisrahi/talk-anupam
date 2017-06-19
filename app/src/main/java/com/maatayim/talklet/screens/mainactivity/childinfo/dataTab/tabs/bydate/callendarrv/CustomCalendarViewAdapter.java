package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;

import java.util.List;

/**
 * Created by Sophie on 6/11/2017
 */
public class CustomCalendarViewAdapter extends RecyclerView.Adapter<CustomCalendarViewHolder> {
    private List<CalendarWordsObj> itemsList;
    public static final int MAX_PADDING = 5;
    private int paddingCounter = 0;

    public CustomCalendarViewAdapter(List<CalendarWordsObj> itemsList) {
        this.itemsList = itemsList;
        for (int i=0; i<MAX_PADDING; i++){
            itemsList.add(0, new CalendarWordsObj(null, null, false));
            itemsList.add(new CalendarWordsObj(null, null, false));
            paddingCounter++;
        }
    }

    @Override
    public CustomCalendarViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_words_bar, parent, false);
        return new CustomCalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomCalendarViewHolder holder, int position) {
        holder.setData(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }

    public CalendarWordsObj getItem(int position) {
        return itemsList.get(position);
    }
}
package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.wordsoftheday;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;

import java.util.List;

/**
 * Created by Sophie on 7/10/2017
 */
public class PopupAdapter extends RecyclerView.Adapter<PopupHolder> {

    private List<String> detailsList;

    public PopupAdapter(List<String> detailsList) {
        this.detailsList = detailsList;
    }

    @Override
    public PopupHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_of_day_item, parent, false);
        return new PopupHolder(view);
    }

    @Override
    public void onBindViewHolder(PopupHolder holder, int position) {
        holder.setData(detailsList.get(position));
    }

    @Override
    public int getItemCount() {
        if (detailsList == null) {
            return 0;
        }
        return detailsList.size();
    }
}
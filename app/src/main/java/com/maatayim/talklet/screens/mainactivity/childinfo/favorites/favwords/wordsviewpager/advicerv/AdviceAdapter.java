package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager.advicerv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;

import java.util.List;

/**
 * Created by Sophie on 6/26/2017.
 */
public class AdviceAdapter extends RecyclerView.Adapter<AdviceHolder> {
    private List<String> advicelist;

    public AdviceAdapter(List<String> advices) {
        this.advicelist = advices;
    }

    @Override
    public AdviceHolder onCreateViewHolder(ViewGroup parent,
                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_usage_item, parent, false);
        return new AdviceHolder(v);
    }

    @Override
    public void onBindViewHolder(AdviceHolder holder, int position) {
        holder.setData(advicelist.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (advicelist == null) {
            return 0;
        }
        return advicelist.size();
    }
}
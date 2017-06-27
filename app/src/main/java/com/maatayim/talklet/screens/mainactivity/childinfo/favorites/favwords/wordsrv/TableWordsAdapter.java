package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.maatayim.talklet.R;

import java.util.List;

/**
 * Created by Sophie on 6/25/2017
 */
public class TableWordsAdapter extends RecyclerView.Adapter<TableWordsHolder> {

    private List<SpecialWords> tableWordslist;
    private Context context;

    public TableWordsAdapter(List<SpecialWords> tableWordslist) {
        this.tableWordslist = tableWordslist;
    }

    @Override
    public TableWordsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_section_item, parent, false);
        return new TableWordsHolder(view);
    }

    @Override
    public void onBindViewHolder(TableWordsHolder holder, int position) {
        holder.setData(tableWordslist.get(position), position);
//        animate(holder);
    }

    @Override
    public int getItemCount() {
        if (tableWordslist == null) {
            return 0;
        }
        return tableWordslist.size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

}
package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.maatayim.talklet.R;

import java.util.List;

/**
 * Created by Sophie on 6/22/2017.
 */
public class FavoriteWordsAdapter extends RecyclerView.Adapter<FavoriteWordsHolder> {
    private List<FourWordsObj> favWordsList;
    private Context context;

    public FavoriteWordsAdapter(List<FourWordsObj> favWordsList) {
        this.favWordsList = favWordsList;
    }

    @Override
    public FavoriteWordsHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_words_row_item, parent, false);
        return new FavoriteWordsHolder(v);
    }

    @Override
    public void onBindViewHolder(FavoriteWordsHolder holder, int position) {
        holder.setData(favWordsList.get(position));
//        animate(holder);
    }

    @Override
    public int getItemCount() {
        if (favWordsList == null) {
            return 0;
        }
        return favWordsList.size();
    }




}
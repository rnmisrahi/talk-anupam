package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingChild;

import java.util.List;

/**
 * Created by Sophie on 6/27/2017
 */
public class AboutBabyAdapter extends RecyclerView.Adapter<AboutBabyHolder> {
    private List<SettingChild> Childlist;

    public AboutBabyAdapter(List<SettingChild> Childlist) {
        this.Childlist = Childlist;
    }

    @Override
    public AboutBabyHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.about_baby_item, parent, false);
        return new AboutBabyHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutBabyHolder holder, int position) {
        holder.setData(Childlist.get(position));
    }

    @Override
    public int getItemCount() {
        if (Childlist == null) {
            return 0;
        }
        return Childlist.size();
    }
}
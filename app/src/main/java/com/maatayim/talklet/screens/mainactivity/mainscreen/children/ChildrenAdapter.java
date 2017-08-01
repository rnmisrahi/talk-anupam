package com.maatayim.talklet.screens.mainactivity.mainscreen.children;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;

import java.util.List;

/**
 * Created by Sophie on 6/6/2017.
 */

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenHolder>{

    private List<MainScreenChild> childrenList;
    private boolean isCountDataChildItemVisable;

    public ChildrenAdapter(List<MainScreenChild> childrenList, boolean isCountDataChildItemVisable){

        this.childrenList = childrenList;
        this.isCountDataChildItemVisable = isCountDataChildItemVisable;
    }


    @Override
    public ChildrenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_img, parent, false);
        return new ChildrenHolder(view, isCountDataChildItemVisable);
    }

    @Override
    public void onBindViewHolder(ChildrenHolder holder, int position) {
        holder.setData(childrenList.get(position));
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }
}

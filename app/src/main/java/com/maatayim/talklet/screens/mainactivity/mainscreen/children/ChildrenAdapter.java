package com.maatayim.talklet.screens.mainactivity.mainscreen.children;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.Child;

import java.util.List;

/**
 * Created by Sophie on 6/6/2017.
 */

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenHolder>{

    private List<Child> childrenList;

    public ChildrenAdapter(List<Child> childrenList){

        this.childrenList = childrenList;
    }


    @Override
    public ChildrenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_img, parent, false);
        return new ChildrenHolder(view);
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

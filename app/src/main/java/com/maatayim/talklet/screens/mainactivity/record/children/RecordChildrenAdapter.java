package com.maatayim.talklet.screens.mainactivity.record.children;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.maatayim.talklet.screens.mainactivity.record.ChildRecObj;

import java.util.List;

/**
 * Created by Sophie on 6/6/2017.
 */

public class RecordChildrenAdapter extends RecyclerView.Adapter<RecordChildrenHolder>{

    private List<ChildRecObj> childrenList;
    private int selectedCounter = 0;

    public RecordChildrenAdapter(List<ChildRecObj> childrenList){

        this.childrenList = childrenList;
        setAllChildSelected();


    }

    private void setAllChildSelected(){
        for (ChildRecObj child : childrenList) {
            child.setSelected(true);
        }
    }


    @Override
    public RecordChildrenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_record, parent, false);
        return new RecordChildrenHolder(view, new onSelectChildListener(){

            @Override
            public void onSelect(int position){
                notifyItemChanged(position);
                if(childrenList.get(position).isSelected()){
                    selectedCounter ++;
                }else{
                    selectedCounter --;
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(RecordChildrenHolder holder, int position) {
        holder.setData(childrenList.get(position));
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    interface onSelectChildListener{
        void onSelect(int position);
    }

    public int getSelectedChildrenNum(){
        return selectedCounter;
    }

    public ChildRecObj getSelectedChild(){
        for (ChildRecObj childRecObj : childrenList) {
            if (childRecObj.isSelected()){
                return childRecObj;
            }
        }
        return null;
    }
}

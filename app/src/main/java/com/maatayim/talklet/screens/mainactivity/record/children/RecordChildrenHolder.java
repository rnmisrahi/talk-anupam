package com.maatayim.talklet.screens.mainactivity.record.children;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.childinfo.ChildFragment;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Sophie on 6/6/2017
 */

public class RecordChildrenHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.child_item)
    CircleImageView childView;

    private final Context context;
    private Child child;

    public RecordChildrenHolder(View itemView, final RecordChildrenAdapter.onSelectChildListener onSelectChildListener) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                child.setSelected(!child.isSelected());
                int itemPosition = RecordChildrenHolder.this.getAdapterPosition();
                onSelectChildListener.onSelect(itemPosition);

            }
        });
    }

    public void setData(Child child) {

        this.child = child;

        Picasso.with(context)
                .load(child.getImg())
                .placeholder(R.drawable.pic)
                .into(childView);

        if(child.isSelected()){
            childView.setBackground(context.getResources().getDrawable(R.drawable.record_child_bg_selected));
        }else{
            childView.setBackground(context.getResources().getDrawable(R.drawable.record_child_bg_not_selected));
        }
    }
}
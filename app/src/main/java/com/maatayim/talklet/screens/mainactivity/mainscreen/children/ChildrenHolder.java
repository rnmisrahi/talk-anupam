package com.maatayim.talklet.screens.mainactivity.mainscreen.children;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.AddFragmentWithSharedElementEvent;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.childinfo.ChildFragment;
import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Sophie on 6/6/2017
 */

public class ChildrenHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.child_item)
    CircleImageView childView;

    @BindView(R.id.words_count_view)
    TextView wordCount;

    private final Context context;

    private final View itemView;

    private final boolean isCountDataChildItemVisable;

    private MainScreenChild child;

    public ChildrenHolder(View itemView, boolean isCountDataChildItemVisable) {
        super(itemView);
        context = itemView.getContext();
        this.itemView = itemView;
        this.isCountDataChildItemVisable = isCountDataChildItemVisable;
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> EventBus.getDefault()
                                                 .post(new AddFragmentWithSharedElementEvent(ChildFragment.newInstance
                                                         (child.getId()), childView)));
    }

    public void setData(MainScreenChild child) {

        this.child = child;
        Picasso.with(context)
               .load(child.getUrl())
               .fit()
               .centerCrop()
               .placeholder(R.drawable.pic)
               .into(childView);

        if (isCountDataChildItemVisable) {
            wordCount.setVisibility(View.VISIBLE);
            wordCount.setText(context.getString(R.string.word_count, child.getWordCount(), child
                    .getTotal()));
        } else {
            wordCount.setVisibility(View.GONE);
        }
    }
}

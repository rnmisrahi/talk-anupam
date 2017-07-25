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
    private MainScreenChild child;

    public ChildrenHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new AddFragmentWithSharedElementEvent(ChildFragment.newInstance(child.getId()),childView));
            }
        });
    }

    public void setData(MainScreenChild child) {

        this.child = child;
        Picasso.with(context)
                .load(child.getUrl())
                .placeholder(R.drawable.pic)
                .into(childView);

        wordCount.setText(context.getString(R.string.word_count, child.getWordCount(), child.getTotal()));
    }
}

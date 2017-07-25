package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.SettingChild;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyrv.aboutbabyf.AboutBabyFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutBabyHolder extends ViewHolder {

    @BindView(R.id.about_child_text_view)
    TextView childsName;

    private Context context;
    private SettingChild child;

    public AboutBabyHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new AddFragmentEvent(AboutBabyFragment.newInstance(child.getId())));
            }
        });
    }


    public void setData(SettingChild child) {
        this.child = child;
        childsName.setText(context.getString(R.string.about_kid, child.getName()));

    }


}
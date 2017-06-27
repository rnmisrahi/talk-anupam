package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager.advicerv;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdviceHolder extends ViewHolder {

    @BindView(R.id.num_of_advice)
    TextView numOfAdvice;

    @BindView(R.id.advice)
    TextView adviceRow;

    public AdviceHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setData(String advice, int position) {

        numOfAdvice.setText(String.valueOf(position));
        adviceRow.setText(advice);
    }


}
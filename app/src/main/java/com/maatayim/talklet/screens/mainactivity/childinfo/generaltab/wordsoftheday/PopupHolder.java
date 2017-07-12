package com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.wordsoftheday;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PopupHolder extends ViewHolder {

    @BindView(R.id.row_item)
    TextView rowItem;

    public PopupHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setData(String item){
        rowItem.setText(item);
    }
    
    
    
}
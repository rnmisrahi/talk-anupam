package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoriteWordsHolder extends ViewHolder {

    @BindView(R.id.word_col1)
    TextView word1;

    @BindView(R.id.word_col2)
    TextView word2;

    @BindView(R.id.word_col3)
    TextView word3;

    @BindView(R.id.word_col4)
    TextView word4;


    public FavoriteWordsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setData(FourWordsObj item) {
        String str1 = item.getWord1();
        String str2 = item.getWord2();
        String str3 = item.getWord3();
        String str4 = item.getWord4();

        if (str1 != null){
            word1.setText(str1);
        }

        if (str2 != null){
            word2.setText(str2);
        }

        if (str3 != null){
            word3.setText(str3);
        }

        if (str4 != null){
            word4.setText(str4);
        }
    }


}
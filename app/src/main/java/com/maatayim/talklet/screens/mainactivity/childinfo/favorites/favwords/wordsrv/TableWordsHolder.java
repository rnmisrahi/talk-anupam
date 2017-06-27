package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.maatayim.talklet.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TableWordsHolder extends ViewHolder {

    @BindView(R.id.new_words_section_title)
    TextView word;

    @BindView(R.id.word_frequency)
    TextView frequency;

    @BindView(R.id.new_word_age)
    TextView age;

    private Context context;


    public TableWordsHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }


    public void setData(SpecialWords item, int position) {
        word.setText(item.getWord());

        frequency.setText(String.valueOf(item.getFrequency()));
        age.setText(context.getString(R.string.age_value, item.getAge()));

        if (position%2 == 0){
            itemView.setBackgroundColor(context.getResources().getColor(R.color.row_color));
        }else{
            itemView.setBackgroundColor(context.getResources().getColor(R.color.primary_background_color));
        }

    }


}
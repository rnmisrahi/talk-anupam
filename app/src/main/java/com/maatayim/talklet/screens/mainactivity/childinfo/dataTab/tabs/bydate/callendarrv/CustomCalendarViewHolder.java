package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.DateObj;
import com.maatayim.talklet.utils.Utils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomCalendarViewHolder extends ViewHolder {

    private int DELTA = 30;

    @BindView(R.id.words_progress_bar)
    ProgressBar wordsProgressBar;

    @BindView(R.id.date_string)
    TextView dateStr;

    @BindView(R.id.bg_view)
    View bgView;

    private final Context context;
    private final View itemView;

    public CustomCalendarViewHolder(final View itemView) {
        super(itemView);
        context = itemView.getContext();
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);

//        itemView.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//
//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
////        int height = displayMetrics.heightPixels;
//                int screenMiddle = displayMetrics.widthPixels/2;
//
//
//                int[] location = new int[2];
//                itemView.getLocationOnScreen(location);
//                int x = location[0];
//
//                if((screenMiddle-x <= DELTA) ||(screenMiddle-x >= -DELTA)){
//                    wordsProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_vertical_accent));
//                }else{
//                    wordsProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_vertical));
//                }
//                return false;
//            }
//        });



    }


    public void setData(CalendarWordsObj item, boolean isVisibleProgressBar) {
        if (isVisibleProgressBar){
            wordsProgressBar.setVisibility(View.VISIBLE);
        }else{
            wordsProgressBar.setVisibility(View.GONE);
        }

        if (item.getTotalWords()!=-1){
//            bgView.setBackgroundColor(context.getResources().getColor(R.color.primary_background_color));
            wordsProgressBar.setMax(item.getTotalWords());
//            String date = Utils.getSpecificFormattedDate(item.getDates(), "d/M");

            if(item.isMiddle()){
                wordsProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_vertical_accent));
                ViewCompat.setBackground(dateStr, context.getResources().getDrawable(R.drawable.calendar_bg));
                dateStr.setTypeface(null, Typeface.BOLD);
//                dateStr.setText(Utils.setTextBold(date));


            }else{
                wordsProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_vertical));
                dateStr.setBackgroundColor(context.getResources().getColor(R.color.primary_background_color));
//                dateStr.setText(date);
            }


            wordsProgressBar.setProgress(item.getWordsCount());
            String datte = Utils.getSpecificFormattedDate(item.getDate(), "d/M");
            dateStr.setText(datte);

        }else{
            wordsProgressBar.setMax(0);
            wordsProgressBar.setProgress(0);
//            bgView.setBackgroundColor(context.getResources().getColor(R.color.white_bg));
            dateStr.setBackgroundColor(context.getResources().getColor(R.color.primary_background_color));
            dateStr.setText("    ");
        }

    }




}
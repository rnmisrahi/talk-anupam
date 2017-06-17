package com.maatayim.talklet.screens.mainactivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maatayim.talklet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 6/7/2017
 */

public class CustomProgressBar extends ConstraintLayout {

    @BindView(R.id.words_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.start_bar_value)
    TextView startBarValue;

    @BindView(R.id.end_bar_value)
    TextView endBarValue;

    String startVal, endVal;

    public CustomProgressBar(Context context) {
        super(context);
        init(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        init(context, attrs);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        handleAttrs(context, attrs);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_progressbar, this, true);
        ButterKnife.bind(this, view);

        if (!isInEditMode()) {

        }

    }



    private void handleAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomProgressBar,
                0, 0);

        try {
            startVal = typedArray.getString(R.styleable.CustomProgressBar_startVal);
            endVal = typedArray.getString(R.styleable.CustomProgressBar_endVal);

            startBarValue.setText(startVal);
            endBarValue.setText(endVal);

        } finally {
            typedArray.recycle();
        }
    }

    public void initProgressBar(int saidWords, int totalWords, String startValue){

        progressBar.setProgress(saidWords);
        startBarValue.setText(startValue);
        endBarValue.setText(String.valueOf(saidWords));
    }

    public void changeProgressBarLength(int length){

    }
}

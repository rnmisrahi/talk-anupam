package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.maatayim.talklet.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.R.attr.orientation;

/**
 * Created by Sophie on 8/29/2017
 */

public class CustomRuler extends ConstraintLayout {

    public static final int DEFAULT_VAL = 0;
    @BindView(R.id.highest_val)
    TextView highestVal;

    @BindView(R.id.half_val)
    TextView halfVal;


    public CustomRuler(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRuler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }



    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomRuler,
                0, 0);

        try {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_ruler, this, true);
            ButterKnife.bind(this, view);
            initRuler(DEFAULT_VAL);




        } finally {
            typedArray.recycle();
        }
    }


    public void initRuler(int maxWords){

        highestVal.setText(String.valueOf(maxWords));
        halfVal.setText(String.valueOf(maxWords/2));


    }



}

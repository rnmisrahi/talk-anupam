package com.maatayim.talklet.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.maatayim.talklet.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sophie on 5/22/2017.
 */

public class Utils {

    private static final java.lang.String defaultDateFormat = "MMM dd, yyyy";
    private static final java.lang.String shortDateFormat = ", d MMM";
    private static final java.lang.String defaultTimeFormat = "%02d:%02d:%02d";
    private static int todaysDateStr;


    public static String getFormattedDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(defaultDateFormat, Locale.getDefault());
        return sf.format(date);
    }

    public static String getSpecificFormattedDate(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format, Locale.getDefault());
        return sf.format(date);
    }

    public static String getDurationRecordStr(long duration) {
        return String.format(Locale.ENGLISH, defaultTimeFormat,
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }


    public static int[] getResourceIdsList(Context context, int arrayRes){
        TypedArray ta = context.getResources().obtainTypedArray(arrayRes);
        int[] resources = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            resources[i] = ta.getResourceId(i, 0);
        }
        ta.recycle();
        return resources;
    }

    public static int getStringWidth(TextView textView) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(textView.getText().toString(), 0, textView.getText().length(), bounds);
        int height = bounds.height();
        int width = bounds.width();
        return width;
    }

    public static String getTodaysDateStr() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        String weekDay = dayFormat.format(calendar.getTime());
        String dateStr = getSpecificFormattedDate(calendar.getTime(), shortDateFormat);
        return weekDay+dateStr;
    }


    public static SpannableStringBuilder setTextBold(String txtStr){
        SpannableStringBuilder txt = new SpannableStringBuilder(txtStr);
        txt.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                0, txt.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return txt;

    }
}

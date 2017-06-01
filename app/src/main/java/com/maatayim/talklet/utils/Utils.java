package com.maatayim.talklet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sophie on 5/22/2017.
 */

public class Utils {

    private static final java.lang.String defaultFormat = "MMM dd, yyyy";



    public static String getFormattedDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(defaultFormat, Locale.getDefault());
        return sf.format(date);
    }
}

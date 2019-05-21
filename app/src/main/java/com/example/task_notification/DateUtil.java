package com.example.task_notification;

/**
 * Author  : duyng
 * since   : 10/20/2016
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final String TAG = DateUtil.class.getSimpleName();

    private static final SimpleDateFormat DAY_MONTH_YEAR_FMT = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    private static final SimpleDateFormat HOUR_MINUTE_SECOND_FMT = new SimpleDateFormat("HH:mm:ss", Locale.US);

    private static final SimpleDateFormat HOUR_MINUTE_FMT = new SimpleDateFormat("HH:mm", Locale.US);

    private static final SimpleDateFormat YEAR_MONTH_DAY_HOUR_MINUTE_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    private static final SimpleDateFormat YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    private static final SimpleDateFormat HOUR_MINUTE_SECOND_YEAR_MONTH_DAY_FMT = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd", Locale.US);

    private static final SimpleDateFormat DATE_MONTH_YEAR_WITH_SLASH_FMT = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    private static final SimpleDateFormat DAY_MONTH_YEAR_TIME_FMT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);

    private static final SimpleDateFormat HOUR_MINUTE_WITH_MARKER = new SimpleDateFormat("h:mm a", Locale.US);

    private static final SimpleDateFormat DAY_MONTH_YEAR_TIME_UTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    private static final int SECOND = 1000;

    private static final int MINUTE = 60 * SECOND;

    private static final int HOUR = 60 * MINUTE;

    private static final int DAY = 24 * HOUR;

    private static final long DAY_TO_MILLIS = TimeUnit.DAYS.toMillis(1);

    private static final long HOUR_TO_MILLIS = TimeUnit.HOURS.toMillis(1);

    private static final long MINUTE_TO_MILLIS = TimeUnit.MINUTES.toMillis(1);

    public static final long SECOND_TO_MILLIS = TimeUnit.SECONDS.toMillis(1);


    public static String getTimeAgo(long time, Context ctx) {
        return getTimeAgo(time, ctx, ctx.getString(R.string.text_time_one_minute_ago));
    }

    public static String getTimeAgo(long time, Context ctx, String moment) {
        // TODO: use DateUtils methods instead
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return moment;
        }

        final long diff = now - time;

        if (diff < MINUTE) {
            return moment;
        } else if (diff < 2 * MINUTE) {
            return ctx.getString(R.string.text_time_one_minute_ago);
        } else if (diff < 50 * MINUTE) {
            return String.format(Locale.US, "%d %s", diff / MINUTE, ctx.getString(R.string.text_time_minute_ago));
        } else if (diff < 90 * MINUTE) {
            return ctx.getString(R.string.text_time_one_hour_ago);
        } else if (diff < 24 * HOUR) {
            return String.format(Locale.US, "%d %s", diff / HOUR, ctx.getString(R.string.text_time_hour_ago));
        } else if (diff < 48 * HOUR) {
            return ctx.getString(R.string.text_time_one_day_ago);
        } else {
            return String.format(Locale.US, "%d %s", diff / DAY, ctx.getString(R.string.text_time_day_ago));
        }
    }
}

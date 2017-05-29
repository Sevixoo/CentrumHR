package com.centrumhr.domain.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Seweryn on 28.05.2017.
 */
public class DateUtils {

    public static Date create(String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd.MM.yyyy"  , Locale.getDefault() );
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date dateMinusDays(Date date, int count){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-count);
        return calendar.getTime();
    }

}

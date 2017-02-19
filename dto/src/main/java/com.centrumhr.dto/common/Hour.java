package com.centrumhr.dto.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Seweryn on 16.10.2016.
 */
public class Hour extends Date {

    public static Hour create(Date date){
        if(date==null)return null;
        return new Hour(date);
    }

    /**
    * time - HH:mm
    */
    public Hour( String time ) {
        DateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        try {
            Date date = format.parse(time);
            this.setTime(date.getTime());
        }catch ( Exception ex ){
            throw new IllegalArgumentException();
        }
    }

    private Hour( Date date ) {
        setTime(date.getTime());
    }

    public String format(){
        DateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        return format.format(this);
    }

    public static int durationHours( Hour start , Hour end ){
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return Math.abs ((int) (start.getTime() - end.getTime()) / MILLI_TO_HOUR );
    }

    public static int duration( Hour start , Hour end ){
        final int MILLI_TO_MIN = 1000 * 60 ;
        return Math.abs ((int) (start.getTime() - end.getTime()) / MILLI_TO_MIN);
    }

}

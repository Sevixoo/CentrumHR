package com.centrumhr.data.model.attendance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Seweryn on 16.10.2016.
 */
public class Hour extends Date {

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

    public String format(){
        DateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        return format.format(this);
    }

}

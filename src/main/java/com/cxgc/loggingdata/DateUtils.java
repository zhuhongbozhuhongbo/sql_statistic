package com.cxgc.loggingdata;

import java.text.SimpleDateFormat;

public class DateUtils {

    public static java.util.Date dateCombination(String date, String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(date + " " + time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * to transform a string to sql.date
     *
     * @param strDate: the date string you wang to transform
     * @return : the date in sql.date
     */
    public static java.sql.Date strToSqlDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    /**
     * to transform a string to sql.time
     *
     * @param strTime: the time string you wang to transform
     * @return : the time in sql.time
     */
    public static java.sql.Time strToSqlTime(String strTime) {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        java.util.Date d = null;
        try {
            /*strTime="24:00:00";*/

            d = format.parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time;

    }
}

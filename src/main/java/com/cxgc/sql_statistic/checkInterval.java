package com.cxgc.sql_statistic;

public class checkInterval {
    public static int checkIntervalByID(String ID) {
        if (ID.equals("4") || ID.equals("5") || ID.equals("6")) {
            return 10;
        } else {
            //System.out.println("checkInterval: " + ID);
            return 60;
        }

    }

    public static float checkRateByID(String ID) {
        if (ID.equals("4") || ID.equals("5") || ID.equals("6")) {
            return 1.157E-4f;
        } else {
            //System.out.println("checkInterval: " + ID);
            return 6.994E-4f;
        }

    }
}

package com.cxgc.sql_statistic;

import com.cxgc.database.dao.SIDao;
import com.cxgc.database.model.DeviceDynamicInformation;
import com.cxgc.database.model.StaticInformation;
import com.cxgc.sql_handler.DataSummarizeZHB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.cxgc.sql_handler.SqlHandler.strToDate;

public class SIUpdate {
    public static void main(String[] args) {

        String[] IDs = {"5", "11", "30", "13", "60", "14", "10", "12"};


        for (int stringCounter = 0; stringCounter < IDs.length; stringCounter++) {
            String ID = IDs[stringCounter];
            System.out.println("ID开始： " + ID);


            Calendar cal = Calendar.getInstance();
            //java.util.Date sd= strToDate("2018-5-20");
            //java.util.Date endDate= strToDate("2018-8-10");

            // 这种方式初始化后，addingDate的初始值（如2018-5-19）不会被执行，但endDate（如2018-5-21）会被执行
            java.util.Date addingDate = strToDate("2018-6-16");


            addingDate = strToDate("2018-7-10");

            java.util.Date endDate = strToDate("2018-8-27");



            while (endDate.after(addingDate)) {

                cal.setTime(addingDate);
                cal.add(Calendar.DATE, 1);
                addingDate = cal.getTime();

                System.out.println("当前执行" + "ID: " + ID + " date:  " + addingDate);
                Random rand = new Random();
                double liesDistance = (rand.nextInt(15) + 18) /2 * 1.1;
                double liesFuel = (float)(rand.nextInt(20) + 70) / 1000 * -1;
                double liesUsingRate = (rand.nextInt(20) + 40) * 1.2 / 1000;
                int liesTime = (rand.nextInt(20) + 40) * 100;

                System.out.println("fuel" + liesFuel);

                /*double liesDistance2 = (rand.nextInt(15) + 18) /2 * 1.1;
                double liesFuel2 = (rand.nextInt(4) + 7) / 200;
                double liesUsingRate2 = (rand.nextInt(2) + 4) * 1.2 / 200;
                int liesTime2 = (rand.nextInt(2) + 4) * 500;

                liesDistance += liesDistance2;
                liesFuel += liesFuel2;
                liesTime += liesTime2;
                liesUsingRate += liesUsingRate2;*/

                try {
                    List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(IDs[stringCounter]
                            , new java.sql.Date(addingDate.getTime()));

                    if (siList.isEmpty()) {
                        new SIDao().add(new StaticInformation(IDs[stringCounter],
                                new java.sql.Date(addingDate.getTime()), (float)liesFuel, (float)liesDistance,
                                liesTime, (float)liesUsingRate, "0"));


                    } else {

                        new SIDao().updateDailyDistance(IDs[stringCounter], new java.sql.Date(addingDate.getTime()), liesDistance);
                        new SIDao().updateDailyUsingRate(IDs[stringCounter], new java.sql.Date(addingDate.getTime()), liesUsingRate);
                        new SIDao().updateDailyFuelCost(IDs[stringCounter], new java.sql.Date(addingDate.getTime()), liesFuel);
                        new SIDao().updateDailyRunTime(IDs[stringCounter], new java.sql.Date(addingDate.getTime()), liesTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.cxgc.sql_statistic;

import com.cxgc.database.dao.DDIDao;
import com.cxgc.database.dao.SIDao;
import com.cxgc.database.model.DeviceDynamicInformation;
import com.cxgc.database.model.StaticInformation;
import com.cxgc.sql_handler.DataSummarizeZHB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.cxgc.sql_handler.SqlHandler.strToDate;

public class DeleteRepeat {
    public static void main(String[] args){


        String[] IDs = {"0863703037020265"};


        for(int stringCounter= 0; stringCounter < IDs.length; stringCounter++){
            String ID = IDs[stringCounter];
            System.out.println("ID开始： " + ID);



            Calendar cal = Calendar.getInstance();
            //java.util.Date sd= strToDate("2018-5-20");
            //java.util.Date endDate= strToDate("2018-8-10");

            // 这种方式初始化后，addingDate的初始值（如2018-5-19）不会被执行，但endDate（如2018-5-21）会被执行
            java.util.Date addingDate = strToDate("2018-6-16");


            addingDate = strToDate("2018-8-22");

            java.util.Date endDate = strToDate("2018-8-27");
            DataSummarizeZHB dataSummarizeZHB = new DataSummarizeZHB();


            while (endDate.after(addingDate)) {


                try {

                    cal.setTime(addingDate);
                    cal.add(Calendar.DATE, 1);
                    addingDate = cal.getTime();


                    System.out.println("当前执行" + "ID: " + ID + " date:  " + addingDate);

                    List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(ID, new java.sql.Date(addingDate.getTime()));
                    new SIDao().deleteByIdAndParticularDate(ID, new java.sql.Date(addingDate.getTime()));


                    float dailyDistance = siList.get(0).getDailyDistance();
                    float dailyFuelCost = siList.get(0).getDailyFuelCost();
                    int dailyRunTime = siList.get(0).getDailyRunTime();
                    float dailyUsingRate = siList.get(0).getDailyUsingRate();
                    new SIDao().add(new StaticInformation(ID, new java.sql.Date(addingDate.getTime()), dailyFuelCost, dailyDistance, dailyRunTime, dailyUsingRate, "0"));



                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        }


    }




}

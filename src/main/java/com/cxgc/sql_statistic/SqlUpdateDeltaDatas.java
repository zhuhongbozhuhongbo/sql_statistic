package com.cxgc.sql_statistic;

import com.cxgc.database.dao.DDIDao;
import com.cxgc.database.dao.SIDao;
import com.cxgc.database.model.DeviceDynamicInformation;
import com.cxgc.database.model.StaticInformation;
import com.cxgc.sql_handler.DataMaterialZHB;
import com.cxgc.sql_handler.DataSummarizeZHB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.cxgc.sql_handler.SqlHandler.strToDate;

/*
    更新t_ddi表中delta数据
 */
public class SqlUpdateDeltaDatas {
    public static void main(String[] args){



        //String[] IDs = {"5", "11", "30", "13", "60", "14", "10", "12"};
        String[] IDs = {"0868744030561140"};


        for(int stringCounter= 0; stringCounter < IDs.length; stringCounter++){
            String ID = IDs[stringCounter];
            System.out.println("ID开始： " + ID);



            Calendar cal = Calendar.getInstance();
            //java.util.Date sd= strToDate("2018-5-20");
            //java.util.Date endDate= strToDate("2018-8-10");

            List<DeviceDynamicInformation> listDDI = new ArrayList<DeviceDynamicInformation>();
            // 这种方式初始化后，addingDate的初始值（如2018-5-19）不会被执行，但endDate（如2018-5-21）会被执行
            java.util.Date addingDate = strToDate("2018-6-16");


            addingDate = strToDate("2018-8-22");

            java.util.Date endDate = strToDate("2018-8-27");
            DataSummarizeZHB dataSummarizeZHB = new DataSummarizeZHB();


            //List<DeviceDynamicInformation> toDelete = new ArrayList<DeviceDynamicInformation>();


            //     new DDIDao().deleteOne(new DeviceDynamicInformation(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            //           strToDate("2018-8-9"), ))

            while (endDate.after(addingDate)) {
                float dailyDistance = 0;
                //int usingRate = 0;

                try {

                    cal.setTime(addingDate);
                    cal.add(Calendar.DATE, 1);
                    addingDate = cal.getTime();


                    System.out.println("当前执行" + "ID: " + ID + " date:  " + addingDate);
                    new SIDao().add(new StaticInformation(ID, new java.sql.Date(addingDate.getTime()), 0, 0, 0, 0, "0"));
                    DDIDao ddiDao = new DDIDao();
                    listDDI = ddiDao.findByIdAndParticularDate(ID, new java.sql.Date(addingDate.getTime()));

                    System.out.println("size of ddi list: " + listDDI.size());
                    if (!listDDI.isEmpty()) {
                        //在此处得到了某天的DDI数据

                        for (int i = 0; i < listDDI.size() - 1; i++) {

                            float deltaDistance = Miller_projection.getDeltaDistance(listDDI.get(i).getGPSInformation(),
                                    listDDI.get(i + 1).getGPSInformation());
                            //deltaDistance = listDDI.get(i).getDeltaDistanceSum();


                            /*
                            此段代码用于更新delta数据（#{deltaDistanceSum}， #{deltaFuel})
                            float deltaFuel = listDDI.get(i + 1).getContainingFuel() - listDDI.get(i).getContainingFuel();


                            listDDI.get(i + 1).setDeltaDistanceSum(deltaDistance);
                            listDDI.get(i + 1).setDeltaFuel(deltaFuel);
                            ddiDao.updateDeltaDatas(listDDI.get(i + 1));*/

                            //计算总距离
                            dailyDistance += deltaDistance;


                            //Thread.sleep(3);

                        }


                        System.out.println("deltaDistance:" + dailyDistance);
                        new SIDao().updateDailyDistance(ID, new java.sql.Date(addingDate.getTime()), dailyDistance / 10000);

                    } else {
                        System.out.println("当前执行日期无动态数据（t_ddi)： " + " ID: " + ID + " " + addingDate);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        }



        SqlStatistic.main(args);

    }




}


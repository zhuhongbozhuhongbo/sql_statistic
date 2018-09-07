package com.cxgc.sql_handler;

import com.cxgc.database.dao.DDIDao;
import com.cxgc.database.dao.SIDao;
import com.cxgc.database.model.DeviceDynamicInformation;
import com.cxgc.database.model.StaticInformation;

import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
    去除t_ddi表中一定时间端内的重复数据
 */
public class SqlHandler {
    public static void main(String[] args) {

        String[] IDs = {"5", "11", "10", "4", "30", "31", "34", "33", "6"};
        for (int stringCounter = 0; stringCounter < IDs.length; stringCounter++) {
            String ID = IDs[stringCounter];
            System.out.println("ID开始： " + ID);

            List<DeviceDynamicInformation> listDDI = new ArrayList<DeviceDynamicInformation>();
            List<DeviceDynamicInformation> listToHandle = new ArrayList<DeviceDynamicInformation>();



            Calendar cal = Calendar.getInstance();
            //java.util.Date sd= strToDate("2018-5-20");
            //java.util.Date endDate= strToDate("2018-8-10");


            // 这种方式初始化后，addingDate的初始值（如2018-5-19）不会被执行，但endDate（如2018-5-21）会被执行
            java.util.Date addingDate = strToDate("2018-5-19");
            java.util.Date endDate = strToDate("2018-8-9");

            //List<DeviceDynamicInformation> toDelete = new ArrayList<DeviceDynamicInformation>();


  /*      new DDIDao().deleteOne(new DeviceDynamicInformation(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                strToDate("2018-8-9"), ))*/

            while (endDate.after(addingDate))
                try {

                    //listSI = new SIDao().findById(ID);

/*
            listDDI = new DDIDao().findByIdAndDate(ID,
                    new java.sql.Date(strToDate("2018-7-20").getTime()),
                    new java.sql.Date(strToDate("2018-7-20").getTime()) );*/
                    cal.setTime(addingDate);
                    cal.add(Calendar.DATE, 1);
                    addingDate = cal.getTime();


                    System.out.println("当前执行 date:  " + addingDate);
                    DDIDao ddiDao = new DDIDao();
                    listDDI = ddiDao.findByIdAndParticularDate(ID, new java.sql.Date(addingDate.getTime()));
                    System.out.println("size of ddi list: " + listDDI.size());
                    if (!listDDI.isEmpty()) {
                        for (int i = 0; i < listDDI.size() - 1; i++) {


                            /**
                             * 注意！！！！！这里要用.equals而不能用 ==  ，== 这种方式判断不了日期相等、字符串相等
                             */
                            if ((listDDI.get(i).getIotDeviceId().equals(listDDI.get(i + 1).getIotDeviceId()))
                                    && (listDDI.get(i).getInformationDate().equals(listDDI.get(i + 1).getInformationDate()))
                                    && (listDDI.get(i).getInformationTime().equals(listDDI.get(i + 1).getInformationTime()))
                                    ) {

                                listToHandle.add(listDDI.get(i));
                                System.out.println("出现重复，添加：" + listDDI.get(i).getIotDeviceId() + " " +
                                        listDDI.get(i).getInformationDate() + " " +
                                        listDDI.get(i).getInformationTime());
                                /*
                                 */
                            }
                        }
                    } else {
                        System.out.println("当前执行日期为空： " + addingDate);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            System.out.println("listToHandle size: " + listToHandle.size());
            //先进行去除list中的重复
            listToHandle = removeDuplicate(listToHandle);
            System.out.println("listToHandle size: " + listToHandle.size());

            DDIDao ddiDao = new DDIDao();
            for (int i = 0; i < listToHandle.size(); i++) {
                try {
                    ddiDao.deleteOne(listToHandle.get(i));
                    ddiDao.add(listToHandle.get(i));
                    Thread.sleep(50);//这个延时如过不加，会报错：连接不上，估计是与数据库连接都被占用了

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("出现重复，删除并增加：" + listToHandle.get(i).getIotDeviceId() + " " +
                        listToHandle.get(i).getInformationDate() + " " +
                        listToHandle.get(i).getInformationTime());
            }

/*        for(int i = 0; i < listToHandle.size(); i++){
            try {
                ddiDao.add(listToHandle.get(i));
                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("重新添加：" + listToHandle.get(i).getIotDeviceId() + " " +
                    listToHandle.get(i).getInformationDate() + " " +
                    listToHandle.get(i).getInformationTime());
        }*/




        /*
            以下代码，执行统计功能：
         */


        }
    }

    public static java.sql.Date strToSqlDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        java.util.Date strtodate = formatter.parse(strDate, pos);
        java.sql.Date sd = new java.sql.Date(strtodate.getTime());
        System.out.println(" str to sql date: " + sd);
        return sd;
    }

    public static java.sql.Time strToSqlTime(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        java.util.Date strtotime = formatter.parse(strDate, pos);
        java.sql.Time st = new java.sql.Time(strtotime.getTime());
        System.out.println(" str to sql time: " + st);
        return st;
    }

    public static java.util.Date strToDate(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        java.util.Date strtodate =formatter.parse(strDate, pos);
        return strtodate;
    }


    //去除ArrayList<DeviceDynamicInformation>中的重复元素
    public static List<DeviceDynamicInformation> removeDuplicate(List list) {
        for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {   //从左向右循环
            for ( int j = list.size() - 1 ; j > i; j -- ) {  //从右往左内循环

              /*  if (list.get(j).equals(list.get(i))) {
                    list.remove(j);                              //相等则移除
                }*/


                if((  ( (DeviceDynamicInformation)list.get(j) ).getIotDeviceId().equals(((DeviceDynamicInformation)list.get(i)).getIotDeviceId()) )
                        && ( ((DeviceDynamicInformation)list.get(j)).getInformationDate().equals(((DeviceDynamicInformation)list.get(i)).getInformationDate()) )
                        && ( ((DeviceDynamicInformation)list.get(j)).getInformationTime().equals(((DeviceDynamicInformation)list.get(i)).getInformationTime()) )
                        ){
                    list.remove(j);


                }
            }
        }
        //System.out.println(list);
        return list;
    }
}

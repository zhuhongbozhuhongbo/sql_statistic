package com.cxgc.sql_handler;

import com.cxgc.database.model.DeviceDynamicInformation;

import java.util.ArrayList;
import java.util.List;

import static com.cxgc.sql_handler.SqlHandler.strToSqlDate;
import static com.cxgc.sql_handler.SqlHandler.strToSqlTime;

public class SqlHandlerTest3 {

    public static void main(String[] args){
        List<DeviceDynamicInformation> listDDI = new ArrayList<DeviceDynamicInformation>();

        java.sql.Time st = strToSqlTime("00:00:00");
        java.sql.Date sd = strToSqlDate("2018-8-9");

        DeviceDynamicInformation deviceDynamicInformation0 =
                new DeviceDynamicInformation(0, 0, 0, 0,
                        0, 0, 0,
                        0, 0, 0,
                        0, 0, sd,
                        st, 0, true,
                        "6", "aaa", 0,
                        0
                );

        DeviceDynamicInformation deviceDynamicInformation1 =
                new DeviceDynamicInformation(0, 0, 0, 0,
                        0, 0, 0,
                        0, 0, 0,
                        0, 0, sd,
                        st, 0, true,
                        "6", "aaa", 0,
                        0
                );

        listDDI.add(deviceDynamicInformation0);
        listDDI.add(deviceDynamicInformation1);
        System.out.println(listDDI.size());
        removeDuplicate(listDDI);
        System.out.println(listDDI.size());
    }

    public static void removeDuplicate(List list) {
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
        System.out.println(list);
    }
}

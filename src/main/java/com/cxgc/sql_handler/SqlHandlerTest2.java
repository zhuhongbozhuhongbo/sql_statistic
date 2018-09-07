package com.cxgc.sql_handler;

import com.cxgc.database.model.DeviceDynamicInformation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import static com.cxgc.sql_handler.SqlHandler.strToSqlDate;
import static com.cxgc.sql_handler.SqlHandler.strToSqlTime;

/*
    像这种想要删除值相同的DeviceDynamicInformation的对象，这种转换成linkedHashSet 或 HashSet的方法无效
 */
public class SqlHandlerTest2 {
    public static void main(String[] args){
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

        ArrayList<DeviceDynamicInformation> deviceDynamicInformations = new ArrayList<DeviceDynamicInformation>();
        deviceDynamicInformations.add(deviceDynamicInformation0);
        deviceDynamicInformations.add(deviceDynamicInformation1);
        System.out.println(deviceDynamicInformations.size());

        LinkedHashSet<DeviceDynamicInformation> linkedHashSet = new LinkedHashSet<DeviceDynamicInformation>(deviceDynamicInformations);

        ArrayList<DeviceDynamicInformation> deviceDynamicInformations1 = new ArrayList<DeviceDynamicInformation>(linkedHashSet);

        System.out.println(deviceDynamicInformations1.size());
    }
}

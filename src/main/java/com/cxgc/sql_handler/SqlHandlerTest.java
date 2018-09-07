package com.cxgc.sql_handler;

import com.cxgc.database.dao.DDIDao;
import com.cxgc.database.model.DeviceDynamicInformation;

import static com.cxgc.sql_handler.SqlHandler.strToSqlDate;
import static com.cxgc.sql_handler.SqlHandler.strToSqlTime;

public class SqlHandlerTest {
    public static void main(String[] args){
        java.sql.Time st = strToSqlTime("00:00:00");
        java.sql.Date sd = strToSqlDate("2018-8-9");

        try {
            new DDIDao().deleteOne(new DeviceDynamicInformation(0, 0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, sd,
                    st, 0, true,
                    "6", "aaa", 0,
                    0
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

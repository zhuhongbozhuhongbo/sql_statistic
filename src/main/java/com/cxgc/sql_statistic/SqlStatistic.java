package com.cxgc.sql_statistic;

import com.cxgc.database.dao.DDIDao;
import com.cxgc.database.dao.IIDao;
import com.cxgc.database.model.DeviceDynamicInformation;
import com.cxgc.database.model.InspectorInformation;
import com.cxgc.sql_handler.DataMaterialZHB;
import com.cxgc.sql_handler.DataSummarizeZHB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static com.cxgc.sql_handler.SqlHandler.strToDate;

/*
    根据t_ddi表中数据进行统计
 */
public class SqlStatistic {

    public static void main(String[] args) {

        //String[] IDs = {"5", "11", "30", "13", "60", "14", "10", "12"};\
        String[] IDs = {"0868744030561140"};
        for(int stringCounter= 0; stringCounter < IDs.length; stringCounter++){
            String ID = IDs[stringCounter];
            System.out.println("ID开始： " + ID);


            Calendar cal = Calendar.getInstance();
            //java.util.Date sd= strToDate("2018-5-20");
            //java.util.Date endDate= strToDate("2018-8-10");

            List<DeviceDynamicInformation> listDDI = new ArrayList<DeviceDynamicInformation>();
            // 这种方式初始化后，addingDate的初始值（如2018-5-19）不会被执行，但endDate（如2018-5-21）会被执行
            java.util.Date addingDate = strToDate("2018-8-22");
            java.util.Date endDate = strToDate("2018-8-27");
            DataSummarizeZHB dataSummarizeZHB = new DataSummarizeZHB();


            //List<DeviceDynamicInformation> toDelete = new ArrayList<DeviceDynamicInformation>();


  //     new DDIDao().deleteOne(new DeviceDynamicInformation(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    //           strToDate("2018-8-9"), ))

            while (endDate.after(addingDate)) {
                try {


                    cal.setTime(addingDate);
                    cal.add(Calendar.DATE, 1);
                    addingDate = cal.getTime();


                    System.out.println("当前执行" + "ID: " + ID + " date:  " + addingDate);
                    DDIDao ddiDao = new DDIDao();
                    listDDI = ddiDao.findByIdAndParticularDate(ID, new java.sql.Date(addingDate.getTime()));

                    System.out.println("size of ddi list: " + listDDI.size());
                    if (!listDDI.isEmpty()) {
                        //在此处得到了某天的DDI数据

                        for (int i = 0; i < listDDI.size(); i++) {
                            DataMaterialZHB dataMaterialZHB = new DataMaterialZHB(listDDI.get(i).getInformationDate(),
                                    listDDI.get(i).getInformationTime(), listDDI.get(i).getIotDeviceId(), listDDI.get(i).isWorkingFlag(),
                                    listDDI.get(i).getDeltaDistanceSum(), listDDI.get(i).getDeltaFuel(), listDDI.get(i).getGPSInformation());

                            dataSummarizeZHB.updateSI(dataMaterialZHB);

                            //判断是否需要向t_ii表中添加传感器异常信息
                            //judgingFuelSensor0(listDDI.get(i).getContainingFuel(), dataMaterialZHB);

                            Thread.sleep(15);

                        }

                    } else {
                        System.out.println("当前执行日期无动态数据（t_ddi)： " + " ID: " + ID + " " + addingDate);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }



    private static int judgingFuelSensor0(float judgingFuel, DataMaterialZHB dataMaterialZHB) {


        if (judgingFuel == 0.0f) {
            try {
                //System.out.println(dataMaterialZHB.getIotDeviceId() + " " + dataMaterialZHB.getDate());
                IIDao iiDao = new IIDao();
                List<InspectorInformation> querylist = iiDao.findByIdAndParticularDate(dataMaterialZHB.getIotDeviceId(), dataMaterialZHB.getDate());

                //System.out.println("size:    " + querylist.size());
                boolean judgeExistence = false;//false为当天II表中不存在该数据
                if(!querylist.isEmpty()){
                    for(int i = 0; i < querylist.size(); i++){
                        if(querylist.get(i).getWarningType().equals("1001")){
                            judgeExistence = true;
                        }
                    }
                }

                if(judgeExistence == false){
//                    new IIDao().add(new InspectorInformation(dataMaterialZHB.getIotDeviceId(), dataMaterialZHB.getDate(),
//                            dataMaterialZHB.getTime(), dataMaterialZHB.getGPSInformation(), "0"));//1001代表油量为0的异常
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;

        } else {
            return 0;
        }

    }

}


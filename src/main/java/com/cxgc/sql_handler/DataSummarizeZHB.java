package com.cxgc.sql_handler;

import com.cxgc.database.dao.IIDao;
import com.cxgc.database.dao.SIDao;
import com.cxgc.database.model.InspectorInformation;
import com.cxgc.database.model.StaticInformation;
import com.cxgc.sql_statistic.checkInterval;
import com.cxgc.sql_statistic.iotConstant;
//import com.cxgc.utils.checkInterval;

import java.util.Date;
import java.util.List;

public class DataSummarizeZHB {
    private float increasFuel = 0;
    private float decreasFuel = 0;
    private int decreasCounter = 0;
    private int increasCounter = 0;

    //需要执行：
    public void updateSI(DataMaterialZHB dataMaterialZHB) {
        updateSIDisUseTime(dataMaterialZHB);
        updateSIDeltaFuel(dataMaterialZHB);
    }


        //负责更新SI表中距离、利用率、工作时间字段


    private void updateSIDisUseTime(DataMaterialZHB dataMaterialZHB) {
        //System.out.println("DatasummarizeZHB 1 " + "updatesql");
        float queryDistance = 0.0f;
        float queryUsingRate = 0.0f;
        int queryTimeSecond = 0;
        float resultUsingRate = 0.0f;

        try {
            List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(dataMaterialZHB.getIotDeviceId()
                    , new java.sql.Date(dataMaterialZHB.getDate().getTime()));

            if (siList.isEmpty()) {
                new SIDao().add(new StaticInformation(dataMaterialZHB.getIotDeviceId(),
                        new java.sql.Date(dataMaterialZHB.getDate().getTime()), 0, 0,
                        0, 0, "0"));


                queryDistance = 0.0f;
                queryUsingRate = 0.0f;
                queryTimeSecond = 0;
            } else {

                queryDistance = siList.get(0).getDailyDistance();
                queryTimeSecond = siList.get(0).getDailyRunTime();
                queryUsingRate = siList.get(0).getDailyUsingRate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dataMaterialZHB.getWorkingFlag()) {
            resultUsingRate = checkInterval.checkRateByID(dataMaterialZHB.getIotDeviceId());
        } else {
            resultUsingRate = 0.0f;
        }


        //注意!如果当天无任何该ID的t_si数据（每天第一次执行时），则第一次插入t_si就会导致更新一次dailyUsingTime

        try {
            new SIDao().updateDailyUsingRate(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()), resultUsingRate + queryUsingRate);
            new SIDao().updateDailyDistance(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()), dataMaterialZHB.getDeltaDistance() + queryDistance);



            //如果workingFlag不为true，则无须更新
            if (dataMaterialZHB.getWorkingFlag()) {
                new SIDao().updateDailyRunTime(dataMaterialZHB.getIotDeviceId(),
                        new java.sql.Date(dataMaterialZHB.getDate().getTime())
                        //4 5 6的话，workingFlag为true代表着10s，其他的代表为60s
                        , queryTimeSecond + checkInterval.checkIntervalByID(dataMaterialZHB.getIotDeviceId()));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        //负责更新SI表中每日油耗字段


    private void updateSIDeltaFuel(DataMaterialZHB dataMaterialZHB) {
        if (dataMaterialZHB.getDeltaFuel() > 0) {

            increasFuel += dataMaterialZHB.getDeltaFuel();
            increasCounter++;

            //重置油量减少计数器
            decreasCounter = 0;
            decreasFuel = 0.0f;


        } else if (dataMaterialZHB.getDeltaFuel() < 0) {

            decreasFuel += dataMaterialZHB.getDeltaFuel();
            decreasCounter++;
            updateSqlSIDeltaFuel(dataMaterialZHB, dataMaterialZHB.getDeltaFuel());

            //可能为加油或抖动
            if (increasCounter > 0) {
                //在加油
                if (increasCounter > 2) {//
                    increasCounter = 0;
                    increasFuel = 0;
                    //System.out.println("加油");
                } else {//抖动
                    increasCounter = 0;
                    updateSqlSIDeltaFuel(dataMaterialZHB, increasFuel);
                    increasFuel = 0;
                }
            }

        } else if (dataMaterialZHB.getDeltaFuel() == 0) {
            //无需执行存储

            //重置油量减少计数器
            decreasCounter = 0;
            decreasFuel = 0.0f;

            //可能为加油或抖动
            if (increasCounter > 0) {
                //在加油
                if (increasCounter > 2) {
                    increasCounter = 0;
                    increasFuel = 0;
                    //System.out.println("加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油");
                } else {//抖动
                    increasCounter = 0;
                    updateSqlSIDeltaFuel(dataMaterialZHB, increasFuel);
                    increasFuel = 0;
                }
            } else {
                //System.out.println("DatasummarizeZHB 0.5 " + "油量未变化");
            }
        }

        if (decreasCounter > iotConstant.fuelAlarmConstant) {
            //发现偷油后立即向t_ii表中插入报警信息
            try {
                new IIDao().add(new InspectorInformation(dataMaterialZHB.getIotDeviceId(), dataMaterialZHB.getDate(),
                        dataMaterialZHB.getTime(), dataMaterialZHB.getGPSInformation(), "1"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("DataSummarizeZHB:" + "偷油！" + "ID" + dataMaterialZHB.getIotDeviceId() + "时间：" + dataMaterialZHB.getDate() + "发现日期：" + new Date());
        }
    }

    private void updateSqlSIDeltaFuel(DataMaterialZHB dataMaterialZHB, float deltaFuel) {
        float queryFuel = 0.0f;
   float queryDistance = 0.0f;
        float queryUsingRate = 0.0f;
        int queryTimeSecond = 0;

        //Time queryFlag = DaoUtil.strToTime("00:00:00");
  float resultUsingRate;



        try {
            List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(dataMaterialZHB.getIotDeviceId()
                    , new java.sql.Date(dataMaterialZHB.getDate().getTime()));

            if (siList.isEmpty()) {
                new SIDao().add(new StaticInformation(dataMaterialZHB.getIotDeviceId(),
                        new java.sql.Date(dataMaterialZHB.getDate().getTime()), 0, 0,
                        0, 0, "0"));

                queryFuel = 0.0f;
            } else {
                queryFuel = siList.get(0).getDailyFuelCost();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new SIDao().updateDailyFuelCost(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()), deltaFuel + queryFuel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

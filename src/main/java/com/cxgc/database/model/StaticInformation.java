package com.cxgc.database.model;

import java.sql.Date;

/**
 * Created by HeRui on 2018/5/21.
 */
public class StaticInformation {

    private String iotDeviceId;
    private Date currentDate;
    private float dailyFuelCost;
    private float dailyDistance;
    //private Time dailyRunTime;
    private int dailyRunTime;
    private float dailyUsingRate;
    private String direction;
    //下位机id，当前日期，每日油耗，每日行程，每日运行时间，本日利用率


    public StaticInformation() {
        super();
    }

    public StaticInformation(String iotDeviceId, Date currentDate, float dailyFuelCost, float dailyDistance, int dailyRunTime, float dailyUsingRate, String direction) {
        this.iotDeviceId = iotDeviceId;
        this.currentDate = currentDate;
        this.dailyFuelCost = dailyFuelCost;
        this.dailyDistance = dailyDistance;
        this.dailyRunTime = dailyRunTime;
        this.dailyUsingRate = dailyUsingRate;
        this.direction = direction;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public float getDailyFuelCost() {
        return dailyFuelCost;
    }

    public void setDailyFuelCost(float dailyFuelCost) {
        this.dailyFuelCost = dailyFuelCost;
    }

    public float getDailyDistance() {
        return dailyDistance;
    }

    public void setDailyDistance(float dailyDistance) {
        this.dailyDistance = dailyDistance;
    }

    public int getDailyRunTime() {
        return dailyRunTime;
    }

    public void setDailyRunTime(int dailyRunTime) {
        this.dailyRunTime = dailyRunTime;
    }

    public float getDailyUsingRate() {
        return dailyUsingRate;
    }

    public void setDailyUsingRate(float dailyUsingRate) {
        this.dailyUsingRate = dailyUsingRate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

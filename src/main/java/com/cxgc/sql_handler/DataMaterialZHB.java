package com.cxgc.sql_handler;

import java.sql.Time;

public class DataMaterialZHB {
    public java.sql.Date getDate() {
        return date;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public boolean getWorkingFlag() {
        return workingFlag;
    }

    public float getDeltaDistance() {
        return deltaDistance;
    }

    public float getDeltaFuel() {
        return deltaFuel;
    }

    public Time getTime() {
        return time;
    }

    private java.sql.Date date;
    private Time time;
    private String iotDeviceId;
    private boolean workingFlag;
    private float deltaDistance;
    private float deltaFuel;
    private String GPSInformation;

    public String getGPSInformation() {
        return GPSInformation;
    }

    public DataMaterialZHB(java.sql.Date date, Time time, String iotDeviceId, boolean workingFlag,
                           float deltaDistance, float deltaFuel, String GPSInformation) {
        this.time = time;
        this.date = date;
        this.iotDeviceId = iotDeviceId;
        this.workingFlag = workingFlag;
        this.deltaDistance = deltaDistance;
        this.deltaFuel = deltaFuel;
        this.GPSInformation = GPSInformation;
    }
}

package com.cxgc.database.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by HeRui on 2018/5/24.
 */
public class InspectorInformation {

    private String iotDeviceId;  //iotID
    private Date informationDate;//记录时间，年月日
    private Time informationTime;//记录时间，时分秒
    private String GPSInformation;
    private String warningType;

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public Date getInformationDate() {
        return informationDate;
    }

    public Time getInformationTime() {
        return informationTime;
    }

    public String getGPSInformation() {
        return GPSInformation;
    }

    public String getWarningType() {
        return warningType;
    }

    public InspectorInformation() {
        super();
    }

    public InspectorInformation(String iotDeviceId, Date informationDate, Time informationTime, String GPSInformation, String warningType) {
        this.iotDeviceId = iotDeviceId;
        this.informationDate = informationDate;
        this.informationTime = informationTime;
        this.GPSInformation = GPSInformation;
        this.warningType = warningType;
    }
}

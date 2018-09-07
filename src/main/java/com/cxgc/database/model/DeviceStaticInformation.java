package com.cxgc.database.model;

import java.sql.Date;

/**
 * Created by HeRui on 2018/3/7.
 * describe device static information
 */
public class DeviceStaticInformation {


    private String SIMId;//sim卡id
    private String iotDeviceId;//IOTid
    private String RFId;//磁条id
    private String projectInformation;// 项目信息
    private String numberPlate; //车牌号
    private String engineNumber; //发动机号
    private Boolean inControl; //设备是否还在中铁管辖下
    private Date startDate;// 进场时间
    private String temp;//临时
    private double fuelTank;
    private String GPSInformation;
    private int rail_id;
    private String deviceNum;
    private String manageNum;
    private String driver;
    private String tel;



    public DeviceStaticInformation() {
        super();
    }

    public DeviceStaticInformation(String SIMId, String iotDeviceId, String RFId, String projectInformation,
                                   String numberPlate, String engineNumber, Boolean inControl, Date startDate,
                                   String temp, double fuelTank, String GPSInformation, int rail_id, String deviceNum,
                                   String manageNum, String driver, String tel) {
        this.SIMId = SIMId;
        this.iotDeviceId = iotDeviceId;
        this.RFId = RFId;
        this.projectInformation = projectInformation;
        this.numberPlate = numberPlate;
        this.engineNumber = engineNumber;
        this.inControl = inControl;
        this.startDate = startDate;
        this.temp = temp;
        this.fuelTank = fuelTank;
        this.GPSInformation = GPSInformation;
        this.rail_id = rail_id;
        this.deviceNum = deviceNum;
        this.manageNum = manageNum;
        this.driver = driver;
        this.tel = tel;
    }

    public String getSIMId() {
        return SIMId;
    }

    public void setSIMId(String SIMId) {
        this.SIMId = SIMId;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public String getRFId() {
        return RFId;
    }

    public void setRFId(String RFId) {
        this.RFId = RFId;
    }

    public String getProjectInformation() {
        return projectInformation;
    }

    public void setProjectInformation(String projectInformation) {
        this.projectInformation = projectInformation;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Boolean getInControl() {
        return inControl;
    }

    public void setInControl(Boolean inControl) {
        this.inControl = inControl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public double getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(double fuelTank) {
        this.fuelTank = fuelTank;
    }

    public String getGPSInformation() {
        return GPSInformation;
    }

    public void setGPSInformation(String GPSInformation) {
        this.GPSInformation = GPSInformation;
    }

    public int getRail_id() {
        return rail_id;
    }

    public void setRail_id(int rail_id) {
        this.rail_id = rail_id;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getManageNum() {
        return manageNum;
    }

    public void setManageNum(String manageNum) {
        this.manageNum = manageNum;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

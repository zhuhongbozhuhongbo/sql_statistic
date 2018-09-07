package com.cxgc.database.model;

import java.sql.Date;
import java.sql.Time;


public class DeviceDynamicInformation {
    private float currentSpeed;//线速度
    private float containingFuel;//剩余燃料
    private float containingElectricity;//剩余电量
    private String GPSInformation;//GPS信息
    private float angleX;//x轴角度
    private float angleY;//y轴角度
    private float angleZ;//z轴角度
    private float angularXSpeed; //x轴角速度
    private float angularYSpeed; //y轴角速度
    private float angularZSpeed;//z轴角速度
    //    private float angularXAcceleration;//x轴角加速度
//    private float angularYAcceleration;//y轴角加速度
//    private float angularZAcceleration;//z轴角加速度
    private float xAcceleration;//x轴加速度
    private float yAcceleration;//y轴加速度
    private float zAcceleration;//z轴加速度
    private Date informationDate;//记录时间，年月日
    private Time informationTime;//记录时间，时分秒
    private int rotationOrientation;//罐车转筒旋转方向，顺时针为正
    private boolean workingFlag;//设备是否工作,工作为正
    private String iotDeviceId;//设备id
    private float deltaDistance;//相对运动距离
    private float deltaFuel;//相对燃料消耗

    public float getDeltaFuel() {
        return deltaFuel;
    }

    public void setDeltaFuel(float deltaFuel) {
        this.deltaFuel = deltaFuel;
    }


    public float getDeltaDistanceSum() {
        return deltaDistance;
    }

    public void setDeltaDistanceSum(float deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public float getAngleX() {
        return angleX;
    }

    public void setAngleX(float angleX) {
        this.angleX = angleX;
    }

    public float getAngleY() {
        return angleY;
    }

    public void setAngleY(float angleY) {
        this.angleY = angleY;
    }

    public float getAngleZ() {
        return angleZ;
    }

    public void setAngleZ(float angleZ) {
        this.angleZ = angleZ;
    }

    public float getAngularXSpeed() {
        return angularXSpeed;
    }

    public void setAngularXSpeed(float angularXSpeed) {
        this.angularXSpeed = angularXSpeed;
    }

    public float getAngularYSpeed() {
        return angularYSpeed;
    }

    public void setAngularYSpeed(float angularYSpeed) {
        this.angularYSpeed = angularYSpeed;
    }

    public float getAngularZSpeed() {
        return angularZSpeed;
    }

    public void setAngularZSpeed(float angularZSpeed) {
        this.angularZSpeed = angularZSpeed;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public float getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(float xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public float getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(float yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public float getzAcceleration() {
        return zAcceleration;
    }

    public void setzAcceleration(float zAcceleration) {
        this.zAcceleration = zAcceleration;
    }

    public float getContainingFuel() {
        return containingFuel;
    }

    public void setContainingFuel(float containingFuel) {
        this.containingFuel = containingFuel;
    }

    public float getContainingElectricity() {
        return containingElectricity;
    }

    public void setContainingElectricity(float containingElectricity) {
        this.containingElectricity = containingElectricity;
    }

    public Date getInformationDate() {
        return informationDate;
    }

    public void setInformationDate(Date informationDate) {
        this.informationDate = informationDate;
    }

    public Time getInformationTime() {
        return informationTime;
    }

    public void setInformationTime(Time informationTime) {
        this.informationTime = informationTime;
    }

    public int getRotationOrientation() {
        return rotationOrientation;
    }

    public void setRotationOrientation(int rotationOrientation) {
        this.rotationOrientation = rotationOrientation;
    }

    public boolean isWorkingFlag() {
        return workingFlag;
    }

    public void setWorkingFlag(boolean workingFlag) {
        this.workingFlag = workingFlag;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public String getGPSInformation() {
        return GPSInformation;
    }

    public void setGPSInformation(String GPSInformation) {
        this.GPSInformation = GPSInformation;
    }

    public DeviceDynamicInformation() {
        super();
    }

    public DeviceDynamicInformation(float angleX, float angleY, float angleZ, float angularXSpeed,
                                    float angularYSpeed, float angularZSpeed, float currentSpeed,
                                    float xAcceleration, float yAcceleration, float zAcceleration,
                                    float containingFuel, float containingElectricity, Date informationDate,
                                    Time informationTime, int rotationOrientation, boolean workingFlag,
                                    String iotDeviceId, String GPSInformation, float deltaDistance,
                                    float deltaFuel) {
        this.angleX = angleX;
        this.angleY = angleY;
        this.angleZ = angleZ;
        this.angularXSpeed = angularXSpeed;
        this.angularYSpeed = angularYSpeed;
        this.angularZSpeed = angularZSpeed;
        this.currentSpeed = currentSpeed;
        this.xAcceleration = xAcceleration;
        this.yAcceleration = yAcceleration;
        this.zAcceleration = zAcceleration;
        this.containingFuel = containingFuel;
        this.containingElectricity = containingElectricity;
        this.informationDate = informationDate;
        this.informationTime = informationTime;
        this.rotationOrientation = rotationOrientation;
        this.workingFlag = workingFlag;
        this.iotDeviceId = iotDeviceId;
        this.GPSInformation = GPSInformation;
        this.deltaDistance = deltaDistance;
        this.deltaFuel = deltaFuel;
    }
}

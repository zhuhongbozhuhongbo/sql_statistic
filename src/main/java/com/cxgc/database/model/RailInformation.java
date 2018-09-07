package com.cxgc.database.model;

public class RailInformation {

    private int railId;
    private String projectName;
    private int orderNum;
    private double lat;
    private double lng;

    /*
    *
    * 这个构造方法必须存在，否则会报错:
    * Cause: org.apache.ibatis.reflection.ReflectionException: Error instantiating class com.cxgc.database.model.RailInformatin
     */
    public RailInformation(){
        super();
    }

    public RailInformation(RailInformation initialRail){
        this.railId = initialRail.getRailId();
        this.projectName = initialRail.getProjectName();
        this.orderNum = initialRail.getOrderNum();
        this.lat = initialRail.getLat();
        this.lng = initialRail.getLng();
    }

    public RailInformation(int railId, String projectName, int orderNum, double lng, double lat) {
        this.railId = railId;
        this.projectName = projectName;
        this.orderNum = orderNum;
        this.lat = lat;
        this.lng = lng;
    }

    public int getRailId() {
        return railId;
    }

    public void setRailId(int railId) {
        this.railId = railId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

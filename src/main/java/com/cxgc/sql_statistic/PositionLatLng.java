package com.cxgc.sql_statistic;

class PositionLatLng {
    private double latitude;
    private double longitude;

    public PositionLatLng(double longitude, double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

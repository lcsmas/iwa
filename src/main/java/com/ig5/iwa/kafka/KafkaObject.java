package com.ig5.iwa.kafka;

public class KafkaObject{

    private Integer userId;
    private String state;
    private double latitude;
    private double longitude;

    public KafkaObject(Integer userId, String state, double latitude, double longitude) {
        this.userId = userId;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return getUserId() + " " + getState() +  " lat: " + getLatitude() + " long: " + getLongitude();
    }
}

package com.caribejobs.model;

import java.io.Serializable;

public class DaySchedule implements Serializable {

    private Boolean isSet = false;
    private int dayNumber = 0;
    private String startTime = "No establecida";
    private String endTime = "No establecida";

    public DaySchedule(int dayNumber, String startTime, String endTime) {
        this.dayNumber = dayNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DaySchedule() {

    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean isSet() {
        return isSet;
    }

    public void setSet(Boolean set) {
        isSet = set;
    }

    @Override
    public String toString() {
        return "DaySchedule{" +
                "isSet=" + isSet +
                ", dayNumber=" + dayNumber +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

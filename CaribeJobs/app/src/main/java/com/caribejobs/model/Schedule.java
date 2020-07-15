package com.caribejobs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Schedule extends DaySchedule implements Serializable {
    private boolean allDays = false;
    private DaySchedule monday = new DaySchedule();
    private DaySchedule tuesday = new DaySchedule();
    private DaySchedule wednesday = new DaySchedule();
    private DaySchedule thursday = new DaySchedule();
    private DaySchedule friday = new DaySchedule();
    private DaySchedule saturday = new DaySchedule();
    private DaySchedule sunday = new DaySchedule();
    private DaySchedule[] days = {monday, tuesday, wednesday, thursday, friday, saturday, sunday};

    public Schedule(boolean allDays, String startTime, String endTime, DaySchedule monday, DaySchedule tuesday, DaySchedule wednesday, DaySchedule thursday, DaySchedule friday, DaySchedule saturday, DaySchedule sunday) {
        setDayNumber(0);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.allDays = allDays;
        this.monday = monday;
        this.monday.setDayNumber(1);
        this.tuesday = tuesday;
        this.tuesday.setDayNumber(2);
        this.wednesday = wednesday;
        this.wednesday.setDayNumber(3);
        this.thursday = thursday;
        this.thursday.setDayNumber(4);
        this.friday = friday;
        this.friday.setDayNumber(5);
        this.saturday = saturday;
        this.saturday.setDayNumber(6);
        this.sunday = sunday;
        this.sunday.setDayNumber(7);
    }

    public Schedule() {
        setDayNumber(0);
        this.monday.setDayNumber(1);
        this.tuesday.setDayNumber(2);
        this.wednesday.setDayNumber(3);
        this.thursday.setDayNumber(4);
        this.friday.setDayNumber(5);
        this.saturday.setDayNumber(6);
        this.sunday.setDayNumber(7);
    }

    public DaySchedule[] getDays() {
        return days;
    }

    public boolean isAllDays() {
        return allDays;
    }

    public void setAllDays(boolean allDays) {
        this.allDays = allDays;
    }

    public DaySchedule getMonday() {
        return monday;
    }

    public void setMonday(DaySchedule monday) {
        this.monday = monday;
    }

    public DaySchedule getTuesday() {
        return tuesday;
    }

    public void setTuesday(DaySchedule tuesday) {
        this.tuesday = tuesday;
    }

    public DaySchedule getWednesday() {
        return wednesday;
    }

    public void setWednesday(DaySchedule wednesday) {
        this.wednesday = wednesday;
    }

    public DaySchedule getThursday() {
        return thursday;
    }

    public void setThursday(DaySchedule thursday) {
        this.thursday = thursday;
    }

    public DaySchedule getFriday() {
        return friday;
    }

    public void setFriday(DaySchedule friday) {
        this.friday = friday;
    }

    public DaySchedule getSaturday() {
        return saturday;
    }

    public void setSaturday(DaySchedule saturday) {
        this.saturday = saturday;
    }

    public DaySchedule getSunday() {
        return sunday;
    }

    public void setSunday(DaySchedule sunday) {
        this.sunday = sunday;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "allDays=" + allDays +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}


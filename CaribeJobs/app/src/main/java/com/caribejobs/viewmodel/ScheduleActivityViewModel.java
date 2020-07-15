package com.caribejobs.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.DaySchedule;
import com.caribejobs.model.Schedule;

public class ScheduleActivityViewModel extends ViewModel {
    private MutableLiveData<Schedule> scheduleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> allDaysEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> mondayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> tuesdayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> wednesdayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> thursdayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> fridayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> saturdayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> sundayEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertAllDaysStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertAllDaysEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertMondayStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertMondayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertTuesdayStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertTuesdayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertWednesdayStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertWednesdayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertThursdayStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertThursdayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertFridayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertFridayStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertSaturdayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertSaturdayStartTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertSundayEndTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertSundayStartTime = new MutableLiveData<>();

    public ScheduleActivityViewModel(Schedule schedule) {
        scheduleMutableLiveData.setValue(schedule);
        allDaysEnabled.setValue(schedule.isSet());
        mondayEnabled.setValue(schedule.getMonday().isSet());
        tuesdayEnabled.setValue(schedule.getTuesday().isSet());
        wednesdayEnabled.setValue(schedule.getThursday().isSet());
        thursdayEnabled.setValue(schedule.getThursday().isSet());
        fridayEnabled.setValue(schedule.getFriday().isSet());
        saturdayEnabled.setValue(schedule.getSaturday().isSet());
        sundayEnabled.setValue(schedule.getSunday().isSet());
        insertAllDaysEndTime.setValue(false);
        insertAllDaysStartTime.setValue(false);
        insertMondayStartTime.setValue(false);
        insertMondayEndTime.setValue(false);
        insertTuesdayStartTime.setValue(false);
        insertTuesdayEndTime.setValue(false);
        insertWednesdayStartTime.setValue(false);
        insertWednesdayEndTime.setValue(false);
        insertThursdayStartTime.setValue(false);
        insertThursdayEndTime.setValue(false);
        insertFridayEndTime.setValue(false);
        insertFridayStartTime.setValue(false);
        insertSaturdayEndTime.setValue(false);
        insertSaturdayStartTime.setValue(false);
        insertSundayEndTime.setValue(false);
        insertSundayStartTime.setValue(false);
    }

    public MutableLiveData<Schedule> getScheduleMutableLiveData() {
        return scheduleMutableLiveData;
    }

    public void setScheduleMutableLiveData(MutableLiveData<Schedule> scheduleMutableLiveData) {
        this.scheduleMutableLiveData = scheduleMutableLiveData;
    }

    public MutableLiveData<Boolean> getMondayEnabled() {
        return mondayEnabled;
    }

    public MutableLiveData<Boolean> getTuesdayEnabled() {
        return tuesdayEnabled;
    }

    public MutableLiveData<Boolean> getWednesdayEnabled() {
        return wednesdayEnabled;
    }

    public MutableLiveData<Boolean> getThursdayEnabled() {
        return thursdayEnabled;
    }

    public MutableLiveData<Boolean> getFridayEnabled() {
        return fridayEnabled;
    }

    public MutableLiveData<Boolean> getSaturdayEnabled() {
        return saturdayEnabled;
    }

    public MutableLiveData<Boolean> getSundayEnabled() {
        return sundayEnabled;
    }

    public MutableLiveData<Boolean> getAllDaysEnabled() {
        return allDaysEnabled;
    }

    public void onInsertAllDaysStartTime() {
        insertAllDaysStartTime.setValue(true);
    }

    public void onInsertAllDaysEndTime() {
        insertAllDaysEndTime.setValue(true);
    }

    public void onInsertMondayStartTime() {
        insertMondayStartTime.setValue(true);
    }

    public void onInsertMondayEndTime() {
        this.insertMondayEndTime.setValue(true);
    }


    public void onInsertTuesdayStartTime() {
        insertTuesdayStartTime.setValue(true);
    }

    public void onInsertTuesdayEndTime() {
        this.insertTuesdayEndTime.setValue(true);
    }

    public void onInsertWednesdayStartTime() {
        insertWednesdayStartTime.setValue(true);
    }

    public void onInsertWednesdayEndTime() {
        this.insertWednesdayEndTime.setValue(true);
    }

    public void onInsertThursdayStartTime() {
        insertThursdayStartTime.setValue(true);
    }

    public void onInsertThursdayEndTime() {
        this.insertThursdayEndTime.setValue(true);
    }

    public void onInsertFridayStartTime() {
        insertFridayStartTime.setValue(true);
    }

    public void onInsertFridayEndTime() {
        this.insertFridayEndTime.setValue(true);
    }

    public void onInsertSaturdayStartTime() {
        insertSaturdayStartTime.setValue(true);
    }

    public void onInsertSaturdayEndTime() {
        this.insertSaturdayEndTime.setValue(true);
    }

    public void onInsertSundayStartTime() {
        insertSundayStartTime.setValue(true);
    }

    public void onInsertSundayEndTime() {
        this.insertSundayEndTime.setValue(true);
    }

    public MutableLiveData<Boolean> getInsertAllDaysStartTime() {
        return insertAllDaysStartTime;
    }

    public MutableLiveData<Boolean> getInsertAllDaysEndTime() {
        return insertAllDaysEndTime;
    }

    public MutableLiveData<Boolean> getInsertMondayStartTime() {
        return insertMondayStartTime;
    }

    public MutableLiveData<Boolean> getInsertMondayEndTime() {
        return insertMondayEndTime;
    }

    public MutableLiveData<Boolean> getInsertTuesdayStartTime() {
        return insertTuesdayStartTime;
    }

    public MutableLiveData<Boolean> getInsertTuesdayEndTime() {
        return insertTuesdayEndTime;
    }

    public MutableLiveData<Boolean> getInsertWednesdayStartTime() {
        return insertWednesdayStartTime;
    }

    public MutableLiveData<Boolean> getInsertWednesdayEndTime() {
        return insertWednesdayEndTime;
    }

    public MutableLiveData<Boolean> getInsertThursdayStartTime() {
        return insertThursdayStartTime;
    }

    public MutableLiveData<Boolean> getInsertThursdayEndTime() {
        return insertThursdayEndTime;
    }

    public MutableLiveData<Boolean> getInsertFridayEndTime() {
        return insertFridayEndTime;
    }

    public MutableLiveData<Boolean> getInsertFridayStartTime() {
        return insertFridayStartTime;
    }

    public MutableLiveData<Boolean> getInsertSaturdayEndTime() {
        return insertSaturdayEndTime;
    }

    public MutableLiveData<Boolean> getInsertSaturdayStartTime() {
        return insertSaturdayStartTime;
    }

    public MutableLiveData<Boolean> getInsertSundayEndTime() {
        return insertSundayEndTime;
    }

    public MutableLiveData<Boolean> getInsertSundayStartTime() {
        return insertSundayStartTime;
    }
}

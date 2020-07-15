package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.WorkZone;

import java.util.ArrayList;

public class WorkZonesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<WorkZone>> workZoneMutableLiveData = new MutableLiveData<>();
    private ArrayList<WorkZone> workZones = new ArrayList<>();

    public WorkZonesViewModel(ArrayList<WorkZone> workZones) {
        this.workZones = workZones;
        this.workZoneMutableLiveData.setValue(workZones);
    }

    public ArrayList<WorkZone> getWorkZones() {
        return workZones;
    }

    public MutableLiveData<ArrayList<WorkZone>> getWorkZoneMutableLiveData() {
        return workZoneMutableLiveData;
    }

}

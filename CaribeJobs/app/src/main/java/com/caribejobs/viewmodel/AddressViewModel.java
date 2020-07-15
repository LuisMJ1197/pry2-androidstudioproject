package com.caribejobs.viewmodel;

import android.app.AlertDialog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.Address;

public class AddressViewModel extends ViewModel {
    private MutableLiveData<Address> address = new MutableLiveData<>();
    private MutableLiveData<Boolean> provinciaInsert = new MutableLiveData<>();
    private MutableLiveData<Boolean> cantonInsert = new MutableLiveData<>();
    private MutableLiveData<Boolean> distritoInsert = new MutableLiveData<>();


    public AddressViewModel(Address address) {
        this.address.setValue(address);
        provinciaInsert.setValue(false);
        cantonInsert.setValue(false);
        distritoInsert.setValue(false);
    }

    public MutableLiveData<Address> getAddress() {
        return address;
    }

    public MutableLiveData<Boolean> getProvinciaInsert() {
        return provinciaInsert;
    }

    public MutableLiveData<Boolean> getCantonInsert() {
        return cantonInsert;
    }

    public MutableLiveData<Boolean> getDistritoInsert() {
        return distritoInsert;
    }

    public void onProvinciaClick() {
        this.provinciaInsert.setValue(true);
    }

    public void onCantonClick() {
        this.cantonInsert.setValue(true);
    }

    public void onDistritoClick() {
        this.distritoInsert.setValue(true);
    }
}

package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditInputViewModel extends ViewModel {
    private MutableLiveData<String> inputData = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Boolean> pressCancel = new MutableLiveData<>();
    private MutableLiveData<Boolean> pressAccept = new MutableLiveData<>();

    public EditInputViewModel(String message, MutableLiveData<String> data) {
        this.inputData = data;
        this.message.setValue(message);
        this.pressAccept.setValue(false);
        this.pressCancel.setValue(true);
    }

    public MutableLiveData<String> getInputData() {
        return inputData;
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getPressAccept() {
        return pressAccept;
    }

    public MutableLiveData<Boolean> getPressCancel() {
        return pressCancel;
    }

    public void setInputData(String inputData) {
        this.inputData.setValue(inputData);
    }

    public void setMessage(String message) {
        this.message.setValue(message);
    }

    public void onCancel() {
        this.pressCancel.setValue(true);
    }

    public void onAccept() {
        this.pressAccept.setValue(true);
    }
}

package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.User;

public class PersonalInfoViewModel extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Boolean> emailClick = new MutableLiveData<>();
    private MutableLiveData<Boolean> phonenumber1Click = new MutableLiveData<>();
    private MutableLiveData<Boolean> phonenumber2Click = new MutableLiveData<>();

    public PersonalInfoViewModel(User user) {
        this.user.setValue(user);
        emailClick.setValue(false);
        phonenumber1Click.setValue(false);
        phonenumber2Click.setValue(false);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<Boolean> getEmailClick() {
        return emailClick;
    }

    public MutableLiveData<Boolean> getPhonenumber1Click() {
        return phonenumber1Click;
    }

    public MutableLiveData<Boolean> getPhonenumber2Click() {
        return phonenumber2Click;
    }

    public void onEmailClick() {
        emailClick.setValue(true);
    }

    public void onPhoneNumber1Click() {
        phonenumber1Click.setValue(true);
    }

    public void onPhoneNumber2Click() {
        phonenumber2Click.setValue(true);
    }

}

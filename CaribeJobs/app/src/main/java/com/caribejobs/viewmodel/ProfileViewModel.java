package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.User;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();

    public ProfileViewModel (User user) {
        this.user.setValue(user);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }


}

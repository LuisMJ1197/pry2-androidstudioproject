package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.User;

public class ProfilePictureViewModel extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();

    public ProfilePictureViewModel(User user) {
        if (user == null) {
            this.user.setValue(new User());
        } else {
            this.user.setValue(user);
        }

    }

    public MutableLiveData<User> getUser() {
        return user;
    }
}

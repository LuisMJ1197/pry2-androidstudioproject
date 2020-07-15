package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.util.Constants;

public class Register2ViewModel extends ViewModel {
    private MutableLiveData<UserLogin> userRegister = new MutableLiveData<>();
    private UserLogin userLogin;
    private MutableLiveData<String> errorFirstname = new MutableLiveData<>();
    private MutableLiveData<String> errorLastname1 = new MutableLiveData<>();
    private MutableLiveData<String> errorLastname2 = new MutableLiveData<>();
    private MutableLiveData<String> errorPhonenumber1 = new MutableLiveData<>();
    private MutableLiveData<String> errorPhonenumber2 = new MutableLiveData<>();
    private MutableLiveData<String> errorBirthday = new MutableLiveData<>();
    private MutableLiveData<Integer> errorFirstnameVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorLastname1Visibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorLastname2Visibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorPhonenumber1Visibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorPhonenumber2Visibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorBirthdayVisibility = new MutableLiveData<>();
    private MutableLiveData<Boolean> endRegister = new MutableLiveData<>();

    public Register2ViewModel(UserLogin user) {
        this.userLogin = user;
        this.userRegister.setValue(user);
        errorFirstname.setValue("Ingrese su nombre");
        errorLastname1.setValue("Ingrese su primer apellido");
        errorLastname2.setValue("Ingrese su segundo apellido");
        errorPhonenumber1.setValue("Ingrese el teléfono");
        errorPhonenumber2.setValue("Ingrese el teléfono");
        errorBirthday.setValue("Ingrese su fecha de cumpleaños");
        errorFirstnameVisibility.setValue(4);
        errorLastname1Visibility.setValue(4);
        errorLastname2Visibility.setValue(4);
        errorPhonenumber1Visibility.setValue(4);
        errorPhonenumber2Visibility.setValue(4);
        errorBirthdayVisibility.setValue(4);
        endRegister.setValue(false);
    }

    public MutableLiveData<UserLogin> getUserRegister() {
        return userRegister;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public MutableLiveData<String> getErrorFirstname() {
        return errorFirstname;
    }

    public MutableLiveData<String> getErrorLastname1() {
        return errorLastname1;
    }

    public MutableLiveData<String> getErrorLastname2() {
        return errorLastname2;
    }

    public MutableLiveData<String> getErrorPhonenumber1() {
        return errorPhonenumber1;
    }

    public MutableLiveData<String> getErrorPhonenumber2() {
        return errorPhonenumber2;
    }

    public MutableLiveData<String> getErrorBirthday() {
        return errorBirthday;
    }

    public MutableLiveData<Integer> getErrorFirstnameVisibility() {
        return errorFirstnameVisibility;
    }

    public MutableLiveData<Integer> getErrorLastname1Visibility() {
        return errorLastname1Visibility;
    }

    public MutableLiveData<Integer> getErrorLastname2Visibility() {
        return errorLastname2Visibility;
    }

    public MutableLiveData<Integer> getErrorPhonenumber1Visibility() {
        return errorPhonenumber1Visibility;
    }

    public MutableLiveData<Integer> getErrorPhonenumber2Visibility() {
        return errorPhonenumber2Visibility;
    }

    public MutableLiveData<Integer> getErrorBirthdayVisibility() {
        return errorBirthdayVisibility;
    }

    public void onEnRegisterClick() {
        if (this.validateEntries()) {
            endRegister.setValue(true);
        }
    }

    public boolean validateEntries() {
        if (userRegister.getValue().getFirstname().length() <= 0) {
            this.errorFirstnameVisibility.setValue(Constants.VISIBLE);
            return false;
        } else {
            this.errorFirstnameVisibility.setValue(Constants.INVISIBLE);
        }
        if (userRegister.getValue().getLastname().length() <= 0) {
            this.errorLastname1Visibility.setValue(Constants.VISIBLE);
            return false;
        } else {
            this.errorLastname1Visibility.setValue(Constants.INVISIBLE);
        }
        if (userRegister.getValue().getLastname2().length() <= 0) {
            this.errorLastname2Visibility.setValue(Constants.VISIBLE);
            return false;
        } else {
            this.errorLastname2Visibility.setValue(Constants.INVISIBLE);
        }
        if (userRegister.getValue().getPhonenumber1().length() <= 0) {
            this.errorPhonenumber1Visibility.setValue(Constants.VISIBLE);
            return false;
        } else {
            this.errorPhonenumber1Visibility.setValue(Constants.INVISIBLE);
        }
        if (userRegister.getValue().getPhonenumber2().length() <= 0) {
            this.errorPhonenumber2Visibility.setValue(Constants.VISIBLE);
            return false;
        } else {
            this.errorPhonenumber2Visibility.setValue(Constants.INVISIBLE);
        }
        if (userRegister.getValue().getBirthday().length() <= 0) {
            this.errorBirthdayVisibility.setValue(Constants.VISIBLE);
            return false;
        } else {
            this.errorBirthdayVisibility.setValue(Constants.INVISIBLE);
        }
        return true;
    }

    public MutableLiveData<Boolean> getEndRegister() {
        return endRegister;
    }
}

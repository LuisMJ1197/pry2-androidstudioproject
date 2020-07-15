package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.util.Constants;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<UserLogin> userRegister = new MutableLiveData<>();
    private UserLogin user;
    private MutableLiveData<String> errorPassword = new MutableLiveData<>();
    private MutableLiveData<String> errorConfirmPassword =  new MutableLiveData<>();
    private MutableLiveData<String> errorUsername =  new MutableLiveData<>();
    private MutableLiveData<String> errorEmail =  new MutableLiveData<>();
    private MutableLiveData<Integer> errorPasswordVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorConfirmPasswordVisibility =  new MutableLiveData<>();
    private MutableLiveData<Integer> errorUsernameVisibility =  new MutableLiveData<>();
    private MutableLiveData<Integer> errorEmailVisibility =  new MutableLiveData<>();
    private MutableLiveData<String> confirmPassword = new MutableLiveData<>();
    private MutableLiveData<Boolean> nextStep = new MutableLiveData<>();

    public RegisterViewModel() {
        user = new UserLogin("", "");
        user.setFirstname("");
        user.setLastname("");
        user.setLastname2("");
        user.setEmail("");
        user.setPhonenumber1("");
        user.setPhonenumber2("");
        user.setBirthday("");
        userRegister.setValue(user);
        errorPassword.setValue("Ingrese la contraseña");
        errorConfirmPassword.setValue("Las contraseñas no coinciden");
        errorEmail.setValue("Ingrese el correo electrónico");
        errorUsername.setValue("Ingrese el nombre de usuario");
        errorPasswordVisibility.setValue(4);
        errorConfirmPasswordVisibility.setValue(4);
        errorEmailVisibility.setValue(4);
        errorUsernameVisibility.setValue(4);
        confirmPassword.setValue("");
        nextStep.setValue(false);
    }

    public MutableLiveData<String> getConfirmPassword() {
        return confirmPassword;
    }

    public MutableLiveData<UserLogin> getUserRegister() {
        return userRegister;
    }

    public User getUser() {
        return user;
    }

    public MutableLiveData<String> getErrorPassword() {
        return errorPassword;
    }

    public MutableLiveData<String> getErrorConfirmPassword() {
        return errorConfirmPassword;
    }

    public MutableLiveData<String> getErrorUsername() {
        return errorUsername;
    }

    public MutableLiveData<String> getErrorEmail() {
        return errorEmail;
    }

    public void setUserRegister(MutableLiveData<UserLogin> userRegister) {
        this.userRegister = userRegister;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public void setErrorPassword(MutableLiveData<String> errorPassword) {
        this.errorPassword = errorPassword;
    }

    public void setErrorConfirmPassword(MutableLiveData<String> errorConfirmPassword) {
        this.errorConfirmPassword = errorConfirmPassword;
    }

    public void setErrorUsername(MutableLiveData<String> errorUsername) {
        this.errorUsername = errorUsername;
    }

    public void setErrorEmail(MutableLiveData<String> errorEmail) {
        this.errorEmail = errorEmail;
    }

    public MutableLiveData<Integer> getErrorPasswordVisibility() {
        return errorPasswordVisibility;
    }

    public MutableLiveData<Integer> getErrorConfirmPasswordVisibility() {
        return errorConfirmPasswordVisibility;
    }

    public MutableLiveData<Integer> getErrorUsernameVisibility() {
        return errorUsernameVisibility;
    }

    public MutableLiveData<Integer> getErrorEmailVisibility() {
        return errorEmailVisibility;
    }

    public void onRegisterClick() {
        if (this.validateEntries()) {
            nextStep.setValue(true);
        }
    }

    public boolean validateEntries() {
        if (userRegister.getValue().getUsername().length() <= 0 || userRegister.getValue().getPassword().length() <= 0
            || userRegister.getValue().getEmail().length() <= 0 || confirmPassword.getValue().length() <= 0
                || !confirmPassword.getValue().equals(userRegister.getValue().getPassword())) {
            if (userRegister.getValue().getUsername().length() <= 0) {
                this.errorUsernameVisibility.setValue(Constants.VISIBLE);
            } else {
                this.errorUsernameVisibility.setValue(Constants.INVISIBLE);
            }
            if (userRegister.getValue().getPassword().length() <= 0) {
                this.errorPasswordVisibility.setValue(Constants.VISIBLE);
            } else {
                this.errorPasswordVisibility.setValue(Constants.INVISIBLE);
            }
            if (userRegister.getValue().getEmail().length() <= 0) {
                this.errorEmailVisibility.setValue(Constants.VISIBLE);
            } else {
                this.errorEmailVisibility.setValue(Constants.INVISIBLE);
            }

            if (confirmPassword.getValue().length() <= 0 || !confirmPassword.getValue().equals(userRegister.getValue().getPassword())) {
                this.errorConfirmPasswordVisibility.setValue(Constants.VISIBLE);
            } else {
                this.errorConfirmPasswordVisibility.setValue(Constants.INVISIBLE);
            }
            return false;
        } else {
            this.errorUsernameVisibility.setValue(Constants.INVISIBLE);
            this.errorConfirmPasswordVisibility.setValue(Constants.INVISIBLE);
            this.errorPasswordVisibility.setValue(Constants.INVISIBLE);
            this.errorEmailVisibility.setValue(Constants.INVISIBLE);
            return true;
        }
    }

    public MutableLiveData<Boolean> getNextStep() {
        return nextStep;
    }


}

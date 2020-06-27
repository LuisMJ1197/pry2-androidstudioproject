package com.caribejobs.viewmodel;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import com.caribejobs.BR;
import com.caribejobs.model.User;

public class HomePageViewModel extends BaseObservable {
    private User user;

    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";

    @Bindable
    private String toastMessage = null;

    public HomePageViewModel() {
        user = new User("LuisMJ","");
    }

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void setUsername(String username) {
        user.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getUsername() {
        return user.getUsername();
    }

    @Bindable
    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password) {
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public void onLoginClicked() {
        if (isInputDataValid()) {
            setToastMessage(successMessage);
        } else {
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUsername()) && Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches() && getUserPassword().length() > 5;
    }
}

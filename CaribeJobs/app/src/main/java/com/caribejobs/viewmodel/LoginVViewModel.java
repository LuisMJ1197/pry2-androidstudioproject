package com.caribejobs.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.dao.UserDAO;
import com.caribejobs.model.UserLogin;
import com.caribejobs.util.Constants;

public class LoginVViewModel extends ViewModel implements UserDAO.UserDAOListener {
    private MutableLiveData<UserLogin> userLogin = new MutableLiveData<>();
    private final String errorUsername = "Ingrese un nombre de usuario válido.";
    public final String errorPassword = "Ingrese una contraseña válida.";
    private MutableLiveData<Integer> errorUsernameVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> errorPasswordVisibilty = new MutableLiveData<>();
    private MutableLiveData<UserLogin> logged = new MutableLiveData<>();
    private MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public LoginVViewModel() {
        userLogin.setValue(new UserLogin("LuisMJ", "luisito138"));
        this.errorUsernameVisibility.setValue(Constants.INVISIBLE);
        this.errorPasswordVisibilty.setValue(Constants.INVISIBLE);
    }

    public MutableLiveData<UserLogin> getUserLogin() {
        return userLogin;
    }

    public MutableLiveData<Integer> getErrorUsernameVisibility() {
        return errorUsernameVisibility;
    }

    public MutableLiveData<Integer> getErrorPasswordVisibilty() {
        return errorPasswordVisibilty;
    }

    public String getErrorUsername() {
        return errorUsername;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public MutableLiveData<UserLogin> getLogged() {
        return logged;
    }

    public void setLogged(UserLogin logged) {
        this.logged.setValue(logged);
    }

    public void onLoginClicked() {
        if (this.validateEntries()) {
            new UserDAO().execLogin(userLogin.getValue(), this);
        }
    }

    public boolean validateEntries() {
        if (userLogin.getValue().getUsername().length() <= 0 || userLogin.getValue().getPassword().length() <= 0) {
            if (userLogin.getValue().getUsername().length() <= 0) {
                this.errorUsernameVisibility.setValue(Constants.VISIBLE);
            } else {
                this.errorUsernameVisibility.setValue(Constants.INVISIBLE);
            }
            if (userLogin.getValue().getPassword().length() <= 0) {
                this.errorPasswordVisibilty.setValue(Constants.VISIBLE);
            } else {
                this.errorPasswordVisibilty.setValue(Constants.INVISIBLE);
            }
            return false;
        } else {
            this.errorUsernameVisibility.setValue(Constants.INVISIBLE);
            this.errorPasswordVisibilty.setValue(Constants.INVISIBLE);
            return true;
        }
    }

    public MutableLiveData<String> getToastMessage() {
        return toastMessage;
    }

    @Override
    public void onResultLogin(int resultCode, UserLogin user) {
        switch (resultCode) {
            case UserDAO.USER_FOUND: {
                setLogged(user);
                break;
            }
            case UserDAO.USER_NOT_FOUND: {
                toastMessage.setValue("Datos incorrectos.");
                break;
            }
            case UserDAO.ERROR: {
                toastMessage.setValue("Error de conexión.");
                break;
            }
        }
    }
}

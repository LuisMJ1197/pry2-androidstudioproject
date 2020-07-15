package com.caribejobs.dao;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.RegisterUserMutation;
import com.caribejobs.SetProfilePictureMutation;
import com.caribejobs.UpdateAddressMutation;
import com.caribejobs.UpdateUserMutation;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.apidatabinding.ModelClassBuilder;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.LoginQuery;
import com.caribejobs.type.AddressInput;
import com.caribejobs.type.UserLoginInput;
import com.caribejobs.view.InitOptionActivity;
import com.caribejobs.view.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class UserDAO {
    public static final int USER_FOUND = 0;
    public static final int USER_NOT_FOUND = 1;
    public static final int ERROR = 2;
    private final String TAG = getClass().getSimpleName();
    private final int TIME_OUT = 3000;
    private boolean timeout_reached = false;
    private UserLogin userLogin;
    private int resultCode = ERROR;

    public interface UserDAOListener {
        void onResultLogin(int resultCode, UserLogin user);
    }

    void onResultLogin(int resultCode, UserLogin user) {
        this.resultCode = resultCode;
        userLogin = user;
    }

    public void execLogin(final UserLogin userLogin, final ViewModel context) {
        validateUserLogin(userLogin.getUsername(), userLogin.getPassword(), context);
        new Timer().schedule(new TimerTask(){
            public void run() {
                timeout_reached = true;
            }
        }, TIME_OUT );
        while (timeout_reached != true) {
        }
        ((UserDAOListener) context).onResultLogin(resultCode, this.userLogin);
    }

    private void validateUserLogin(String username, String password, final ViewModel context) {
        userLogin = null;
        GraphQLConnector.setApolloClient().query(
                LoginQuery.builder()
                        .username(username)
                        .password(password)
                        .build())
                .enqueue(new ApolloCall.Callback<LoginQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<LoginQuery.Data> response) {
                        if (response.data() != null) {
                            if (response.data().login() != null) {
                                userLogin = ((UserLogin) ModelClassBuilder.createUserFromLoginData(response.data().login()));
                                onResultLogin(USER_FOUND, userLogin);
                            } else {
                                onResultLogin(USER_NOT_FOUND, null);
                            }
                        } else {
                            onResultLogin(ERROR, null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        e.printStackTrace();
                        onResultLogin(ERROR, null);
                    }
                });
    }

    public void updateProfilePicture(User user) {
        GraphQLConnector.setApolloClient().mutate(
                SetProfilePictureMutation.builder()
                        .username(user.getUsername())
                        .imageURL(user.getProfilePicture()).build()
        ).enqueue(new ApolloCall.Callback<SetProfilePictureMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<SetProfilePictureMutation.Data> response) {
                System.out.println("Correct");
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("False");
            }
        });
    }

    public void updateAddress(User user) {
        GraphQLConnector.setApolloClient().mutate(
                UpdateAddressMutation.builder()
                        .address(AddressInput.builder().username(user.getUsername())
                                .provincia(user.getAddress().getProvincia())
                                .canton(user.getAddress().getCanton())
                                .distrito(user.getAddress().getDistrito()).build()).build()
        ).enqueue(new ApolloCall.Callback<UpdateAddressMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpdateAddressMutation.Data> response) {
                System.out.println("Correct");
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("False");
            }
        });
    }

    public void updateUser(User user) {
        GraphQLConnector.setApolloClient().mutate(
                UpdateUserMutation.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .phonenumber1(user.getPhonenumber1())
                        .phonenumber2(user.getPhonenumber2()).build()
        ).enqueue(new ApolloCall.Callback<UpdateUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpdateUserMutation.Data> response) {
                System.out.println("Correct");
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("False");
            }
        });
    }

    public void execUpdateUser(User user) {
        System.out.println(user.toString());
    }

    public void registerUser(UserLogin user, int selectedDay, int selectedMonth, int selectedYear) {
        GraphQLConnector.setApolloClient().mutate(
                RegisterUserMutation.builder()
                        .user(UserLoginInput.builder()
                                .username(user.getUsername())
                                .password(user.getPassword())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .lastname2(user.getLastname2())
                                .email(user.getEmail())
                                .phonenumber1(user.getPhonenumber1())
                                .phonenumber2(user.getPhonenumber2())
                                .birthday_day(selectedDay)
                                .birthday_month(selectedMonth)
                                .birthday_year(selectedYear)
                                .address(
                                        AddressInput.builder()
                                                .username(user.getUsername())
                                                .provincia("No establecida")
                                                .canton("No establecido")
                                                .distrito("No establecido")
                                                .build()
                                )
                                .build()
                        )
                .build()
        ).enqueue(new ApolloCall.Callback<RegisterUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<RegisterUserMutation.Data> response) {

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}

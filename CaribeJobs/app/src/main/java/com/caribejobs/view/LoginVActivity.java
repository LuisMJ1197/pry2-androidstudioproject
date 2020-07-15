package com.caribejobs.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.caribejobs.R;
import com.caribejobs.databinding.ActivityLoginVBinding;
import com.caribejobs.model.UserLogin;
import com.caribejobs.viewmodel.LoginVViewModel;

public class LoginVActivity extends AppCompatActivity {
    private LoginVViewModel loginVViewModel;
    public static LoginVActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        ActivityLoginVBinding loginVActivityDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_v);
        loginVViewModel = ViewModelProviders.of(this).get(LoginVViewModel.class);
        loginVActivityDataBinding.setViewModel(loginVViewModel);
        loginVActivityDataBinding.setLifecycleOwner(this);

        loginVViewModel.getLogged().observe(this, new Observer<UserLogin>() {
            @Override
            public void onChanged(UserLogin userLogin) {
                if (userLogin != null) {
                    UserLogin.setUserLogged(userLogin);
                    Intent  intent = new Intent(LoginVActivity.instance, LoggedHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        final LoginVActivity context = this;
        
        loginVViewModel.getToastMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(context, s, Toast.LENGTH_LONG);
            }
        });

    }

    public void goRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }
}

package com.caribejobs.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caribejobs.R;
import com.caribejobs.databinding.ActivityRegisterBinding;
import com.caribejobs.viewmodel.Register2ViewModel;
import com.caribejobs.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        final RegisterViewModel registerViewModel = new RegisterViewModel();
        activityRegisterBinding.setViewModel(registerViewModel);
        activityRegisterBinding.setLifecycleOwner(this);
        final RegisterActivity context = this;

        registerViewModel.getNextStep().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Intent i = new Intent(context, Register2Activity.class);
                    i.putExtra("userregister", registerViewModel.getUserRegister().getValue());
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public void goLogin(View view) {
        Intent i = new Intent(this, LoginVActivity.class);
        startActivity(i);
        finish();
    }
}

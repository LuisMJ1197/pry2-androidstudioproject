package com.caribejobs.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.caribejobs.R;
import com.caribejobs.dao.UserDAO;
import com.caribejobs.databinding.ActivityRegister2Binding;
import com.caribejobs.databinding.ActivityRegisterBinding;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.viewmodel.Register2ViewModel;

import java.util.Calendar;

public class Register2Activity extends AppCompatActivity {
    EditText eText;
    int selectedMonth = 0;
    int selectedDay = 0;
    int selectedYear = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegister2Binding activityRegister2Binding = DataBindingUtil.setContentView(this, R.layout.activity_register2);
        UserLogin userregister = (UserLogin) getIntent().getSerializableExtra("userregister");
        final Register2ViewModel register2ViewModel = new Register2ViewModel(userregister);
        activityRegister2Binding.setViewModel(register2ViewModel);
        activityRegister2Binding.setLifecycleOwner(this);

        eText = (EditText) findViewById(R.id.inBirthday);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(view);
            }
        });

        register2ViewModel.getEndRegister().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    UserLogin user = (UserLogin) register2ViewModel.getUserRegister().getValue();
                    new UserDAO().registerUser(user, selectedDay, selectedMonth, selectedYear);
                    goBack(null);
                }
            }
        });
    }

    public void goBack(View view) {
        Intent i = new Intent(this, InitOptionActivity.class);
        startActivity(i);
        finish();
    }

    public void openDatePicker(View view) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        DatePickerDialog picker = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDay = dayOfMonth;
                        selectedMonth = monthOfYear;
                        selectedYear = year;
                        eText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
        picker.show();
    }
}

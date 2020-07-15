package com.caribejobs.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.caribejobs.R;
import com.caribejobs.dao.ScheduleDAO;
import com.caribejobs.databinding.ActivityScheduleBinding;
import com.caribejobs.model.Schedule;
import com.caribejobs.model.User;
import com.caribejobs.viewmodel.ScheduleActivityViewModel;

import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {
    ScheduleActivityViewModel scheduleActivityViewModel;
    ScheduleActivity context;
    User user;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                new ScheduleDAO().saveSchedule(scheduleActivityViewModel.getScheduleMutableLiveData().getValue());
                handleOnBackPress();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScheduleBinding activityScheduleBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        activityScheduleBinding.setLifecycleOwner(this);
        user = (User) getIntent().getSerializableExtra("user");
        scheduleActivityViewModel = new ScheduleActivityViewModel(user.getSchedule());
        activityScheduleBinding.setViewModel(scheduleActivityViewModel);
        context = this;

        scheduleActivityViewModel.getAllDaysEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.allDaysLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getMondayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.mondayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getMonday().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getTuesdayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.tuesdayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getTuesday().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getWednesdayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.wednesdayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getWednesday().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getThursdayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.thursdayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getThursday().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getFridayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.fridayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getFriday().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getSaturdayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.saturdayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getSaturday().setSet(aBoolean);
            }
        });

        scheduleActivityViewModel.getSundayEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LinearLayout mHiddenLinearLayout = ((LinearLayout) findViewById(R.id.sundayLayout));
                mHiddenLinearLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                scheduleActivityViewModel.getScheduleMutableLiveData().getValue().getSunday().setSet(aBoolean);
            }
        });

        createTimePicker(scheduleActivityViewModel.getInsertAllDaysStartTime(), R.id.allDaysStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertAllDaysEndTime(), R.id.allDaysEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertMondayStartTime(), R.id.mondayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertMondayEndTime(), R.id.mondayEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertTuesdayStartTime(), R.id.tuesdayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertTuesdayEndTime(), R.id.tuesdayEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertWednesdayStartTime(), R.id.wednesdayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertWednesdayEndTime(), R.id.wednesdayEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertThursdayStartTime(), R.id.thursdayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertThursdayEndTime(), R.id.thursdayEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertFridayStartTime(), R.id.fridayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertFridayEndTime(), R.id.fridayEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertSaturdayStartTime(), R.id.saturdayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertSaturdayEndTime(), R.id.saturdayEndTime, true);

        createTimePicker(scheduleActivityViewModel.getInsertSundayStartTime(), R.id.sundayStartTime);
        createTimePicker(scheduleActivityViewModel.getInsertSundayEndTime(), R.id.sundayEndTime, true);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Disponibilidad");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnBackPress();
            }
        });

    }

    public void createTimePicker(MutableLiveData<Boolean> mutableLiveData, final int id) {
        mutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            TextView selected = (TextView) findViewById(id);
                            selected.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                        }
                    }, 8, 0, true);//Yes 24 hour time
                    mTimePicker.setTitle("Seleccione una hora");
                    mTimePicker.show();
                }
            }
        });
    }

    public void createTimePicker(MutableLiveData<Boolean> mutableLiveData, final int id, final boolean end) {
        mutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            TextView selected = (TextView) findViewById(id);
                            selected.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                        }
                    }, end ? 17 : 8, 0, true);//Yes 24 hour time
                    mTimePicker.setTitle("Seleccione una hora");
                    mTimePicker.show();
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        handleOnBackPress();
    }

    private void handleOnBackPress() {
        user.setSchedule(scheduleActivityViewModel.getScheduleMutableLiveData().getValue());
        Intent i = new Intent();
        i.putExtra("user", user);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}

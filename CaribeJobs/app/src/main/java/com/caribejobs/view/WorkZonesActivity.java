package com.caribejobs.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caribejobs.R;
import com.caribejobs.dao.WorkZoneDAO;
import com.caribejobs.databinding.ActivityProfessionsBinding;
import com.caribejobs.databinding.ActivityWorkZonesBinding;
import com.caribejobs.model.User;
import com.caribejobs.model.UserProfession;
import com.caribejobs.model.WorkZone;
import com.caribejobs.view.adapters.ProfessionsRecyclerViewAdapter;
import com.caribejobs.view.adapters.WorkZonesRecyclerViewAdapter;
import com.caribejobs.view.fragments.AddProfessionFragment;
import com.caribejobs.view.fragments.AddWorkZoneFragment;
import com.caribejobs.viewmodel.ProfessionsViewModel;
import com.caribejobs.viewmodel.WorkZonesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WorkZonesActivity extends AppCompatActivity implements WorkZonesRecyclerViewAdapter.OnClickListener, AddWorkZoneFragment.AddWorkZoneListener {
    WorkZonesViewModel workZonesViewModel;
    WorkZonesActivity context;
    RecyclerView recyclerView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWorkZonesBinding activityWorkZonesBinding = DataBindingUtil.setContentView(this, R.layout.activity_work_zones);
        activityWorkZonesBinding.setLifecycleOwner(this);
        user = (User) getIntent().getSerializableExtra("user");
        workZonesViewModel = new WorkZonesViewModel(user.getWorkZones());
        activityWorkZonesBinding.setViewModel(workZonesViewModel);
        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        workZonesViewModel.getWorkZoneMutableLiveData().observe(this, new Observer<ArrayList<WorkZone>>() {
            @Override
            public void onChanged(ArrayList<WorkZone> workZones) {
                WorkZonesRecyclerViewAdapter workZonesRecyclerViewAdapter =
                        new WorkZonesRecyclerViewAdapter(context, workZones, context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(workZonesRecyclerViewAdapter);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Zonas de trabajo");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnBackPress();
            }
        });

        ((FloatingActionButton) findViewById(R.id.addProfessionFloatingButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                AddWorkZoneFragment addWorkZoneFragment = new AddWorkZoneFragment();
                addWorkZoneFragment.show(fm, "add_work_zone");
            }
        });
    }

    @Override
    public void onBackPressed() {
        handleOnBackPress();
    }

    private void handleOnBackPress() {
        Intent i = new Intent();
        i.putExtra("user", user);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    @Override
    public void onClick(int position) {

    }

    @Override
        public void onFinishAddWorkZone(WorkZone workZone) {
        new WorkZoneDAO().saveWorkZone(workZone);
        workZonesViewModel.getWorkZones().add(workZone);
        workZonesViewModel.getWorkZoneMutableLiveData().setValue(workZonesViewModel.getWorkZones());
        user.setWorkZones(workZonesViewModel.getWorkZones());
    }
}

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
import com.caribejobs.dao.ReferenceDAO;
import com.caribejobs.databinding.ActivityReferencesBinding;
import com.caribejobs.model.Reference;
import com.caribejobs.model.User;
import com.caribejobs.model.WorkZone;
import com.caribejobs.view.adapters.ReferencesRecyclerViewAdapter;
import com.caribejobs.view.fragments.AddReferenceFragment;
import com.caribejobs.view.fragments.AddWorkZoneFragment;
import com.caribejobs.viewmodel.ReferencesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReferencesActivity extends AppCompatActivity implements ReferencesRecyclerViewAdapter.OnClickListener, AddReferenceFragment.AddReferenceListener {
    ReferencesViewModel referencesViewModel;
    ReferencesActivity context;
    RecyclerView recyclerView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReferencesBinding activityReferencesBinding = DataBindingUtil.setContentView(this, R.layout.activity_references);
        activityReferencesBinding.setLifecycleOwner(this);
        user = (User) getIntent().getSerializableExtra("user");
        referencesViewModel = new ReferencesViewModel(user.getReferences());
        activityReferencesBinding.setViewModel(referencesViewModel);
        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        referencesViewModel.getReferenceMutableLiveData().observe(this, new Observer<ArrayList<Reference>>() {
            @Override
            public void onChanged(ArrayList<Reference> references) {
                ReferencesRecyclerViewAdapter referencesRecyclerViewAdapter =
                        new ReferencesRecyclerViewAdapter(context, references, context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(referencesRecyclerViewAdapter);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Referencias");
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
                AddReferenceFragment addReferenceFragment = new AddReferenceFragment();
                addReferenceFragment.show(fm, "add_reference");
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
    public void onFinishAddReference(Reference reference) {
        new ReferenceDAO().saveReferences(reference);
        referencesViewModel.getReferences().add(reference);
        referencesViewModel.getReferenceMutableLiveData().setValue(referencesViewModel.getReferences());
        user.setReferences(referencesViewModel.getReferences());
    }

}

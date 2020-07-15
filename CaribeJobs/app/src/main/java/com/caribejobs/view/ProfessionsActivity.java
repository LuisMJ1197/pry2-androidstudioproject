package com.caribejobs.view;

import androidx.annotation.Nullable;
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
import com.caribejobs.dao.ProfessionDAO;
import com.caribejobs.databinding.ActivityProfessionsBinding;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.UserProfession;
import com.caribejobs.view.adapters.ProfessionsRecyclerViewAdapter;
import com.caribejobs.view.fragments.AddProfessionFragment;
import com.caribejobs.viewmodel.ProfessionsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfessionsActivity extends AppCompatActivity implements ProfessionsRecyclerViewAdapter.OnClickListener, AddProfessionFragment.AddProfessionListener {
    ProfessionsViewModel professionsViewModel;
    ProfessionsActivity context;
    RecyclerView recyclerView;
    User user;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfessionsBinding activityProfessionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_professions);
        activityProfessionsBinding.setLifecycleOwner(this);
        user = (User) getIntent().getSerializableExtra("user");
        professionsViewModel = new ProfessionsViewModel(user.getProfessions());
        activityProfessionsBinding.setViewModel(professionsViewModel);
        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        professionsViewModel.getProfessionsLD().observe(this, new Observer<ArrayList<UserProfession>>() {
            @Override
            public void onChanged(ArrayList<UserProfession> professions) {
                ProfessionsRecyclerViewAdapter professionsRecyclerViewAdapter =
                        new ProfessionsRecyclerViewAdapter(context, professions, context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(professionsRecyclerViewAdapter);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Profesiones");
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
                AddProfessionFragment addProfessionFragment = new AddProfessionFragment();
                addProfessionFragment.show(fm, "add_profession");
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
        selectedPosition = position;
        UserProfession user = professionsViewModel.getProfessions().get(position);
        Intent i = new Intent(this, ReferencePicturesActivity.class);
        i.putExtra("userprofession", user);
        startActivityForResult(i, 0);
    }

    @Override
    public void onFinishAddProfession(UserProfession userProfession) {
        professionsViewModel.getProfessions().add(userProfession);
        user.setProfessions(professionsViewModel.getProfessions());
        new ProfessionDAO().saveProfession(userProfession);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    professionsViewModel.getProfessions().set(selectedPosition, (UserProfession) data.getSerializableExtra("userprofession"));
                    professionsViewModel.getProfessionsLD().setValue(professionsViewModel.getProfessions());
                    user.setProfessions(professionsViewModel.getProfessions());
                }
        }
    }
}

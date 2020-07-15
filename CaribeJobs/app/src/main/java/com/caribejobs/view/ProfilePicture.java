package com.caribejobs.view;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.caribejobs.R;
import com.caribejobs.dao.UserDAO;
import com.caribejobs.databinding.ActivityProfilePictureBinding;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.util.SelectImageActivity;
import com.caribejobs.util.blobstorage.BlobStorageSaveProfilePictureTask;
import com.caribejobs.util.blobstorage.BlobStorageTask;
import com.caribejobs.viewmodel.ProfilePictureViewModel;
import com.squareup.picasso.Picasso;

public class ProfilePicture extends SelectImageActivity implements BlobStorageTask.BlobStorageTaskListener {
    ProfilePictureViewModel profilePictureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfilePictureBinding activityProfilePictureBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_picture);
        User user = (User) getIntent().getSerializableExtra("user");
        profilePictureViewModel = new ProfilePictureViewModel(user);
        activityProfilePictureBinding.setViewModel(profilePictureViewModel);
        activityProfilePictureBinding.setLifecycleOwner(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Foto de perfil");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                handleOnBackPress();
            }
        });
        setupProfilePicture();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handleOnBackPress();
    }

    private void handleOnBackPress() {
        Intent i = new Intent();
        i.putExtra("user", profilePictureViewModel.getUser().getValue());
        setResult(RESULT_OK, i);
        finish();
    }

    private void setupProfilePicture() {
        profilePictureViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                ImageView imageView = (ImageView) findViewById(R.id.profilePictureIMGEdit);
                Picasso.get().load(user.getProfilePicture()).into(imageView);
            }
        });
    }

    private void setupProfilePictureByUri() {

        ImageView imageView = (ImageView) findViewById(R.id.profilePictureIMGEdit);
        imageView.setImageURI(uriSelected);

    }


    private void setupProfilePictureByBipMap() {
        new BlobStorageSaveProfilePictureTask(this, profilePictureViewModel.getUser().getValue().getUsername(), bitmapSelected)
                .execute();
        ImageView imageView = (ImageView) findViewById(R.id.profilePictureIMGEdit);
        imageView.setImageBitmap(bitmapSelected);
    }

    @Override
    public void handlePictureURL() {
        if (uriSelected != null) {
            //profilePictureViewModel.getUser().getValue().setProfilePicture();
            setupProfilePictureByUri();
        }
    }

    @Override
    public void handlePictureBitmap() {
        if (bitmapSelected != null) {
            setupProfilePictureByBipMap();
        }
    }

    @Override
    public void takePicture(View view) {
        super.takePicture(view);
    }

    @Override
    public void selectPicture(View view) {
        super.selectPicture(view);
    }

    @Override
    public void afterBlobExecution(String urlres) {
        profilePictureViewModel.getUser().getValue().setProfilePicture(urlres);
        UserLogin.getUserLogged().setProfilePicture(urlres);
        new UserDAO().updateProfilePicture(profilePictureViewModel.getUser().getValue());
    }
}

package com.caribejobs.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.caribejobs.R;
import com.caribejobs.dao.ReferencePictureDAO;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.UserProfession;
import com.caribejobs.util.Constants;
import com.caribejobs.util.SelectImageActivity;
import com.caribejobs.util.blobstorage.BlobStorageSaveReferencePicture;
import com.caribejobs.util.blobstorage.BlobStorageTask;
import com.caribejobs.view.adapters.GridViewImageAdapter;
import com.caribejobs.viewmodel.ProfessionsViewModel;

import java.util.ArrayList;

public class ReferencePicturesActivity extends SelectImageActivity implements BlobStorageTask.BlobStorageTaskListener {
    UserProfession userProfession;
    GridView gridView;
    GridViewImageAdapter adapter;
    int columnWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userProfession = (UserProfession) getIntent().getSerializableExtra("userprofession");
        setContentView(R.layout.activity_reference_pictures);
        gridView = (GridView) findViewById(R.id.photosGridView);
        InitilizeGridLayout();
        adapter = new GridViewImageAdapter(this, userProfession.getReferencePictures(), userProfession, columnWidth);
        adapter.getArrayListMutableLiveData().observe(this, new Observer<ArrayList<ReferencePicture>>() {
            @Override
            public void onChanged(ArrayList<ReferencePicture> referencePictures) {
                gridView.setAdapter(adapter);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Fotos de referencia");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnBackPress();
            }
        });
    }

    @Override
    public void onBackPressed() {
        handleOnBackPress();
    }

    private void handleOnBackPress() {
        Intent i = new Intent();
        i.putExtra("userprofession", userProfession);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                Constants.GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((Constants.getScreenWidth(this) - ((Constants.NUM_OF_COLUMNS + 1) * padding)) / Constants.NUM_OF_COLUMNS);

        gridView.setNumColumns(Constants.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

    private void setupProfilePictureByUri() {

    }

    private void setupProfilePictureByBipMap() {
        new BlobStorageSaveReferencePicture(this, UserLogin.getUserLogged().getUsername(), bitmapSelected, userProfession, userProfession.getReferencePictures().size() + 1)
                .execute();
    }

    @Override
    public void handlePictureURL() {
        if (uriSelected != null) {
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
    public void afterBlobExecution(String urlres) {
        ReferencePicture referencePicture = new ReferencePicture(adapter.getReferencePictures().size() + 1, urlres);
        new ReferencePictureDAO().saveReferencePicture(referencePicture, userProfession.getProfession().getProfessionid());
        adapter.getReferencePictures().add(referencePicture);
        adapter.setReferencePictures(adapter.getReferencePictures());
        userProfession.setReferencePictures(adapter.getReferencePictures());
    }
}

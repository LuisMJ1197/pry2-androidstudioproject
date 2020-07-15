package com.caribejobs.view;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.caribejobs.R;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.UserProfession;
import com.caribejobs.view.adapters.FullScreenImageAdapter;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FullScreenImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        viewPager = findViewById(R.id.pager);
        adapter = new FullScreenImageAdapter(this, ((UserProfession) getIntent().getSerializableExtra("userprofession")).getReferencePictures());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int pos = getIntent().getIntExtra("position", 0);
        viewPager.setCurrentItem(pos);
    }
}

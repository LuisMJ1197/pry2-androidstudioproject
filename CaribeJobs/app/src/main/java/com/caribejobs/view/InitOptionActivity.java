package com.caribejobs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caribejobs.R;

public class InitOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_option);
    }

    public void goLogin(View view) {
        Intent intent = new Intent(this, LoginVActivity.class);
        startActivity(intent);
    }

    public void goGuest(View view) {

    }

    public void goRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }
}

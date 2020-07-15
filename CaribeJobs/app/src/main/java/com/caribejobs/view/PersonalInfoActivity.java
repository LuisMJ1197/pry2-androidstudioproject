package com.caribejobs.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caribejobs.R;
import com.caribejobs.dao.UserDAO;
import com.caribejobs.databinding.ActivityPersonalInfoBinding;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.viewmodel.PersonalInfoViewModel;

public class PersonalInfoActivity extends AppCompatActivity {

    private PersonalInfoViewModel personalInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersonalInfoBinding activityPersonalInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_personal_info);
        personalInfoViewModel = new PersonalInfoViewModel((User) getIntent().getSerializableExtra("user"));
        activityPersonalInfoBinding.setViewModel(personalInfoViewModel);
        activityPersonalInfoBinding.setLifecycleOwner(this);

        personalInfoViewModel.getEmailClick().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    openEditDialog("Digite el correo", personalInfoViewModel.getUser().getValue(), "email");
                }
            }
        });

        personalInfoViewModel.getPhonenumber1Click().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    openEditDialog("Digite el teléfono 1", personalInfoViewModel.getUser().getValue(), "phonenumber1");
                }
            }
        });

        personalInfoViewModel.getPhonenumber2Click().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    openEditDialog("Digite el teléfono 2", personalInfoViewModel.getUser().getValue(), "phonenumber2");
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        this.setTitle("Dirección");
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
        i.putExtra("user", personalInfoViewModel.getUser().getValue());
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    private void openEditDialog(String message, final User user, final String type) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final AlertDialog dialogBox = dialog.create();
        final View view = getLayoutInflater().inflate(R.layout.input_text_layout, null);
        dialogBox.setView(view);
        ((TextView) view.findViewById(R.id.message)).setText(message);

        switch (type) {
            case "email":
                ((EditText) view.findViewById(R.id.inputText)).setText(user.getEmail());
                ((EditText) view.findViewById(R.id.inputText)).setInputType(EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case "phonenumber1":
                ((EditText) view.findViewById(R.id.inputText)).setText(user.getPhonenumber1());
                ((EditText) view.findViewById(R.id.inputText)).setInputType(EditorInfo.TYPE_CLASS_PHONE);
                break;
            case "phonenumber2":
                ((EditText) view.findViewById(R.id.inputText)).setText(user.getPhonenumber2());
                ((EditText) view.findViewById(R.id.inputText)).setInputType(EditorInfo.TYPE_CLASS_PHONE);
                break;
        }

        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox.cancel();
            }
        });

        final EditText editText = (EditText) view.findViewById(R.id.inputText);

        Button acceptButton = (Button) view.findViewById(R.id.ok_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox.dismiss();
                user.setAttribute(type, editText.getText().toString());
                new UserDAO().updateUser(user);
                personalInfoViewModel.getUser().setValue(user);
                UserLogin.setUserLogged(personalInfoViewModel.getUser().getValue());
            }
        });

        editText.requestFocus();
        dialogBox.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialogBox.show();

    }
}

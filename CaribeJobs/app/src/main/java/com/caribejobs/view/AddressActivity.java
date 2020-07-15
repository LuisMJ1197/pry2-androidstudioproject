package com.caribejobs.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caribejobs.R;
import com.caribejobs.dao.AddressDAO;
//import com.caribejobs.databinding.ActivityAddressBinding;
import com.caribejobs.model.Address;
import com.caribejobs.viewmodel.AddressViewModel;

public class AddressActivity extends AppCompatActivity {
    private AddressViewModel addressViewModel;

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
                new AddressDAO().execUpdateUserAddress(addressViewModel.getAddress().getValue());
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/*
        ActivityAddressBinding activityAddressBinding = DataBindingUtil.setContentView(this, R.layout.activity_address);
        final Address address = (Address) getIntent().getSerializableExtra("address");
        addressViewModel = new AddressViewModel(address);
        activityAddressBinding.setViewModel(addressViewModel);
        activityAddressBinding.setLifecycleOwner(this);

        addressViewModel.getAddress().observe(this, new Observer<Address>() {
            @Override
            public void onChanged(Address address1) {
                ((TextView) findViewById(R.id.provinciaTV)).setText(address1.getProvincia());
            }
        });

        addressViewModel.getProvinciaInsert().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean editProvincia) {
                if (editProvincia) {
                    openEditDialog("Digite la provincia", addressViewModel.getAddress().getValue(), "provincia");
                }
            }
        });

        addressViewModel.getCantonInsert().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean editCanton) {
                if (editCanton) {
                    openEditDialog("Digite el canton", addressViewModel.getAddress().getValue(), "canton");
                }
            }
        });

        addressViewModel.getDistritoInsert().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean editDistrito) {
                if (editDistrito) {
                    openEditDialog("Digite el distrito", addressViewModel.getAddress().getValue(), "distrito");
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
        });*/
    }

    @Override
    public void onBackPressed() {
        handleOnBackPress();
    }

    private void handleOnBackPress() {
        Intent i = new Intent();
        i.putExtra("address", addressViewModel.getAddress().getValue());
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    private void openEditDialog(String message, final Address address, final String type) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final AlertDialog dialogBox = dialog.create();
        final View view = getLayoutInflater().inflate(R.layout.input_text_layout, null);
        dialogBox.setView(view);
        ((TextView) view.findViewById(R.id.message)).setText(message);

        switch (type) {
            case "provincia":
                ((EditText) view.findViewById(R.id.inputText)).setText(address.getProvincia());
                break;
            case "canton":
                ((EditText) view.findViewById(R.id.inputText)).setText(address.getCanton());
                break;
            case "distrito":
                ((EditText) view.findViewById(R.id.inputText)).setText(address.getDistrito());
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
                address.setAttribute(type, editText.getText().toString());
                addressViewModel.getAddress().setValue(address.clone());
            }
        });

        editText.requestFocus();
        dialogBox.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialogBox.show();

    }
}

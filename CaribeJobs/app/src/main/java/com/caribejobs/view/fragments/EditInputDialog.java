package com.caribejobs.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.caribejobs.R;
import com.caribejobs.databinding.InputTextLayoutBinding;
import com.caribejobs.viewmodel.EditInputViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditInputDialog extends DialogFragment {
    private EditInputViewModel editInputViewModel;

    public EditInputDialog(String message, MutableLiveData<String> data) {
        editInputViewModel = new EditInputViewModel(message, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.input_text_layout, container);
        /*InputTextLayoutBinding inputTextLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.input_text_layout, container, false);
        inputTextLayoutBinding.setViewModel(editInputViewModel);
        inputTextLayoutBinding.setLifecycleOwner(this);
        View view = inputTextLayoutBinding.getRoot();
        editInputViewModel.getPressCancel().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    getDialog().cancel();
                }
            }
        });

        editInputViewModel.getPressAccept().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    getDialog().dismiss();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.inputText)).requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }
}

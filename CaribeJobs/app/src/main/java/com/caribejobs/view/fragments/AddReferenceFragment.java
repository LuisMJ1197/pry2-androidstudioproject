package com.caribejobs.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.caribejobs.R;
import com.caribejobs.model.Reference;
import com.caribejobs.model.address.Utility;

import org.w3c.dom.Text;

public class AddReferenceFragment extends DialogFragment {

    public interface AddReferenceListener {
        void onFinishAddReference(Reference reference);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_references_layout, container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.addReferenceBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reference reference = new Reference();
                TextView jobname = (TextView) getView().findViewById(R.id.jobname);
                TextView firstname = (TextView) getView().findViewById(R.id.name);
                TextView lastname = (TextView) getView().findViewById(R.id.lastname1);
                TextView lastname2 = (TextView) getView().findViewById(R.id.lastname2);
                TextView phonenumber = (TextView) getView().findViewById(R.id.phonenumber);
                reference.setLastJob(jobname.getText().toString());
                reference.setFirstname(firstname.getText().toString());
                reference.setLastname(lastname.getText().toString());
                reference.setLastname2(lastname2.getText().toString());
                reference.setPhonenumber(phonenumber.getText().toString());
                ((AddReferenceFragment.AddReferenceListener) getActivity()).onFinishAddReference(reference);
                getDialog().dismiss();
            }
        });

    }
}

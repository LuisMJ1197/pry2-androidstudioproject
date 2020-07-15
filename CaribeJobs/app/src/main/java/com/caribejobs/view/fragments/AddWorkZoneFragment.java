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
import com.caribejobs.model.WorkZone;
import com.caribejobs.model.address.Utility;

public class AddWorkZoneFragment extends DialogFragment {

    public interface AddWorkZoneListener {
        void onFinishAddWorkZone(WorkZone workZone);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_work_zone_layout, container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Spinner spinner = (Spinner) view.findViewById(R.id.provinciaSpinner);
        ArrayAdapter<String> staticAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, Utility.getInstance().getProvinciasName());
        spinner.setAdapter(staticAdapter);
        final View parentView = view;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final Spinner spinner2 = (Spinner) parentView.findViewById(R.id.cantonSpinner);
                ArrayAdapter<String> staticAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, Utility.provincias.get(i).getCantonesNames());
                spinner2.setAdapter(staticAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = (Button) view.findViewById(R.id.addWZBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner1 = (Spinner) getView().findViewById(R.id.provinciaSpinner);
                Spinner spinner2 = (Spinner) getView().findViewById(R.id.cantonSpinner);
                WorkZone workZone = new WorkZone();
                workZone.setProvincia((String) spinner1.getSelectedItem());
                workZone.setCanton((String) spinner2.getSelectedItem());
                ((AddWorkZoneFragment.AddWorkZoneListener) getActivity()).onFinishAddWorkZone(workZone);
                getDialog().dismiss();
            }
        });

    }
}

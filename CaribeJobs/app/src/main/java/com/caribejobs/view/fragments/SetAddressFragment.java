package com.caribejobs.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.caribejobs.R;
import com.caribejobs.model.Address;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.address.Canton;
import com.caribejobs.model.address.Utility;

public class SetAddressFragment extends DialogFragment {
    private Address address;

    public SetAddressFragment() {
        address = UserLogin.getUserLogged().getAddress();
    }


    public interface SetAddressListener {
        void onFinishSetAddress(Address address);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_address, container);
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
                changeDistritoSpinner(parentView, Utility.provincias.get(i).getCantones().get(0));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final Spinner spinner3 = (Spinner) parentView.findViewById(R.id.cantonSpinner);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeDistritoSpinner(parentView, Utility.provincias.get(spinner.getSelectedItemPosition()).getCantones().get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        System.out.println(address);
        Spinner spinner1 = (Spinner) getView().findViewById(R.id.provinciaSpinner);
        Spinner spinner2 = (Spinner) getView().findViewById(R.id.cantonSpinner);
        Spinner spinner4 = (Spinner) getView().findViewById(R.id.distritoSpinner);
        int selectedProvincia = 0;
        int selectedCanton = 0;
        int selectedDistrito = 0;
        for (int i = 0; i < Utility.getInstance().getProvinciasName().size(); i++) {
            if (Utility.getInstance().getProvinciasName().get(i).equals(address.getProvincia())) {
                for (int j = 0; j < Utility.provincias.get(i).getCantonesNames().size(); j++) {
                    if (Utility.provincias.get(i).getCantonesNames().get(j).equals(address.getCanton())) {
                        for (int k = 0; k < Utility.provincias.get(i).getCantones().get(j).getDistritos().size(); k++) {
                            if (Utility.provincias.get(i).getCantones().get(j).getDistritos().get(k).equals(address.getCanton())) {
                                selectedProvincia = i;
                                selectedCanton = j;
                                selectedDistrito = k;
                            }
                        }
                    }
                }
            }
        }

        spinner1.setSelection(selectedProvincia);
        spinner2.setSelection(selectedCanton);
        spinner4.setSelection(selectedDistrito);

        Button button = (Button) view.findViewById(R.id.addWZBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner1 = (Spinner) getView().findViewById(R.id.provinciaSpinner);
                Spinner spinner2 = (Spinner) getView().findViewById(R.id.cantonSpinner);
                Spinner spinner3 = (Spinner) getView().findViewById(R.id.distritoSpinner);
                Address address = new Address();
                address.setProvincia((String) spinner1.getSelectedItem());
                address.setCanton((String) spinner2.getSelectedItem());
                address.setDistrito((String) spinner3.getSelectedItem());
                ((SetAddressListener) getActivity()).onFinishSetAddress(address);
                getDialog().dismiss();
            }
        });

    }

    private void changeDistritoSpinner(View view, Canton canton) {
        Spinner distritoSpinner = (Spinner) view.findViewById(R.id.distritoSpinner);
        ArrayAdapter<String> staticAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, canton.getDistritos());
        distritoSpinner.setAdapter(staticAdapter2);
    }
}

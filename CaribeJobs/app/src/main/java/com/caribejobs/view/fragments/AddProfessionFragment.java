package com.caribejobs.view.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.caribejobs.R;
import com.caribejobs.databinding.ActivityProfessionsBinding;
import com.caribejobs.model.Profession;
import com.caribejobs.model.User;
import com.caribejobs.model.UserProfession;
import com.caribejobs.viewmodel.ProfessionsViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddProfessionFragment extends DialogFragment {
    private ArrayList<Profession> professionsList = new ArrayList<>();

    public interface AddProfessionListener {
        void onFinishAddProfession(UserProfession userProfession);
    }

    public AddProfessionFragment() {
        professionsList = Profession.professionsUtil;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_profession_layout, container);
    }

    public ArrayList<String> getProfesionsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Profession profession: professionsList) {
            names.add(profession.getProfessionname());
        }
        return names;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Spinner spinner = (Spinner) view.findViewById(R.id.professionsSpinner);
        ArrayAdapter<String> staticAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, getProfesionsNames());
        spinner.setAdapter(staticAdapter);

        Button button = (Button) view.findViewById(R.id.addProfessionBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner1 = (Spinner) getView().findViewById(R.id.professionsSpinner);
                TextView experience = (TextView) getView().findViewById(R.id.experienceyears);
                TextView details = (TextView) getView().findViewById(R.id.details);
                TextView costperhour = (TextView) getView().findViewById(R.id.costperhour);
                UserProfession userProfession = new UserProfession();
                userProfession.setCostperhour(Double.parseDouble(costperhour.getText().toString()));
                userProfession.setDetails(details.getText().toString());
                userProfession.setExperienceyears(Integer.parseInt(experience.getText().toString()));
                userProfession.setProfession(professionsList.get(spinner1.getSelectedItemPosition()));
                ((AddProfessionListener) getActivity()).onFinishAddProfession(userProfession);
                getDialog().dismiss();
            }
        });
    }

}

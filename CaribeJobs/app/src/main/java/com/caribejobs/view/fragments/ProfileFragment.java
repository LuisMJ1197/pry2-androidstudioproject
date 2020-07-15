package com.caribejobs.view.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caribejobs.R;
import com.caribejobs.dao.UserDAO;
import com.caribejobs.databinding.FragmentProfileBinding;
import com.caribejobs.model.Address;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.view.AddressActivity;
import com.caribejobs.view.PersonalInfoActivity;
import com.caribejobs.view.ProfessionsActivity;
import com.caribejobs.view.ProfilePicture;
import com.caribejobs.view.ReferencesActivity;
import com.caribejobs.view.ScheduleActivity;
import com.caribejobs.view.WorkZonesActivity;
import com.caribejobs.viewmodel.LoggedHomeActivityViewModel;
import com.caribejobs.viewmodel.ProfileViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {
    private static final int PROFILE_PICTURE = 3;
    private final int SCHEDULE_RESULT_CODE = 5;
    private final int REFERENCES_RESULT_CODE = 7;
    private final int WORK_ZONE_RESULT_CODE = 6;
    private  final int PERSONAL_INFO_RESULT_CODE = 1;
    private final int ADDRESS_RESULT_CODE = 2;
    private final int PROFESSIONS_RESULT_CODE = 4;

    private LoggedHomeActivityViewModel viewModel;
    private ProfileFragment context;
    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(LoggedHomeActivityViewModel.class);
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                ImageView imageView = (ImageView) getActivity().findViewById(R.id.profilePictureIMG);
                Picasso.get().load(user.getProfilePicture()).into(imageView);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) getActivity().findViewById(R.id.usernameLB)).setText(viewModel.getUser().getValue().getUsername());
        LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.profilePictureLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ProfilePicture.class);
                i.putExtra("user", viewModel.getUser().getValue());
                startActivityForResult(i, PROFILE_PICTURE);
            }
        });

        linearLayout = (LinearLayout) getActivity().findViewById(R.id.personalInfoLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PersonalInfoActivity.class);
                i.putExtra("user", viewModel.getUser().getValue());
                startActivityForResult(i, PERSONAL_INFO_RESULT_CODE);
            }
        });

        linearLayout = (LinearLayout) getActivity().findViewById(R.id.addressLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                SetAddressFragment setAddressFragment = new SetAddressFragment();
                setAddressFragment.show(fm, "set_address");
            }
        });

        linearLayout = (LinearLayout) getActivity().findViewById(R.id.professionsLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ProfessionsActivity.class);
                i.putExtra("user", viewModel.getUser().getValue());
                startActivityForResult(i, PROFESSIONS_RESULT_CODE);
            }
        });

        linearLayout = (LinearLayout) getActivity().findViewById(R.id.scheduleLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ScheduleActivity.class);
                i.putExtra("user", viewModel.getUser().getValue());
                startActivityForResult(i, SCHEDULE_RESULT_CODE);
            }
        });

        linearLayout = (LinearLayout) getActivity().findViewById(R.id.workZonesLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), WorkZonesActivity.class);
                i.putExtra("user", viewModel.getUser().getValue());
                startActivityForResult(i, WORK_ZONE_RESULT_CODE);
            }
        });

        linearLayout = (LinearLayout) getActivity().findViewById(R.id.referencesLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ReferencesActivity.class);
                i.putExtra("user", viewModel.getUser().getValue());
                startActivityForResult(i, REFERENCES_RESULT_CODE);
            }
        });
    }

    public LoggedHomeActivityViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        User user;
        switch (requestCode) {
            case PROFILE_PICTURE:
            case PERSONAL_INFO_RESULT_CODE:
            case PROFESSIONS_RESULT_CODE:
            case REFERENCES_RESULT_CODE:
            case WORK_ZONE_RESULT_CODE:
            case SCHEDULE_RESULT_CODE:
                user = (User) data.getSerializableExtra("user");
                viewModel.getUser().setValue(user);
                UserLogin.setUserLogged(user);
                break;
            case ADDRESS_RESULT_CODE:
                Address address = (Address) data.getSerializableExtra("address");
                this.viewModel.getUser().getValue().setAddress(address.getProvincia(), address.getCanton(), address.getDistrito());
                break;
        }
    }

}

package com.caribejobs.view;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.caribejobs.R;
import com.caribejobs.dao.AddressesUtilDAO;
import com.caribejobs.dao.ProfessionDAO;
import com.caribejobs.dao.UserDAO;
import com.caribejobs.databinding.ActivityLoggedHomeBinding;
import com.caribejobs.model.Address;
import com.caribejobs.model.User;
import com.caribejobs.util.CustomActionBarActivity;
import com.caribejobs.view.fragments.SetAddressFragment;
import com.caribejobs.viewmodel.LoggedHomeActivityViewModel;
import com.caribejobs.view.adapters.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoggedHomeActivity extends CustomActionBarActivity implements SetAddressFragment.SetAddressListener{
    LoggedHomeActivityViewModel loggedHomeActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AddressesUtilDAO().loadAddresses();
        new ProfessionDAO().loadProfessions();

        ActivityLoggedHomeBinding activityLoggedHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_logged_home);
        loggedHomeActivityViewModel = new LoggedHomeActivityViewModel();
        activityLoggedHomeBinding.setViewModel(loggedHomeActivityViewModel);
        activityLoggedHomeBinding.setLifecycleOwner(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.content);
        PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout layout = (TabLayout) findViewById(R.id.navTab);
        layout.setupWithViewPager(viewPager);

    }

    @Override
    public void onFinishSetAddress(Address address) {
        loggedHomeActivityViewModel.getUser().getValue().setAddress(address.getProvincia(), address.getCanton(), address.getDistrito());
        User.getUserLogged().setAddress(address);
        new UserDAO().updateAddress(User.getUserLogged());
    }
}

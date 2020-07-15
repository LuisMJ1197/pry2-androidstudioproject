package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.UserProfession;

import java.util.ArrayList;

public class ProfessionsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<UserProfession>> professionsLD = new MutableLiveData<>();
    private ArrayList<UserProfession> professions;
    private MutableLiveData<UserProfession> addUser = new MutableLiveData<>();

    public ProfessionsViewModel(ArrayList<UserProfession> professions) {
        this.professions = professions;
        this.professionsLD.setValue(professions);
    }

    public MutableLiveData<ArrayList<UserProfession>> getProfessionsLD() {
        return professionsLD;
    }

    public ArrayList<UserProfession> getProfessions() {
        return professions;
    }
}

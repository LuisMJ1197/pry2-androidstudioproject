package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.Reference;

import java.util.ArrayList;

public class ReferencesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Reference>> referenceMutableLiveData = new MutableLiveData<>();
    private ArrayList<Reference> references;

    public ReferencesViewModel( ArrayList<Reference> references) {
        this.referenceMutableLiveData.setValue(references);
        this.references = references;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }

    public MutableLiveData<ArrayList<Reference>> getReferenceMutableLiveData() {
        return referenceMutableLiveData;
    }
}

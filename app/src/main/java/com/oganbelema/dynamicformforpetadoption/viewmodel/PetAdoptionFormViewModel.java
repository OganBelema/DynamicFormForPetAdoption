package com.oganbelema.dynamicformforpetadoption.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.repository.PetAdoptionFormRepository;


public class PetAdoptionFormViewModel extends ViewModel {

    private final LiveData<PetAdoptionForm> mPetAdoptionFormLiveData;

    public PetAdoptionFormViewModel(PetAdoptionFormRepository petAdoptionFormRepository) {
        mPetAdoptionFormLiveData = petAdoptionFormRepository.getPetAdoptionFormMutableLiveData();
    }

    public LiveData<PetAdoptionForm> getPetAdoptionFormLiveData() {
        return mPetAdoptionFormLiveData;
    }
}

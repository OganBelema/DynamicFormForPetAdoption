package com.oganbelema.dynamicformforpetadoption.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.repository.PetAdoptionFormRepository;


public class PetAdoptionFormViewModel extends ViewModel {

    private final MutableLiveData<PetAdoptionForm> mPetAdoptionFormMutableLiveData;

    public PetAdoptionFormViewModel(PetAdoptionFormRepository petAdoptionFormRepository) {
        mPetAdoptionFormMutableLiveData = petAdoptionFormRepository.getPetAdoptionFormMutableLiveData();
    }

    public MutableLiveData<PetAdoptionForm> getPetAdoptionFormMutableLiveData() {
        return mPetAdoptionFormMutableLiveData;
    }
}

package com.oganbelema.dynamicformforpetadoption.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.oganbelema.dynamicformforpetadoption.repository.PetAdoptionFormRepository;

public class PetAdoptionFormViewModelFactory implements ViewModelProvider.Factory {

    private final PetAdoptionFormRepository mPetAdoptionFormRepository;

    public PetAdoptionFormViewModelFactory(Context context) {
        mPetAdoptionFormRepository = new PetAdoptionFormRepository(context);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PetAdoptionFormViewModel(mPetAdoptionFormRepository);
    }
}

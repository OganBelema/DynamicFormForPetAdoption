package com.oganbelema.dynamicformforpetadoption.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.oganbelema.dynamicformforpetadoption.R;
import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.util.JSONResourceReader;


public class PetAdoptionFormRepository {

    private final Context mContext;
    private final MutableLiveData<PetAdoptionForm> mPetAdoptionFormMutableLiveData;

    public PetAdoptionFormRepository(Context context) {
        mContext = context;
        mPetAdoptionFormMutableLiveData = fetchPetAdoptionFormData();
    }

    private MutableLiveData<PetAdoptionForm> fetchPetAdoptionFormData(){
        JSONResourceReader reader = new JSONResourceReader(mContext.getResources(), R.raw.pet_adoption);
        PetAdoptionForm petAdoptionForm = reader.constructUsingGson(PetAdoptionForm.class);
        MutableLiveData<PetAdoptionForm> petAdoptionFormMutableLiveData = new MutableLiveData<>();
        petAdoptionFormMutableLiveData.postValue(petAdoptionForm);
        return petAdoptionFormMutableLiveData;
    }

    public MutableLiveData<PetAdoptionForm> getPetAdoptionFormMutableLiveData() {
        return mPetAdoptionFormMutableLiveData;
    }
}

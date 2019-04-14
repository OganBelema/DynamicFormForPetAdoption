package com.oganbelema.dynamicformforpetadoption;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModel;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PetAdoptionFormViewModel petAdoptionFormViewModel = ViewModelProviders.of(this,
                new PetAdoptionFormViewModelFactory(this))
                .get(PetAdoptionFormViewModel.class);

        petAdoptionFormViewModel.getPetAdoptionFormMutableLiveData().observe(this,
                new Observer<PetAdoptionForm>() {
            @Override
            public void onChanged(@Nullable PetAdoptionForm petAdoptionForm) {
                if (petAdoptionForm != null){

                }
            }
        });

    }
}

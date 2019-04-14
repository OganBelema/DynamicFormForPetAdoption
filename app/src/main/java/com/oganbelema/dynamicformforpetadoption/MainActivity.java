package com.oganbelema.dynamicformforpetadoption;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.view.MyFormNameTextView;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModel;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PetAdoptionFormViewModel petAdoptionFormViewModel = ViewModelProviders.of(this,
                new PetAdoptionFormViewModelFactory(this))
                .get(PetAdoptionFormViewModel.class);

        petAdoptionFormViewModel.getPetAdoptionFormMutableLiveData().observe(this,
                new Observer<PetAdoptionForm>() {
            @Override
            public void onChanged(@Nullable PetAdoptionForm petAdoptionForm) {
                if (petAdoptionForm != null){
                    populateView(petAdoptionForm);
                }
            }
        });

    }

    private void populateView(PetAdoptionForm petAdoptionForm) {

        //The parent view
        LinearLayout baseView = new LinearLayout(this);
        baseView.setPadding(24, 24, 24, 24);
        baseView.setOrientation(LinearLayout.VERTICAL);

        //The text view to display the form name
        if (petAdoptionForm.getName() != null){
            MyFormNameTextView formNameTextView = new MyFormNameTextView(this);
            formNameTextView.setText(petAdoptionForm.getName());
            baseView.addView(formNameTextView);
        }


        //populates the activity with created view
        setContentView(baseView);
    }
}

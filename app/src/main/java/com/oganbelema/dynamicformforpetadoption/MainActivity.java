package com.oganbelema.dynamicformforpetadoption;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.model.Page;
import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.view.MyFormNameTextView;
import com.oganbelema.dynamicformforpetadoption.view.MyPageLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.adapter.MyViewPagerAdapter;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModel;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewPagerAdapter mMyViewPagerAdapter;

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

        //Check that form has pages
        if (petAdoptionForm.getPages() != null){

            //creates viewPager and tablayout 
            ViewPager viewPager = new ViewPager(this);
            TabLayout tabLayout = new TabLayout(this);
            List<View> views = new ArrayList<>();

            //adds views which we want to set as pages
            mMyViewPagerAdapter = new MyViewPagerAdapter(views, this);
            viewPager.setAdapter(mMyViewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);

            for (Page page: petAdoptionForm.getPages()){

                //So the user can scroll through the view, this will serve as the base view for'
                //pages
                NestedScrollView nestedScrollView = new NestedScrollView(this);

                //creates view for the page
                LinearLayout pageView = new LinearLayout(this);
                pageView.setPadding(24, 24, 24, 24);
                pageView.setOrientation(LinearLayout.VERTICAL);


                //create label text view for the page to show the page number
                MyPageLabelTextView pageLabelTextView = new MyPageLabelTextView(this);
                pageLabelTextView.setText(page.getLabel());
                pageView.addView(pageLabelTextView);


                views.add(nestedScrollView);
                mMyViewPagerAdapter.notifyDataSetChanged();

                //add the page view to the nested scollview
                nestedScrollView.addView(pageView);
            }

            //add the viewpager and the tablayout to the base/parent view
            baseView.addView(tabLayout);
            baseView.addView(viewPager);
        }

        //populates the activity with created view
        setContentView(baseView);
    }
}

package com.oganbelema.dynamicformforpetadoption;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.oganbelema.dynamicformforpetadoption.model.Element;
import com.oganbelema.dynamicformforpetadoption.model.Page;
import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.model.Section;
import com.oganbelema.dynamicformforpetadoption.view.MyCustomButton;
import com.oganbelema.dynamicformforpetadoption.view.MyEditText;
import com.oganbelema.dynamicformforpetadoption.view.MyElementLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.MyFormNameTextView;
import com.oganbelema.dynamicformforpetadoption.view.MyPageLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.MySectionLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.adapter.MyViewPagerAdapter;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModel;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModelFactory;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

                //So the user can scroll through the view, this will serve as the base view for
                //pages
                NestedScrollView nestedScrollView = new NestedScrollView(this);

                //creates view for the page
                LinearLayout pageView = new LinearLayout(this);
                pageView.setPadding(24, 24, 24, 24);
                pageView.setOrientation(LinearLayout.VERTICAL);

                //check that page has sections
                if (page.getSections() != null){

                    //create section views
                    for (Section section: page.getSections()) {
                        LinearLayout sectionView = new LinearLayout(this);
                        sectionView.setOrientation(LinearLayout.VERTICAL);

                        //create section label textview
                        MySectionLabelTextView sectionLabelTextView =
                                new MySectionLabelTextView(this);
                        sectionLabelTextView.setText(section.getLabel());
                        sectionView.addView(sectionLabelTextView);
                        pageView.addView(sectionView);

                        if (section.getElements() != null){

                            //create view for elements
                            for (Element element: section.getElements()){

                                switch (element.getType()){
                                    case "embeddedphoto":
                                        //create imageview element view
                                        LinearLayout.LayoutParams imageViewParameter =
                                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
                                        imageViewParameter.gravity = Gravity.CENTER;
                                        ImageView imageView = new ImageView(this);
                                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                        imageView.setContentDescription(element.getLabel());
                                        imageView.setLayoutParams(imageViewParameter);
                                        Picasso.get().load(element.getFile()).into(imageView);
                                        sectionView.addView(imageView);
                                        break;

                                    case "text":
                                        //create text element label view
                                        MyElementLabelTextView elementLabelView =
                                                new MyElementLabelTextView(this);
                                        elementLabelView.setText(element.getLabel());
                                        sectionView.addView(elementLabelView);

                                        //create editText view for element
                                        MyEditText elementEditTextView = new MyEditText(this);
                                        elementEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                                        sectionView.addView(elementEditTextView);
                                        break;

                                    case "formattednumeric":
                                        //create text element label view
                                        MyElementLabelTextView numericElementLabelView =
                                                new MyElementLabelTextView(this);
                                        numericElementLabelView.setText(element.getLabel());
                                        sectionView.addView(numericElementLabelView);

                                        MyEditText numericElementEditTextView = new MyEditText(this);
                                        numericElementEditTextView.setInputType(InputType.TYPE_CLASS_NUMBER);
                                        sectionView.addView(numericElementEditTextView);

                                        break;

                                    case "datetime":
                                        //create text element label view
                                        MyElementLabelTextView dateElementLabelView =
                                                new MyElementLabelTextView(this);
                                        dateElementLabelView.setText(element.getLabel());
                                        dateElementLabelView.setTextSize(18f);
                                        sectionView.addView(dateElementLabelView);

                                        //create edit text for date selection
                                        final MyEditText dateElementEditTextView = new MyEditText(this);
                                        dateElementEditTextView.setInputType(InputType.TYPE_NULL);
                                        dateElementEditTextView.setFocusable(false);
                                        dateElementEditTextView.setClickable(true);
                                        dateElementEditTextView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                createDatePickerDialog(MainActivity.this, dateElementEditTextView)
                                                        .show();
                                            }
                                        });
                                        sectionView.addView(dateElementEditTextView);
                                        break;

                                    case "yesno":
                                        //label for boolean element
                                        MyElementLabelTextView yesNoElementLabelView =
                                                new MyElementLabelTextView(this);
                                        yesNoElementLabelView.setText(element.getLabel());
                                        yesNoElementLabelView.setTextSize(18f);
                                        sectionView.addView(yesNoElementLabelView);

                                        //create radio buttons for boolean options
                                        RadioGroup radioGroup = new RadioGroup(this);
                                        radioGroup.setOrientation(RadioGroup.HORIZONTAL);

                                        RadioButton yesRadioButton = new RadioButton(this);
                                        yesRadioButton.setText(R.string.yes);

                                        RadioButton noRadioButton = new RadioButton(this);
                                        noRadioButton.setText(R.string.no);

                                        radioGroup.addView(yesRadioButton);
                                        radioGroup.addView(noRadioButton);
                                        sectionView.addView(radioGroup);
                                        break;
                                }
                            }
                        }

                        //check if it is last page of form
                        //if it is create a submit button and add to view
                        if (petAdoptionForm.getPages().indexOf(page) == petAdoptionForm.getPages().size() - 1){
                            MyCustomButton submitButton = new MyCustomButton(this);
                            submitButton.setText(R.string.submit);

                            //add button to the page view
                            pageView.addView(submitButton);
                        }


                    }
                }

                //create label text view for the page to show the page number
                MyPageLabelTextView pageLabelTextView = new MyPageLabelTextView(this);
                pageLabelTextView.setText(page.getLabel());
                pageView.addView(pageLabelTextView);


                //add the nested scroll view to the list of views to bel displayed by the viewpager
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

    private DatePickerDialog createDatePickerDialog(Context context, final EditText editText){
        Calendar myCalendar = Calendar.getInstance();

        return new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                editText.setText(sdf.format(newDate.getTime()));
            }

        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
    }
}

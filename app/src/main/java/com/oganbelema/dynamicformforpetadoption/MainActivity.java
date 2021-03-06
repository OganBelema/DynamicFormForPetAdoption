package com.oganbelema.dynamicformforpetadoption;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.oganbelema.dynamicformforpetadoption.model.Element;
import com.oganbelema.dynamicformforpetadoption.model.Page;
import com.oganbelema.dynamicformforpetadoption.model.PetAdoptionForm;
import com.oganbelema.dynamicformforpetadoption.model.Rule;
import com.oganbelema.dynamicformforpetadoption.model.Section;
import com.oganbelema.dynamicformforpetadoption.view.DogAnimationViewForLoading;
import com.oganbelema.dynamicformforpetadoption.view.MyCustomButton;
import com.oganbelema.dynamicformforpetadoption.view.MyElementLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.MyFormNameTextView;
import com.oganbelema.dynamicformforpetadoption.view.MyLabelTextInputLayout;
import com.oganbelema.dynamicformforpetadoption.view.MyPageLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.MySectionLabelTextView;
import com.oganbelema.dynamicformforpetadoption.view.MyTextInputEditText;
import com.oganbelema.dynamicformforpetadoption.view.adapter.MyViewPagerAdapter;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModel;
import com.oganbelema.dynamicformforpetadoption.viewmodel.PetAdoptionFormViewModelFactory;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private DogAnimationViewForLoading mDogAnimationViewForLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDogAnimationViewForLoading = new DogAnimationViewForLoading(LayoutInflater.from(this),
                null);

        PetAdoptionFormViewModel petAdoptionFormViewModel = ViewModelProviders.of(this,
                new PetAdoptionFormViewModelFactory(this))
                .get(PetAdoptionFormViewModel.class);

        petAdoptionFormViewModel.getPetAdoptionFormLiveData().observe(this,
                new Observer<PetAdoptionForm>() {
                    @Override
                    public void onChanged(@Nullable PetAdoptionForm petAdoptionForm) {
                        if (petAdoptionForm != null) {
                            generateView(petAdoptionForm);
                        }
                    }
                });

        setContentView(mDogAnimationViewForLoading.getRootView());
    }

    private void generateView(final PetAdoptionForm petAdoptionForm) {

        //The parent view
        final LinearLayout baseView = new LinearLayout(this);
        baseView.setPadding(24, 24, 24, 24);
        baseView.setOrientation(LinearLayout.VERTICAL);

        //The text view to display the form name
        if (petAdoptionForm.getName() != null) {
            MyFormNameTextView formNameTextView = new MyFormNameTextView(this);
            formNameTextView.setText(petAdoptionForm.getName());
            baseView.addView(formNameTextView);
        }

        //Check that form has pages
        if (petAdoptionForm.getPages() != null) {

            //creates viewPager and tablayout
            final ViewPager viewPager = new ViewPager(this);
            TabLayout tabLayout = new TabLayout(this);
            List<View> views = new ArrayList<>();

            //adds the list of page views to the viewpager
            final MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(views, this);
            viewPager.setAdapter(myViewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);

            for (Page page : petAdoptionForm.getPages()) {

                //So the user can scroll through the view, this will serve as the base view for
                //pages
                final NestedScrollView nestedScrollView = new NestedScrollView(this);

                //creates view for the page
                LinearLayout pageView = new LinearLayout(this);
                pageView.setPadding(24, 24, 24, 24);
                pageView.setOrientation(LinearLayout.VERTICAL);

                //check that page has sections
                if (page.getSections() != null) {

                    //create section views
                    for (final Section section : page.getSections()) {
                        LinearLayout sectionView = new LinearLayout(this);
                        sectionView.setOrientation(LinearLayout.VERTICAL);

                        //create section label textview
                        MySectionLabelTextView sectionLabelTextView =
                                new MySectionLabelTextView(this);
                        sectionLabelTextView.setText(section.getLabel());
                        sectionView.addView(sectionLabelTextView);
                        pageView.addView(sectionView);

                        if (section.getElements() != null) {

                            //create view for elements
                            for (Element element : section.getElements()) {

                                switch (element.getType()) {
                                    case "embeddedphoto":
                                        //create imageview element view
                                        LinearLayout.LayoutParams imageViewParameter =
                                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
                                        imageViewParameter.gravity = Gravity.CENTER;
                                        ImageView imageView = new ImageView(this);

                                        //add unique id
                                        imageView.setTag(element.getUniqueId());

                                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                        imageView.setContentDescription(element.getLabel());
                                        imageView.setLayoutParams(imageViewParameter);
                                        Picasso.get().load(element.getFile()).into(imageView);

                                        sectionView.addView(imageView);
                                        break;

                                    case "text":
                                        //create text element label view
                                        MyLabelTextInputLayout elementLabelView =
                                                new MyLabelTextInputLayout(this);
                                        sectionView.addView(elementLabelView);

                                        //create editText view for element
                                        MyTextInputEditText elementEditTextView = new MyTextInputEditText(this);
                                        //add unique id
                                        elementEditTextView.setTag(element.getUniqueId());
                                        //set label as hint
                                        elementEditTextView.setHint(element.getLabel());

                                        elementEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                                        elementLabelView.addView(elementEditTextView);
                                        break;

                                    case "formattednumeric":
                                        //create text element label view
                                        MyLabelTextInputLayout numericElementLabelView =
                                                new MyLabelTextInputLayout(this);
                                        sectionView.addView(numericElementLabelView);

                                        MyTextInputEditText numericElementEditTextView =
                                                new MyTextInputEditText(this);
                                        //add unique id
                                        numericElementEditTextView.setTag(element.getUniqueId());

                                        //set label
                                        numericElementEditTextView.setHint(element.getLabel());
                                        //format edit text
                                        formatEditText(numericElementEditTextView);
                                        numericElementLabelView.addView(numericElementEditTextView);

                                        break;

                                    case "datetime":
                                        //create text element label view
                                        MyLabelTextInputLayout dateElementLabelView =
                                                new MyLabelTextInputLayout(this);
                                        sectionView.addView(dateElementLabelView);

                                        //create edit text for date selection
                                        final MyTextInputEditText dateElementEditTextView =
                                                new MyTextInputEditText(this);
                                        //add unique id
                                        dateElementEditTextView.setTag(element.getUniqueId());
                                        //set label
                                        dateElementEditTextView.setHint(element.getLabel());

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
                                        dateElementLabelView.addView(dateElementEditTextView);
                                        break;

                                    case "yesno":
                                        //label for boolean element
                                        MyElementLabelTextView yesNoElementLabelView =
                                                new MyElementLabelTextView(this);
                                        yesNoElementLabelView.setText(element.getLabel());
                                        yesNoElementLabelView.setTextSize(18f);
                                        sectionView.addView(yesNoElementLabelView);

                                        sectionView.addView(createSpinnerForYesNoElement(sectionView,
                                                element));
                                        break;
                                }
                            }
                        }

                        //check if it is last page of form
                        //if it is create a submit button and add to view
                        if (petAdoptionForm.getPages().indexOf(page) == petAdoptionForm.getPages().size() - 1) {
                            MyCustomButton submitButton = new MyCustomButton(this);
                            submitButton.setText(R.string.submit);

                            submitButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    validateForm(myViewPagerAdapter, viewPager, petAdoptionForm);
                                }
                            });

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
                myViewPagerAdapter.notifyDataSetChanged();

                //add the page view to the nested scollview
                nestedScrollView.addView(pageView);
            }

            //add the viewpager and the tablayout to the base/parent view
            baseView.addView(tabLayout);
            baseView.addView(viewPager);
        }

        //populates the activity with generated view
        setContentView(baseView);
    }

    private void validateForm(MyViewPagerAdapter viewPagerAdapter, ViewPager viewpager, PetAdoptionForm petAdoptionForm) {

        //check that population form, viewpager and viewpager are not null
        if (petAdoptionForm != null && viewpager != null && viewPagerAdapter != null) {

            //check that pages property of petAdoptionForm isn't null
            if (petAdoptionForm.getPages() != null) {
                //loop through the list of pages in the pet adoption form
                for (int i = 0; i < petAdoptionForm.getPages().size(); i++) {
                    //set the current page in the loop as the current view
                    viewpager.setCurrentItem(i);

                    //get the current page's view from the viewpager adapter
                    View pageView = viewPagerAdapter.getView(i);

                    //check that the page view isn't null
                    if (pageView != null) {
                        //get the current page object from the form
                        Page page = petAdoptionForm.getPages().get(i);

                        //check that the sections property of the page isn't null
                        if (page.getSections() != null) {

                            //loop through the sections in the page
                            for (Section section : page.getSections()) {

                                //check that the element property of the current section isn't null
                                if (section.getElements() != null) {

                                    //loop through the elements in the current section
                                    for (Element element : section.getElements()) {

                                        //get the element's view from the pageView by it's tag
                                        View elementView = pageView.findViewWithTag(element.getUniqueId());

                                        //check that the isMandatory property of the element isn't null
                                        if (element.getIsMandatory() != null) {

                                            //check if the element is mandatory
                                            if (element.getIsMandatory()) {

                                                //since the element is mandatory, check for the element type
                                                //so as to determine the view type and validate that the user
                                                //provided input
                                                switch (element.getType()) {
                                                    //since these element types have the same view I grouped them together
                                                    case "text":
                                                    case "formattednumeric":
                                                    case "datetime":
                                                        try {
                                                            String text = Objects.requireNonNull(((TextInputEditText)
                                                                    elementView).getText()).toString();
                                                            if (TextUtils.isEmpty(text)) {
                                                                ((TextInputEditText) elementView).setError(getString(R.string.required_field));
                                                                elementView.requestFocus();
                                                                return;
                                                            }
                                                        } catch (Exception e) {
                                                            Log.e(TAG, "Error in validating text input element: " +
                                                                    e.getLocalizedMessage());
                                                        }
                                                        break;

                                                    //check that the user selected a valid dropdown option
                                                    case "yesno":
                                                        try {
                                                            String userSelection = (String)
                                                                    ((Spinner) elementView).getSelectedItem();
                                                            if (userSelection.equals(getString(R.string.select_option))){
                                                                elementView.requestFocus();
                                                                Toast.makeText(this, R.string.please_select_option, Toast.LENGTH_LONG).show();
                                                                return;
                                                            }
                                                        } catch (Exception e){
                                                            e.printStackTrace();
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private DatePickerDialog createDatePickerDialog(Context context, final EditText editText) {
        Calendar myCalendar = Calendar.getInstance();

        return new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                editText.setText(sdf.format(newDate.getTime()));
            }

        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private Spinner createSpinnerForYesNoElement(final View parentView, final Element element) {
        //get element rules
        final List<Rule> rules = element.getRules();

        final Spinner spinner = new Spinner(this);
        //set unique id
        spinner.setTag(element.getUniqueId());
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.yesno));
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = spinnerAdapter.getItem(position);

                if (rules != null && rules.size() > 0) {

                    for (Rule rule : rules) {
                        List<String> targets = rule.getTargets();

                        if (selectedOption != null) {
                            if (selectedOption.equals(rule.getValue())) {
                                for (String target : targets) {
                                    View targetView = parentView.findViewWithTag(target);

                                    if (targetView != null) {

                                        //show the target view's label showing parent
                                        try {
                                            ((TextInputLayout) targetView.getParent().getParent())
                                                    .setVisibility(View.VISIBLE);
                                        } catch (Exception e) {
                                            Log.e(TAG, "Error in showing target view parent: "
                                                    + e.getLocalizedMessage());
                                        }

                                        targetView.setVisibility(View.VISIBLE);
                                    }
                                }
                            } else {
                                for (String target : targets) {
                                    View targetView = parentView.findViewWithTag(target);

                                    if (targetView != null) {

                                        //hide the target view's label showing parent
                                        try {
                                            ((TextInputLayout) targetView.getParent().getParent())
                                                    .setVisibility(View.GONE);
                                        } catch (Exception e) {
                                            Log.e(TAG, "Error in hiding target view parent: "
                                                    + e.getLocalizedMessage());
                                        }

                                        //hide the target view
                                        targetView.setVisibility(View.GONE);
                                    }

                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.requestFocus();
                Toast.makeText(MainActivity.this, R.string.please_select_option,
                        Toast.LENGTH_LONG).show();
            }
        });


        return spinner;
    }

    private void formatEditText(final MyTextInputEditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            boolean isEditing;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }


            @Override
            public void afterTextChanged(Editable s) {
                if(isEditing) return;
                isEditing = true;
                // removing old dashes
                StringBuilder sb = new StringBuilder();
                sb.append(s.toString().replace("-", ""));

                if (sb.length()> 3)
                    sb.insert(3, "-");
                if (sb.length()> 8)
                    sb.insert(8, "-");
                if(sb.length()> 12)
                    sb.delete(12, sb.length());

                s.replace(0, s.length(), sb.toString());
                isEditing = false;
            }
        });
    }
}

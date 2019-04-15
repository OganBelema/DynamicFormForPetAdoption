package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.R;


public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
        this.setBackground(ResourcesCompat.getDrawable(context.getResources(),
                R.drawable.edit_text_background, context.getTheme()));
        LinearLayout.LayoutParams editTextLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                120);
        this.setLayoutParams(editTextLayoutParams);
    }
}

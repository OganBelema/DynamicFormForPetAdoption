package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.R;

public class MyElementLabelTextView extends AppCompatTextView {

    public MyElementLabelTextView(Context context) {
        super(context);
        this.setTextSize(20f);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 40;
        this.setLayoutParams(layoutParams);
    }
}

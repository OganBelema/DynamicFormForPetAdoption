package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.LinearLayout;

public class MyLabelTextInputLayout extends TextInputLayout {

    public MyLabelTextInputLayout(Context context) {
        super(context);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 40;
        this.setLayoutParams(layoutParams);
    }
}

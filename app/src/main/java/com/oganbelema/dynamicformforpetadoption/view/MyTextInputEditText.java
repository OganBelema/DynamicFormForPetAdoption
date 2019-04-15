package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.widget.LinearLayout;


public class MyTextInputEditText extends TextInputEditText {

    public MyTextInputEditText(Context context) {
        super(context);
        LinearLayout.LayoutParams editTextLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        120);
        this.setLayoutParams(editTextLayoutParams);
    }
}

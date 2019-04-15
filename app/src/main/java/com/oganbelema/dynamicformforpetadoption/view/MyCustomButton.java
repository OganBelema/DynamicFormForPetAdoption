package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.R;

public class MyCustomButton extends AppCompatButton {

    public MyCustomButton(Context context) {
        super(context);
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        buttonLayoutParams.gravity = Gravity.CENTER;
        buttonLayoutParams.setMargins(0, 40, 0, 20);
        this.setLayoutParams(buttonLayoutParams);

        //style button
        this.setTextColor(ResourcesCompat.getColor(getResources(),
                android.R.color.white, context.getTheme()));
        this.setBackground(ResourcesCompat.getDrawable(getResources(),
                R.drawable.button_style_selector, context.getTheme()));
    }
}

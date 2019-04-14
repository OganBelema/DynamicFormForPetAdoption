package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.R;

public class MyFormNameTextView extends AppCompatTextView {

    public MyFormNameTextView(Context context) {
        super(context);
        this.setTextSize(28f);
        this.setTypeface(ResourcesCompat.getFont(context, R.font.gothic), Typeface.BOLD);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 20;
        layoutParams.bottomMargin = 20;
        layoutParams.gravity = Gravity.CENTER;
        this.setGravity(Gravity.CENTER);
        this.setLayoutParams(layoutParams);
    }
}

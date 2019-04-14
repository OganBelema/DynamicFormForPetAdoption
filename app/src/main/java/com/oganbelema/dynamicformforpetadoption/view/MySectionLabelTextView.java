package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.oganbelema.dynamicformforpetadoption.R;


public class MySectionLabelTextView extends AppCompatTextView {

    public MySectionLabelTextView(Context context) {
        super(context);
        this.setTextSize(20f);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 10;
        layoutParams.bottomMargin = 10;
        layoutParams.gravity = Gravity.CENTER;
        this.setTypeface(ResourcesCompat.getFont(context, R.font.gothic), Typeface.BOLD);
        this.setLayoutParams(layoutParams);
    }
}

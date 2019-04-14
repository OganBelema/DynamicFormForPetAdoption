package com.oganbelema.dynamicformforpetadoption.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.widget.LinearLayout;


public class MyPageLabelTextView extends AppCompatTextView {

    public MyPageLabelTextView(Context context) {
        super(context);
        this.setTextSize(18f);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 10;
        layoutParams.bottomMargin = 10;
        layoutParams.gravity = Gravity.CENTER;
        this.setLayoutParams(layoutParams);
    }
}

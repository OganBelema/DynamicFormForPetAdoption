package com.oganbelema.dynamicformforpetadoption.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.oganbelema.dynamicformforpetadoption.R;

public class DogAnimationViewForLoading {

    private final View mRootView;
    private final LottieAnimationView mLottieAnimationView;
    private final TextView mLoadingTextView;

    public DogAnimationViewForLoading(LayoutInflater inflater, ViewGroup parent) {
        mRootView = inflater.inflate(R.layout.activity_main, parent, false);
        mLottieAnimationView = findViewById(R.id.lottieAnimationView);
        mLoadingTextView = findViewById(R.id.loading_textView);
    }

    private <T extends View> T findViewById(int id){
        return mRootView.findViewById(id);
    }

    private void hideView(){
        mLottieAnimationView.setVisibility(View.GONE);
        mLoadingTextView.setVisibility(View.GONE);
    }

    private void showView(){
        mLottieAnimationView.setVisibility(View.VISIBLE);
        mLoadingTextView.setVisibility(View.VISIBLE);
    }

    public View getRootView() {
        return mRootView;
    }
}

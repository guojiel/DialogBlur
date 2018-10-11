package com.guojiel.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class BlurDialog extends Dialog {

    protected BlurView mBlurView;

    public BlurDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BlurDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected BlurDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context){
        Activity activity = (Activity) context;
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        mBlurView = decorView.findViewById(R.id.blur_dialog_bg);
        if(mBlurView == null){
            mBlurView = new BlurView(activity);
            mBlurView.setId(R.id.blur_dialog_bg);
            mBlurView.setAlpha(0f);
            decorView.addView(mBlurView, new ViewGroup.LayoutParams(-1,-1));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBlurView.blur();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBlurView.show();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBlurView.hide();
    }

}

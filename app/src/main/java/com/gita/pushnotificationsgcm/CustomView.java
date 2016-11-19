package com.gita.pushnotificationsgcm;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by alex on 11/19/2016
 */

public class CustomView extends FrameLayout {
    private ImageView imageView;


    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.custom_view_layout, this);

        imageView = (ImageView) findViewById(R.id.custom_view_image);

        if (isInEditMode()) {
            return;
        }


        if (attrs != null) {
            TypedArray ar = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
            boolean canScroll = ar.getBoolean(R.styleable.CustomView_canScroll, false);
            String disabledText = ar.getString(R.styleable.CustomView_defaultDisabledText);
            int id = ar.getResourceId(R.styleable.CustomView_someParam, -1);

            if(id != -1){

                imageView.setImageResource(id);


            }
            ar.recycle();
        }
    }
}

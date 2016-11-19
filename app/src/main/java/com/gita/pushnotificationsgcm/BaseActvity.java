package com.gita.pushnotificationsgcm;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by alex on 11/19/2016
 */

public class BaseActvity extends AppCompatActivity {


    @Override
    protected void onPause() {
        super.onPause();
        App.instance.getBus().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.instance.getBus().register(this);
    }
}

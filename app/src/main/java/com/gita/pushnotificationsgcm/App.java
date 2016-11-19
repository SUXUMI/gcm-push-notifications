package com.gita.pushnotificationsgcm;

import android.app.Application;
import android.content.Intent;

import com.gita.pushnotificationsgcm.gcm.GCMRegistrationIntentService;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by alex on 11/19/2016
 */

public class App extends Application {
    public static App instance;

    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, GCMRegistrationIntentService.class);
        startService(intent);
        instance = this;

        bus = new Bus();
    }


    public Bus getBus() {
        return bus;
    }
}

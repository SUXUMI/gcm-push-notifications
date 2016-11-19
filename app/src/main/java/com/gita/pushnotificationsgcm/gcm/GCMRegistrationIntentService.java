package com.gita.pushnotificationsgcm.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.gita.pushnotificationsgcm.App;
import com.gita.pushnotificationsgcm.R;
import com.gita.pushnotificationsgcm.TokenAvailableEvent;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.squareup.otto.Bus;

import java.io.IOException;

/**
 * Created by alex on 11/19/2016
 */

public class GCMRegistrationIntentService extends IntentService {

    public GCMRegistrationIntentService() {
        super("");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        registerGCM();
    }

    private void registerGCM() {
        final String token;
        try {
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.d("Token",token);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    App.instance.getBus().post(new TokenAvailableEvent(token));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

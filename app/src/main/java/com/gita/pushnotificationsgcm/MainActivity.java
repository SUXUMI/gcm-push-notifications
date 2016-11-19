package com.gita.pushnotificationsgcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends BaseActvity {

    private TextView tokenTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenTV = (TextView) findViewById(R.id.text_view_token);

        TextView fromPushTV = (TextView) findViewById(R.id.text_view_from_push);
        if (getIntent().getBooleanExtra("fromPush", false)) {
            fromPushTV.setText("Application started from push\n" +
                    getIntent().getStringExtra("title") + "\n" +
                    getIntent().getStringExtra("message"));
        }

    }


    @Subscribe
    public void onTokenAvailable(String token) {
        tokenTV.setText("Token = " + token);
    }
}

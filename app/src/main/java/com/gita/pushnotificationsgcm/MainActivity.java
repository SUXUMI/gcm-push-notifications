package com.gita.pushnotificationsgcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onResume() {
        super.onResume();
        App.instance.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.instance.getBus().unregister(this);
    }

    @Subscribe
    public void onTokenAvailable(TokenAvailableEvent event) {
        tokenTV.setText("Token = " + event.getToken());
    }
}

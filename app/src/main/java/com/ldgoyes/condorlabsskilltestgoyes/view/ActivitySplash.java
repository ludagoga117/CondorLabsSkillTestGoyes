package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceSplashPresenterView;

public class ActivitySplash extends AppCompatActivity implements InterfaceSplashPresenterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void launchNextActivity() {
        Intent activityListIntent = new Intent (
                ActivitySplash.this,
                ActivityDetail.class
        );
        activityListIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(activityListIntent);
        ActivitySplash.this.finish();
    }
}

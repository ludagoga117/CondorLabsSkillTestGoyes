package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceSplashPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.presenter.PresenterSplash;

public class ActivitySplash extends AppCompatActivity implements InterfaceSplashPresenterView {

    private PresenterSplash presenterSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenterSplash = PresenterSplash.instanceOf(
                ActivitySplash.this,
                this
        );
        presenterSplash.start();
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

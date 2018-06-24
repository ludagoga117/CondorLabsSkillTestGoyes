package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;
import android.os.CountDownTimer;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceSplashPresenterView;

public class PresenterSplash {
    private Context context;
    private InterfaceSplashPresenterView activitySplash;

    public PresenterSplash (Context context, InterfaceSplashPresenterView activitySplash ){
        this.context = context;
        this.activitySplash = activitySplash;
    }

    public static PresenterSplash instanceOf ( Context context, InterfaceSplashPresenterView activitySplash ){
        return new PresenterSplash( context, activitySplash );
    }

    public void start(){
        new CountDownTimer(
                context.getResources().getInteger( R.integer.timeout_splash_screen ),
                context.getResources().getInteger( R.integer.timeout_splash_screen )
        ){
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                activitySplash.launchNextActivity();
            }
        }.start();
    }
}

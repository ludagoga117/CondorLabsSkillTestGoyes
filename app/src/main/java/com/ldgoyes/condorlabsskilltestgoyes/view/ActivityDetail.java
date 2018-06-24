package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.presenter.PresenterDetail;

public class ActivityDetail extends AppCompatActivity implements InterfaceDetailPresenterView {

    private PresenterDetail presenterDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String movieId = "297762";
        
        presenterDetail = PresenterDetail.instanceOf(
                ActivityDetail.this,
                this,
                movieId
        );
        presenterDetail.start();
    }
}

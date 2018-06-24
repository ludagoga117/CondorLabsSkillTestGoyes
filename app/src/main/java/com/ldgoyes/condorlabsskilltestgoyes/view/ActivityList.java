package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.presenter.PresenterList;

public class ActivityList extends AppCompatActivity implements InterfaceListPresenterView {

    private PresenterList presenterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        presenterList = PresenterList.instanceOf(
                ActivityList.this,
                this
        );
        presenterList.start();
    }


}
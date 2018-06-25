package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.presenter.PresenterList;
import com.ldgoyes.condorlabsskilltestgoyes.view.adapters.AdapterRecyclerView;

public class ActivityList extends AppCompatActivity implements InterfaceListPresenterView {

    private PresenterList presenterList;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

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

    @Override
    public void jumpToDetailActivity(SummaryHolder summaryObject) {
        Intent activityListIntent = new Intent (
                ActivityList.this,
                ActivityDetail.class
        );
        // TODO pasar informacion a traves de los extras
        startActivity(activityListIntent);
    }

    @Override
    public void initRecyclerview(AdapterRecyclerView adapterRecyclerView) {
        recyclerView = (RecyclerView) findViewById(R.id.activitylist_recyclerview );
        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( layoutManager );

        recyclerView.setAdapter( adapterRecyclerView );
    }
}

package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
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

        CheckBox filterGreaterThan2000 = (CheckBox) findViewById(R.id.activitylist_checkbox_votecounter);
        filterGreaterThan2000.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenterList.applyFilterGreaterThan2000( isChecked );
            }
        });

        CheckBox filterOnlyFav = (CheckBox) findViewById(R.id.activitylist_checkbox_onlyfav);
        filterOnlyFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenterList.applyFilterOnlyFav( isChecked );
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void jumpToDetailActivity(SummaryHolder summaryObject) {
        Intent activityListIntent = new Intent (
                ActivityList.this,
                ActivityDetail.class
        );
        activityListIntent.putExtra(DBConstants.DataSummary.MOVIE_ID, summaryObject.movieId);
        activityListIntent.putExtra(DBConstants.DataSummary.MOVIE_NAME, summaryObject.movieName);
        startActivity(activityListIntent);
    }

    @Override
    public void initRecyclerview(AdapterRecyclerView adapterRecyclerView) {
        recyclerView = (RecyclerView) findViewById(R.id.activitylist_recyclerview );
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( layoutManager );

        recyclerView.setAdapter( adapterRecyclerView );
    }
}

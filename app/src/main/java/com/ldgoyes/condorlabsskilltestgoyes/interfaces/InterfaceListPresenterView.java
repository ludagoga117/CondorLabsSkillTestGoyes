package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.view.adapters.AdapterRecyclerView;

public interface InterfaceListPresenterView {
    void jumpToDetailActivity(SummaryHolder summaryObject );
    void initRecyclerview(AdapterRecyclerView adapterRecyclerView);
}

package com.ldgoyes.condorlabsskilltestgoyes.interactor;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;

public class InteractorList implements InterfaceListInteractorDatabase {
    private Context context;
    private InterfaceListPresenterInteractor presenterList;

    public InteractorList( Context context, InterfaceListPresenterInteractor presenterList ){
        this.context = context;
        this.presenterList = presenterList;
    }

    public static InteractorList instanceOf( Context context, InterfaceListPresenterInteractor presenterList ){
        return new InteractorList( context, presenterList );
    }

    @Override
    public void successfulCreateSummary() {
        
    }

    @Override
    public void errorCreateSummary() {

    }
}

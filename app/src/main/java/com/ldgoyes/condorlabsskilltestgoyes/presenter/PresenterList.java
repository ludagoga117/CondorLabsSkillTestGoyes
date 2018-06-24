package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorList;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;

public class PresenterList implements InterfaceListPresenterInteractor{

    private Context context;
    private InterfaceListPresenterView activityList;
    private InteractorList interactorList;


    public PresenterList ( Context context, InterfaceListPresenterView activityList ){
        this.context = context;
        this.activityList = activityList;
        interactorList = InteractorList.instanceOf(
                context,
                this
        );
    }

    public static PresenterList instanceOf ( Context context, InterfaceListPresenterView activityList ){
        return new PresenterList( context, activityList );
    }

    public void start(){
        interactorList.downloadPopularMoviesList();
    }
}

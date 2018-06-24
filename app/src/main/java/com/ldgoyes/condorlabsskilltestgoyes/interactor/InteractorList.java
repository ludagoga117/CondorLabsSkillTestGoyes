package com.ldgoyes.condorlabsskilltestgoyes.interactor;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskDownloadPopular;
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

    public void downloadPopularMoviesList(){
        AsyncTaskDownloadPopular asyncTaskDownloadPopular = new AsyncTaskDownloadPopular();
        asyncTaskDownloadPopular.execute();
    }










    @Override
    public void successfulCreateSummary() {
        
    }

    @Override
    public void errorCreateSummary() {

    }

    @Override
    public void successfulReadSummary(SummaryHolder extractedData) {

    }

    @Override
    public void errorReadSummary() {

    }

    @Override
    public void successfulListSummary(SummaryHolder[] extractedData) {

    }

    @Override
    public void errorListSummary() {

    }

    @Override
    public void successfulClearSummary() {

    }

    @Override
    public void errorClearSummary() {

    }

    @Override
    public void successfulDeleteSummary() {

    }

    @Override
    public void errorDeleteSummary() {

    }

    @Override
    public void successfulCreateDetail() {

    }

    @Override
    public void errorCreateDetail() {

    }

    @Override
    public void successfulReadDetail(DetailHolder extractedData) {

    }

    @Override
    public void errorReadDetail() {

    }

    @Override
    public void successfulUpdateDetail() {

    }

    @Override
    public void errorUpdateDetail() {

    }

    @Override
    public void successfulDeleteDetail() {

    }

    @Override
    public void errorDeleteDetail() {

    }
}

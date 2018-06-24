package com.ldgoyes.condorlabsskilltestgoyes.interactor;

import android.content.Context;
import android.util.Log;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskDownloadJSON;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskResponseDownloadJSON;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;

import org.json.JSONObject;

public class InteractorList implements InterfaceListInteractorDatabase {
    private Context context;
    private InterfaceListPresenterInteractor presenterList;

    private String tmdbApiKey;

    public InteractorList( Context context, InterfaceListPresenterInteractor presenterList ){
        this.context = context;
        this.presenterList = presenterList;
        this.tmdbApiKey = context.getString( R.string.tmdb_api_key );
    }

    public static InteractorList instanceOf( Context context, InterfaceListPresenterInteractor presenterList ){
        return new InteractorList( context, presenterList );
    }

    public void downloadPopularMoviesList( String language, String pageToQuery ){
        String URLdownloadPopularMovies =
                "https://api.themoviedb.org/3/movie/popular?api_key=" + tmdbApiKey
                    + "&language=" + language
                    + "&page=" + pageToQuery;

        AsyncTaskDownloadJSON asyncTaskDownloadPopular = new AsyncTaskDownloadJSON(
                URLdownloadPopularMovies,
                processDownloadPopularMovies()
        );
        asyncTaskDownloadPopular.execute();
    }

    private AsyncTaskResponseDownloadJSON processDownloadPopularMovies(){
        AsyncTaskResponseDownloadJSON asyncTaskResponse = new AsyncTaskResponseDownloadJSON(){
            @Override
            public void processResult(JSONObject jsonObject) {
                if( jsonObject == null ) {
                    presenterList.notifyDownloadErrorPopularMovies();
                    return;
                }

                Log.d( context.getString(R.string.debug_tag), jsonObject.toString() );
            }
        };
        return asyncTaskResponse;
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

package com.ldgoyes.condorlabsskilltestgoyes.interactor;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskDownloadJSON;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskResponseDownloadJSON;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailInteractorDatabase;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailPresenterInteractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InteractorDetail implements InterfaceDetailInteractorDatabase {
    private Context context;
    private InterfaceDetailPresenterInteractor presenterDetail;

    private String tmdbApiKey;

    public InteractorDetail( Context context, InterfaceDetailPresenterInteractor presenterDetail ){
        this.context = context;
        this.presenterDetail = presenterDetail;
        this.tmdbApiKey = context.getString( R.string.tmdb_api_key );
    }

    public static InteractorDetail instanceOf( Context context, InterfaceDetailPresenterInteractor presenterDetail ){
        return new InteractorDetail( context, presenterDetail );
    }

    public void downloadMovieDetails( String movieId, String language ){
        String URLdownloadMovieDetails =
                "https://api.themoviedb.org/3/movie/"+ movieId
                        + "?api_key=" + tmdbApiKey
                        + "&language=" + language;

        AsyncTaskDownloadJSON asyncTaskDownloadMovieDetails = new AsyncTaskDownloadJSON(
                URLdownloadMovieDetails,
                processDownloadMovieDetails()
        );
        asyncTaskDownloadMovieDetails.execute();
    }

    private AsyncTaskResponseDownloadJSON processDownloadMovieDetails(){
        AsyncTaskResponseDownloadJSON asyncTaskResponse = new AsyncTaskResponseDownloadJSON() {
            @Override
            public void processResult(JSONObject jsonObject) {
                if( jsonObject == null ) {
                    presenterDetail.notifyDownloadErrorMovieDetails();
                    return;
                }

                storeDetailsJsonResult( jsonObject );
            }
        };
        return asyncTaskResponse;
    }

    private void storeDetailsJsonResult( JSONObject jsonObject ){
        try {
            String movieId = jsonObject.getString( context.getString(R.string.JSONObject_TAG_id ) );
            String budget = jsonObject.getString( context.getString(R.string.JSONObject_TAG_budget ) );

            HashMap<String, String> newDetailEntryArguments = new HashMap<>();
            newDetailEntryArguments.put( DBConstants.DataDetail.BUDGET, budget);

            DBManager.updateDetailEntry(
                    context,
                    InteractorDetail.this,
                    newDetailEntryArguments,
                    movieId
            );
        } catch (JSONException e) {
            presenterDetail.notifyDownloadErrorMovieDetails();
        }
    }

    public void downloadMovieVideo( String movieId ){
        String URLdownloadMovieVideos =
                "https://api.themoviedb.org/3/movie/"+ movieId
                        + "/videos?api_key=" + tmdbApiKey;

        AsyncTaskDownloadJSON asyncTaskDownloadMovieDetails = new AsyncTaskDownloadJSON(
                URLdownloadMovieVideos,
                processDownloadMovieVideos()
        );
        asyncTaskDownloadMovieDetails.execute();
    }

    private AsyncTaskResponseDownloadJSON processDownloadMovieVideos(){
        AsyncTaskResponseDownloadJSON asyncTaskResponse = new AsyncTaskResponseDownloadJSON(){
            @Override
            public void processResult(JSONObject jsonObject) {
                if( jsonObject == null ) {
                    presenterDetail.notifyDownloadErrorMovieDetails();
                    return;
                }

                storeVideoJsonResult( jsonObject );
            }
        };
        return asyncTaskResponse;
    }

    private void storeVideoJsonResult( JSONObject jsonObject ){
        try {
            String movieId = jsonObject.getString( context.getString(R.string.JSONObject_TAG_id ) );

            JSONArray videosJsonArray = jsonObject.getJSONArray( context.getString(R.string.JSONArray_TAG_results ) );


            if( videosJsonArray.length() == 0 ){
                presenterDetail.notifyUpdateSuccessDetail();
                return;
            }

            JSONObject firstVideo = videosJsonArray.getJSONObject( 0 );
            String youtubeVideoKey = firstVideo.getString( context.getString(R.string.JSONObject_TAG_youtubevideo) );

            HashMap <String, String> newDetailEntryArguments = new HashMap<>();
            newDetailEntryArguments.put( DBConstants.DataDetail.TRAILER_LINK, "https://www.youtube.com/watch?v=" + youtubeVideoKey );

            DBManager.updateDetailEntry(
                    context,
                    InteractorDetail.this,
                    newDetailEntryArguments,
                    movieId
            );

        } catch (JSONException e) {
            presenterDetail.notifyDownloadErrorVideo();
        }
    }

    public void extractMovieDetailsFromDatabase(String movieId ){
        DBManager.readDetailEntry(
                context,
                InteractorDetail.this,
                movieId
        );
    }


    @Override
    public void successfulReadDetail(DetailHolder extractedData) {
        presenterDetail.notifyExtractionSuccessMovieDetails( extractedData );
    }

    @Override
    public void errorReadDetail() {

    }

    @Override
    public void successfulUpdateDetail() {
        presenterDetail.notifyUpdateSuccessDetail();
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

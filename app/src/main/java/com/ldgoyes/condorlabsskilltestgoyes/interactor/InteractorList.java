package com.ldgoyes.condorlabsskilltestgoyes.interactor;

import android.content.Context;
import android.util.Log;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskDownloadJSON;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks.AsyncTaskResponseDownloadJSON;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InteractorList implements InterfaceListInteractorDatabase {
    private Context context;
    private InterfaceListPresenterInteractor presenterList;
    private long remainingMoviesToStore;

    private String tmdbApiKey;

    public InteractorList( Context context, InterfaceListPresenterInteractor presenterList ){
        this.context = context;
        this.presenterList = presenterList;
        this.tmdbApiKey = context.getString( R.string.tmdb_api_key );
        this.remainingMoviesToStore = 0;
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

    private AsyncTaskResponseDownloadJSON processDownloadMovieDetails(){
        AsyncTaskResponseDownloadJSON asyncTaskResponse = new AsyncTaskResponseDownloadJSON() {
            @Override
            public void processResult(JSONObject jsonObject) {
                if( jsonObject == null ) {
                    presenterList.notifyDownloadErrorMovieDetails();
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

            HashMap <String, String> newDetailEntryArguments = new HashMap<>();
            newDetailEntryArguments.put( DBConstants.DataDetail.BUDGET, budget);

            DBManager.updateDetailEntry(
                    context,
                    InteractorList.this,
                    newDetailEntryArguments,
                    movieId
            );
        } catch (JSONException e) {
            presenterList.notifyDownloadErrorMovieDetails();
        }
    }

    public void clearPopularMoviesList(){
        DBManager.clearSummaryEntries(
                context,
                this
        );
    }

    private AsyncTaskResponseDownloadJSON processDownloadPopularMovies(){
        AsyncTaskResponseDownloadJSON asyncTaskResponse = new AsyncTaskResponseDownloadJSON(){
            @Override
            public void processResult(JSONObject jsonObject) {
                if( jsonObject == null ) {
                    presenterList.notifyDownloadErrorPopularMovies();
                    return;
                }

                storePopularMoviesJsonResult( jsonObject );
            }
        };
        return asyncTaskResponse;
    }

    private void storePopularMoviesJsonResult( JSONObject jsonObject ){
        try {
            JSONArray moviesJsonArray = jsonObject.getJSONArray( context.getString(R.string.JSONArray_TAG_results) );
            //Log.d( context.getString(R.string.debug_tag), moviesJsonArray.toString() );

            remainingMoviesToStore = moviesJsonArray.length();
            for( int i = 0; i < moviesJsonArray.length(); i++ ){
                JSONObject movieJsonObject = moviesJsonArray.getJSONObject( i );

                String voteCount = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_vote_count) );
                String video = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_video ));
                String id = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_id ) );
                String voteAverage = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_vote_average ) );
                String title = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_title ));
                String popularity = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_popularity ));
                String poster_path = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_poster_path ));
                String originalLanguage = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_original_language ));
                String originalTitle = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_original_title ));
                String genreIds = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_genre_ids ));
                String backdropPath = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_backdrop_path ));
                String adult = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_adult ));
                String overview = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_overview ));
                String releaseDate = movieJsonObject.getString( context.getString(R.string.JSONObject_TAG_release_date ));

                HashMap <String, String> newSummaryEntryArguments = new HashMap<>();
                newSummaryEntryArguments.put( DBConstants.DataSummary.MOVIE_ID, id);
                newSummaryEntryArguments.put( DBConstants.DataSummary.VOTE_COUNT, voteCount);
                newSummaryEntryArguments.put( DBConstants.DataSummary.MOVIE_NAME, title);
                newSummaryEntryArguments.put( DBConstants.DataSummary.POSTER_PICTURE_PATH, "http://image.tmdb.org/t/p/w185/"+poster_path.substring(1) );
                newSummaryEntryArguments.put( DBConstants.DataSummary.VOTE_AVERAGE, voteAverage);

                Log.d( context.getString(R.string.debug_tag), "Storing: " + id + " - " + title );

                DBManager.createSummaryEntry(
                        context,
                        this,
                        newSummaryEntryArguments
                );

                HashMap <String, String> newDetailEntryArguments = new HashMap<>();

                newDetailEntryArguments.put( DBConstants.DataDetail.MOVIE_ID, id);
                newDetailEntryArguments.put( DBConstants.DataDetail.MOVIE_OVERVIEW, overview);
                newDetailEntryArguments.put( DBConstants.DataDetail.RELEASE_DATE, releaseDate);
                newDetailEntryArguments.put( DBConstants.DataDetail.BUDGET, null );
                newDetailEntryArguments.put( DBConstants.DataDetail.TRAILER_LINK, null );
                newDetailEntryArguments.put( DBConstants.DataDetail.IS_FAVORITE, "false");

                DBManager.createDetailEntry(
                        context,
                        this,
                        newDetailEntryArguments
                );

            }

        } catch (JSONException e) {
            presenterList.notifyDownloadErrorMovieDetails();
            return;
        }
    }

    private AsyncTaskResponseDownloadJSON processDownloadMovieVideos(){
        AsyncTaskResponseDownloadJSON asyncTaskResponse = new AsyncTaskResponseDownloadJSON(){
            @Override
            public void processResult(JSONObject jsonObject) {
                if( jsonObject == null ) {
                    presenterList.notifyDownloadErrorMovieDetails();
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
                Log.d( context.getString(R.string.debug_tag), "movie-id: "+movieId+" - No video" );
                presenterList.notifyUpdateSuccessDetail();
                return;
            }
            //Log.d( context.getString(R.string.debug_tag), "movie-id: "+movieId+" - HAS VIDEO!" );

            JSONObject firstVideo = videosJsonArray.getJSONObject( 0 );
            String youtubeVideoKey = firstVideo.getString( context.getString(R.string.JSONObject_TAG_youtubevideo) );

            HashMap <String, String> newDetailEntryArguments = new HashMap<>();
            newDetailEntryArguments.put( DBConstants.DataDetail.TRAILER_LINK, "https://www.youtube.com/watch?v=" + youtubeVideoKey );

            DBManager.updateDetailEntry(
                    context,
                    InteractorList.this,
                    newDetailEntryArguments,
                    movieId
            );

        } catch (JSONException e) {
            presenterList.notifyDownloadErrorVideo();
        }


    }

    public void extractPopularMoviesFromDatabase(){
        DBManager.listSummaryEntries(
                context,
                this
        );
    }

    public void extractMovieDetailsFromDatabase(String movieId ){
        DBManager.readDetailEntry(
                context,
                InteractorList.this,
                movieId
        );
    }

    @Override
    public synchronized void successfulCreateSummary() {
        if( --remainingMoviesToStore == 0 ){
            presenterList.notifyDownloadSuccessPopularMovies();
        }
    }

    @Override
    public void errorCreateSummary() {
        presenterList.notifyDownloadErrorPopularMovies();
    }

    @Override
    public void successfulReadSummary(SummaryHolder extractedData) {

    }

    @Override
    public void errorReadSummary() {

    }

    @Override
    public void successfulListSummary(SummaryHolder[] extractedData) {
        presenterList.notifyExtractionSuccessPopularMovies( extractedData );
    }

    @Override
    public void errorListSummary() {

    }

    @Override
    public void successfulClearSummary() {
        presenterList.notifySuccessClearTableSummary();
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
        presenterList.notifyExtractionSuccessMovieDetails( extractedData );
    }

    @Override
    public void errorReadDetail() {

    }

    @Override
    public void successfulUpdateDetail() {
        presenterList.notifyUpdateSuccessDetail();
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

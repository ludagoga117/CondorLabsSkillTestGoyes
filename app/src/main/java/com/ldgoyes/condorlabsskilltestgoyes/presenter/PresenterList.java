package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;
import android.util.Log;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorList;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;

public class PresenterList implements InterfaceListPresenterInteractor{

    private Context context;
    private InterfaceListPresenterView activityList;
    private InteractorList interactorList;

    private String tmdbPopularMoviesLanguage;
    private String tmdbPopularMoviesPageToQuery;

    private int remainingUpdates;

    public PresenterList ( Context context, InterfaceListPresenterView activityList ){
        this.context = context;
        this.activityList = activityList;
        interactorList = InteractorList.instanceOf(
                context,
                this
        );
        this.tmdbPopularMoviesLanguage = context.getString( R.string.default_tmdb_popular_movies_language );
        this.tmdbPopularMoviesPageToQuery = context.getString( R.string.default_tmdb_popular_movies_page_to_query );
        this.remainingUpdates = 0;
    }

    public static PresenterList instanceOf ( Context context, InterfaceListPresenterView activityList ){
        return new PresenterList( context, activityList );
    }

    public void start(){
        interactorList.clearPopularMoviesList();
        interactorList.downloadPopularMoviesList( tmdbPopularMoviesLanguage, tmdbPopularMoviesPageToQuery );
    }


    @Override
    public void notifyDownloadErrorPopularMovies() {
        Log.e( context.getString(R.string.debug_tag), "PresenterList - notifyDownloadErrorPopularMovies");
    }

    @Override
    public void notifyDownloadSuccessPopularMovies() {
        Log.d( context.getString(R.string.debug_tag), "PresenterList - notifyDownloadSuccessPopularMovies");
        interactorList.extractPopularMoviesFromDatabase();
    }

    @Override
    public synchronized void notifyUpdateSuccessDetail() {
        if( --remainingUpdates == 0 ){
            // TODO borrar este metodo. Solo se debe llamar si se requieren los detalles. De lo contrario, no.
            interactorList.extractMovieDetailsFromDatabase( "297762" );
        }
    }

    @Override
    public void notifySuccessClearTableSummary() {
        Log.d( context.getString(R.string.debug_tag), "PresenterList - notifySuccessClearTableSummary");
    }

    @Override
    public void notifyExtractionSuccessPopularMovies(SummaryHolder[] extractedData) {
        Log.d( context.getString(R.string.debug_tag), "PresenterList - notifyExtractionSuccessPopularMovies. Number of extracted entries: " + Integer.toString(extractedData.length) );
        for( SummaryHolder summaryObject : extractedData ){
            Log.d( context.getString(R.string.debug_tag), "(id/name): ("+ summaryObject.movieId + "/" + summaryObject.movieName +")");

            // TODO borrar estos metodos. Solo se deben llamar si se requieren los detalles. De lo contrario, no
            remainingUpdates = 2;
            interactorList.downloadMovieDetails( summaryObject.movieId, tmdbPopularMoviesLanguage );
            interactorList.downloadMovieVideo( summaryObject.movieId );
        }
    }

    @Override
    public void notifyExtractionSuccessMovieDetails(DetailHolder extractedData) {
        String movieId = extractedData.movieId;
        String trailerLink = extractedData.trailerLink;
        String budget = extractedData.budget;

        Log.d( context.getString(R.string.debug_tag), "(movieId): ("+ movieId + ")");
        Log.d( context.getString(R.string.debug_tag), "(trailerLink): ("+ trailerLink + ")");
        Log.d( context.getString(R.string.debug_tag), "(budget): ("+ budget + ")");
    }


    @Override
    public void notifyDownloadErrorMovieDetails() {}

    @Override
    public void notifyDownloadErrorVideo() {}
}

package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorDetail;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailPresenterView;

public class PresenterDetail implements InterfaceDetailPresenterInteractor {

    private int remainingUpdates;
    private Context context;
    private InterfaceDetailPresenterView activityDetail;
    private InteractorDetail interactorDetail;
    private String movieId;

    private String tmdbPopularMoviesLanguage;

    public PresenterDetail(Context context, InterfaceDetailPresenterView activityDetail, String movieId ){
        this.context = context;
        this.activityDetail = activityDetail;
        this.remainingUpdates = 0;
        this.movieId = movieId;

        this.interactorDetail = InteractorDetail.instanceOf(
                context,
                PresenterDetail.this
        );
        this.tmdbPopularMoviesLanguage = context.getString( R.string.default_tmdb_popular_movies_language );
    }

    public static PresenterDetail instanceOf( Context context, InterfaceDetailPresenterView activityDetail, String movieId ){
        return new PresenterDetail( context, activityDetail, movieId );
    }

    public void start(){
        remainingUpdates = 2;
        interactorDetail.downloadMovieDetails( movieId, tmdbPopularMoviesLanguage );
        interactorDetail.downloadMovieVideo( movieId );
    }


    @Override
    public void notifyDownloadErrorMovieDetails() {}

    @Override
    public void notifyExtractionSuccessMovieDetails(DetailHolder extractedData) {
        String movieId = extractedData.movieId;
        String trailerLink = extractedData.trailerLink;
        String budget = extractedData.budget;

    }

    @Override
    public synchronized void notifyUpdateSuccessDetail() {
        if( --remainingUpdates == 0 ){
            interactorDetail.extractMovieDetailsFromDatabase( movieId );
        }
    }

    @Override
    public void notifyDownloadErrorVideo() {}
}
package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorList;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterRVAdapter;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.view.adapters.AdapterRecyclerView;

public class PresenterList implements InterfaceListPresenterInteractor, InterfaceListPresenterRVAdapter {

    private Context context;
    private InterfaceListPresenterView activityList;
    private InteractorList interactorList;

    private String tmdbPopularMoviesLanguage;
    private String tmdbPopularMoviesPageToQuery;

    private AdapterRecyclerView adapterRecyclerView;

    private SummaryHolder[] extractedData;

    private int imagesToDownload;


    public PresenterList ( Context context, InterfaceListPresenterView activityList ){
        this.context = context;
        this.activityList = activityList;
        interactorList = InteractorList.instanceOf(
                context,
                this
        );
        this.tmdbPopularMoviesLanguage = context.getString( R.string.default_tmdb_popular_movies_language );
        this.tmdbPopularMoviesPageToQuery = context.getString( R.string.default_tmdb_popular_movies_page_to_query );
    }

    public static PresenterList instanceOf ( Context context, InterfaceListPresenterView activityList ){
        return new PresenterList( context, activityList );
    }

    public void start(){
        interactorList.clearPopularMoviesList();
        interactorList.clearDetailTable();
        interactorList.downloadPopularMoviesList( tmdbPopularMoviesLanguage, tmdbPopularMoviesPageToQuery );
    }

    @Override
    public void notifyDownloadSuccessPopularMovies() {
        interactorList.extractPopularMoviesFromDatabase();
    }

    @Override
    public synchronized void notifyDownloadSuccessImage(String movieId, Bitmap image) {
        adapterRecyclerView.addImageToShow( movieId, image );
        if( --imagesToDownload == 0 ){
            adapterRecyclerView.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyExtractionSuccessPopularMovies(final SummaryHolder[] extractedData) {
        this.extractedData = extractedData;
        adapterRecyclerView = new AdapterRecyclerView(
                extractedData,
                this
        );
        activityList.initRecyclerview( adapterRecyclerView );

        imagesToDownload = extractedData.length;
        for( SummaryHolder summaryObject : extractedData ){
            interactorList.downloadImage( summaryObject.movieId, summaryObject.posterPicturePath );
        }
    }


    @Override
    public void notifyDownloadErrorPopularMovies() {}

    @Override
    public void notifySuccessClearTableSummary() {
    }



    @Override
    public void notifyDownloadErrorMovieDetails() {}

    @Override
    public void recyclerviewNotifyItemSelected(int indexSelectedItem) {
        activityList.jumpToDetailActivity( extractedData[indexSelectedItem] );
    }
}

package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorList;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.ExtendedSummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterRVAdapter;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.view.adapters.AdapterRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PresenterList implements InterfaceListPresenterInteractor, InterfaceListPresenterRVAdapter {

    private Context context;
    private InterfaceListPresenterView activityList;
    private InteractorList interactorList;

    private String tmdbPopularMoviesLanguage;
    private String tmdbPopularMoviesPageToQuery;

    private AdapterRecyclerView adapterRecyclerView;

    private ExtendedSummaryHolder[] extractedData, filteredData;

    private boolean filterVoteCountGreaterThan2000, filterOnlyFav;

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
        this.filterVoteCountGreaterThan2000 = false;
        this.filterOnlyFav = false;
    }

    public static PresenterList instanceOf ( Context context, InterfaceListPresenterView activityList ){
        return new PresenterList( context, activityList );
    }

    public void start(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        /* Según la documentación, la api actualiza la lista de peliculas diariamente, por eso,
        si sigue siendo el mismo día no vale la pena volver a descargar */

        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );
        if( !sharedPref.getString(
                context.getString(R.string.preference_last_download),
                "").equals(timeStamp)
        ){
            interactorList.clearPopularMoviesList();
            interactorList.clearDetailTable();
            interactorList.downloadPopularMoviesList( tmdbPopularMoviesLanguage, tmdbPopularMoviesPageToQuery );
        }else{
            interactorList.extractPopularMoviesFromDatabase();
        }
    }

    @Override
    public void notifyDownloadSuccessPopularMovies() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );
        sharedPref.edit().putString(
                context.getString(R.string.preference_last_download),
                timeStamp
        ).commit();

        interactorList.extractPopularMoviesFromDatabase();
    }

    @Override
    public synchronized void notifyDownloadSuccessImage(String movieId, Bitmap image) {
        adapterRecyclerView.addImageToShow( movieId, image );
        adapterRecyclerView.notifyDataSetChanged();
        /*if( --imagesToDownload == 0 ){
            adapterRecyclerView.notifyDataSetChanged();
        }*/
    }

    @Override
    public void notifyExtractionSuccessPopularMovies(final ExtendedSummaryHolder[] extractedData) {
        this.extractedData = extractedData;
        this.filteredData = applyFilters( extractedData );

        adapterRecyclerView = new AdapterRecyclerView(
                filteredData,
                this
        );
        activityList.initRecyclerview( adapterRecyclerView );

        imagesToDownload = filteredData.length;
        for( SummaryHolder summaryObject : filteredData ){
            interactorList.downloadImage( summaryObject.movieId, summaryObject.posterPicturePath );
        }
    }

    private ExtendedSummaryHolder[] applyFilters( ExtendedSummaryHolder[] extractedData ){
        List<ExtendedSummaryHolder> filteredData = new ArrayList<ExtendedSummaryHolder>();

        for( ExtendedSummaryHolder summaryObject : extractedData ){
            if( this.filterVoteCountGreaterThan2000 && Integer.parseInt(summaryObject.voteCount)<2000 ) continue;
            //if( this.filterOnlyFav && !summaryObject.isFavorite ) continue;
            filteredData.add( summaryObject );
        }

        ExtendedSummaryHolder[] filteredDataArray = new ExtendedSummaryHolder[ filteredData.size() ];
        filteredDataArray = filteredData.toArray( filteredDataArray );

        return filteredDataArray;
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
        activityList.jumpToDetailActivity( filteredData[indexSelectedItem] );
    }

    public void applyFilterGreaterThan2000( boolean filterVoteCountGreaterThan2000 ){
        this.filterVoteCountGreaterThan2000 = filterVoteCountGreaterThan2000;
        this.filteredData = applyFilters( extractedData );
        this.adapterRecyclerView.setData( filteredData );
        this.adapterRecyclerView.notifyDataSetChanged();
    }

    public void applyFilterOnlyFav( boolean filterOnlyFav ){
        this.filterOnlyFav = filterOnlyFav;
        this.filteredData = applyFilters( extractedData );
        this.adapterRecyclerView.setData( filteredData );
        this.adapterRecyclerView.notifyDataSetChanged();
    }
}

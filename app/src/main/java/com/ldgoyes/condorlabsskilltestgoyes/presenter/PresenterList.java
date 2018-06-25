package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorList;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterRVAdapter;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.view.adapters.AdapterRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PresenterList implements InterfaceListPresenterInteractor, InterfaceListPresenterRVAdapter {

    private Context context;
    private InterfaceListPresenterView activityList;
    private InteractorList interactorList;

    private String tmdbPopularMoviesLanguage;
    private String tmdbPopularMoviesPageToQuery;

    private AdapterRecyclerView adapterRecyclerView;

    private SummaryHolder[] extractedData, filteredData;

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
        adapterRecyclerView.notifyDataSetChanged();
        /*if( --imagesToDownload == 0 ){
            adapterRecyclerView.notifyDataSetChanged();
        }*/
    }

    @Override
    public void notifyExtractionSuccessPopularMovies(final SummaryHolder[] extractedData) {
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

    private SummaryHolder[] applyFilters( SummaryHolder[] extractedData ){
        List<SummaryHolder> filteredData = new ArrayList<SummaryHolder>();

        for( SummaryHolder summaryObject : extractedData ){
            if( this.filterVoteCountGreaterThan2000 && Integer.parseInt(summaryObject.voteCount)<2000 ) continue;
            //if( this.filterOnlyFav  ) continue;
            filteredData.add( summaryObject );
        }

        SummaryHolder[] filteredDataArray = new SummaryHolder[ filteredData.size() ];
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
        activityList.jumpToDetailActivity( extractedData[indexSelectedItem] );
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

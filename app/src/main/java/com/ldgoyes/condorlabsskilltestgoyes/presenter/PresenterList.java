package com.ldgoyes.condorlabsskilltestgoyes.presenter;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.InteractorList;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterInteractor;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterView;

public class PresenterList implements InterfaceListPresenterInteractor{

    private Context context;
    private InterfaceListPresenterView activityList;
    private InteractorList interactorList;

    private String tmdbPopularMoviesLanguage;
    private String tmdbPopularMoviesPageToQuery;


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
        interactorList.downloadPopularMoviesList( tmdbPopularMoviesLanguage, tmdbPopularMoviesPageToQuery );
    }


}

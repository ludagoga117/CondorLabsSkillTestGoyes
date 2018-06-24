package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;

public class DetailHolder {
    public static final String[] entryProperties = {
            DBConstants.General.id,
            DBConstants.DataDetail.MOVIE_ID,
            DBConstants.DataDetail.MOVIE_OVERVIEW,
            DBConstants.DataDetail.BUDGET,
            DBConstants.DataDetail.TRAILER_LINK,
            DBConstants.DataDetail.IS_FAVORITE,
    };
    private String id, movieId, movieOverview, budget, trailerLink;
    private boolean isFavorite;

    public DetailHolder( String id, String movieId, String movieOverview, String budget, String trailerLink, String isFavorite ){
        this.id = id;
        this.movieId = movieId;
        this.movieOverview = movieOverview;
        this.budget = budget;
        this.trailerLink = trailerLink;
        this.isFavorite = isFavorite.equals("true");
    }
}

package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;

public class DetailHolder {
    public static final String[] entryProperties = {
            DBConstants.General.id,
            DBConstants.DataDetail.ID_SUMMARY,
            DBConstants.DataDetail.MOVIE_OVERVIEW,
            DBConstants.DataDetail.BUDGET,
            DBConstants.DataDetail.TRAILER_LINK,
            DBConstants.DataDetail.IS_FAVORITE,
    };
    private String id, idSummary, movieOverview, budget, trailerLink;
    private boolean isFavorite;

    public DetailHolder( String id, String idSummary, String movieOverview, String budget, String trailerLink, String isFavorite ){
        this.id = id;
        this.idSummary = idSummary;
        this.movieOverview = movieOverview;
        this.budget = budget;
        this.trailerLink = trailerLink;
        this.isFavorite = isFavorite.equals("true");
    }
}

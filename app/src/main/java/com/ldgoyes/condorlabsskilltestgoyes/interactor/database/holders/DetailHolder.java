package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;

public class DetailHolder {
    public static final String[] entryProperties = DBConstants.DataDetail.entryProperties;

    public String id, movieId, movieOverview, releaseDate, budget, trailerLink;
    public boolean isFavorite;

    public DetailHolder( String id, String movieId, String movieOverview, String releaseDate, String budget, String trailerLink, String isFavorite ){
        this.id = id;
        this.movieId = movieId;
        this.movieOverview = movieOverview;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.trailerLink = trailerLink;
        this.isFavorite = isFavorite.equals("true");
    }
}

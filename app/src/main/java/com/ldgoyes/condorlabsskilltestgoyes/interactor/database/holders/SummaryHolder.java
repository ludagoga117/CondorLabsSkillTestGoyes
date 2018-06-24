package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;

public class SummaryHolder {
    public static final String[] entryProperties = DBConstants.DataSummary.entryProperties;

    public String id, movieId, voteCount, movieName, posterPicturePath, voteAverage;

    public SummaryHolder( String id, String movieId, String voteCount, String movieName, String posterPicturePath, String voteAverage ){
        this.id = id;
        this.movieId = movieId;
        this.voteCount = voteCount;
        this.movieName = movieName;
        this.posterPicturePath = posterPicturePath;
        this.voteAverage = voteAverage;
    }
}

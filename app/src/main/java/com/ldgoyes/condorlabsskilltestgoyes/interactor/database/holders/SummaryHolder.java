package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;

public class SummaryHolder {
    public static final String[] entryProperties = {
            DBConstants.General.id,
            DBConstants.DataSummary.MOVIE_NAME,
            DBConstants.DataSummary.POSTER_PICTURE_PATH,
            DBConstants.DataSummary.VOTE_AVERAGE
    };
    public String id, movieName, posterPicturePath, voteAverage;

    public SummaryHolder( String id, String movieName, String posterPicturePath, String voteAverage ){
        this.id = id;
        this.movieName = movieName;
        this.posterPicturePath = posterPicturePath;
        this.voteAverage = voteAverage;
    }
}

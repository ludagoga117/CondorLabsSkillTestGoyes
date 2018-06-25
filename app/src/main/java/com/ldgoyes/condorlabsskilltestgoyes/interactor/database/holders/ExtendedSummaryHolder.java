package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders;

public class ExtendedSummaryHolder extends SummaryHolder {
    public boolean isFavorite;

    public ExtendedSummaryHolder(String id, String movieId, String voteCount, String movieName, String posterPicturePath, String voteAverage, boolean isFavorite) {
        super(id, movieId, voteCount, movieName, posterPicturePath, voteAverage);
        this.isFavorite = isFavorite;
    }
}

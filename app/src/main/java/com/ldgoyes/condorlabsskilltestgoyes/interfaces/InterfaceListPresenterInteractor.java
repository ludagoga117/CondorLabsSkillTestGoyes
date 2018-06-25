package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import android.graphics.Bitmap;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.ExtendedSummaryHolder;

public interface InterfaceListPresenterInteractor {
    void notifyDownloadErrorPopularMovies();
    void notifyDownloadErrorMovieDetails();
    void notifyDownloadSuccessPopularMovies();
    void notifyDownloadSuccessImage( String movieId, Bitmap image );
    void notifySuccessClearTableSummary();
    void notifyExtractionSuccessPopularMovies( ExtendedSummaryHolder[] extractedData );
}

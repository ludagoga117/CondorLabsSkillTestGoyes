package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;

public interface InterfaceListPresenterInteractor {
    void notifyDownloadErrorPopularMovies();
    void notifyDownloadErrorMovieDetails();
    void notifyDownloadErrorVideo();
    void notifyDownloadSuccessPopularMovies();
    void notifyUpdateSuccessDetail();
    void notifySuccessClearTableSummary();
    void notifyExtractionSuccessPopularMovies( SummaryHolder[] extractedData );
}

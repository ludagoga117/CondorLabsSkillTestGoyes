package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;

public interface InterfaceListPresenterInteractor {
    void notifyDownloadErrorPopularMovies();
    void notifyDownloadErrorMovieDetails();
    void notifyDownloadSuccessPopularMovies();
    void notifySuccessClearTableSummary();
    void notifyExtractionSuccessPopularMovies( SummaryHolder[] extractedData );
}

package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;

public interface InterfaceDetailPresenterInteractor {
    void notifyDownloadErrorMovieDetails();
    void notifyDownloadErrorVideo();
    void notifyUpdateSuccessDetail();
    void notifyExtractionSuccessMovieDetails( DetailHolder extractedData );
}

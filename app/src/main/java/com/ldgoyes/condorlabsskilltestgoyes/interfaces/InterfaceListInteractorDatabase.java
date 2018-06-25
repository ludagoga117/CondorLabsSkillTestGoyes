package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.ExtendedSummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;

public interface InterfaceListInteractorDatabase {
    void successfulCreateSummary();
    void errorCreateSummary();
    void successfulReadSummary( SummaryHolder extractedData );
    void errorReadSummary();
    void successfulListSummary( SummaryHolder[] extractedData );
    void errorListSummary();
    void successfulListExtendedSummary(ExtendedSummaryHolder[] extractedData);
    void errorListExtendedSummary();
    void successfulClearSummary();
    void errorClearSummary();
    void successfulDeleteSummary();
    void errorDeleteSummary();

    void successfulCreateDetail();
    void errorCreateDetail();
    void successfulClearDetail();
    void errorClearDetail();
}

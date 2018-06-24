package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;

import java.util.HashMap;

public interface InterfaceListInteractorDatabase {
    void successfulCreateSummary();
    void errorCreateSummary();
    void successfulReadSummary( SummaryHolder extractedData );
    void errorReadSummary();
    void successfulListSummary( SummaryHolder[] extractedData );
    void errorListSummary();
}

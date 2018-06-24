package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import java.util.HashMap;

public interface InterfaceListInteractorDatabase {
    void successfulCreateSummary();
    void errorCreateSummary();
    void successfulReadSummary( HashMap<String, String> extractedData );
    void errorReadSummary();
}

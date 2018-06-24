package com.ldgoyes.condorlabsskilltestgoyes.interfaces;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;

public interface InterfaceDetailInteractorDatabase {
    void successfulReadDetail( DetailHolder extractedData );
    void errorReadDetail();
    void successfulUpdateDetail();
    void errorUpdateDetail();
    void successfulDeleteDetail();
    void errorDeleteDetail();
}

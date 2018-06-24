package com.ldgoyes.condorlabsskilltestgoyes.interactor.database;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskCreateSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

/**
 * Desde esta clase se administran los llamados a las tareas asíncronas
 * que escriben en la base de datos.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class DBManager {

    public static void createSummaryEntry(final Context context, InterfaceListInteractorDatabase interactorList){
        AsyncTaskCreateSummary dataInsertionAsyncTask =
                new AsyncTaskCreateSummary(
                        context,
                        interactorList
                );
        dataInsertionAsyncTask.execute();
    }
}
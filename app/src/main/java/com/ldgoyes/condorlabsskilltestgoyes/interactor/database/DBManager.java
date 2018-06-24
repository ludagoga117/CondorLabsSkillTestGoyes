package com.ldgoyes.condorlabsskilltestgoyes.interactor.database;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskClearSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskCreateSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskListSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskReadSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

import java.util.HashMap;

/**
 * Desde esta clase se administran los llamados a las tareas asíncronas
 * que escriben en la base de datos.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class DBManager {
    private static boolean activeApp = false;

    public synchronized static void setActiveApp ( boolean setValue ){
        activeApp = setValue;
    }
    public static boolean getActiveApp (){
        return activeApp;
    }

    public static boolean createSummaryEntry(final Context context, InterfaceListInteractorDatabase interactorList, HashMap<String, String> asyncTaskArguments){
        AsyncTaskCreateSummary dataInsertionAsyncTask =
                new AsyncTaskCreateSummary(
                        context,
                        interactorList
                );
        boolean correctArguments = dataInsertionAsyncTask.setContentValues( asyncTaskArguments );
        if( correctArguments ) dataInsertionAsyncTask.execute();
        return correctArguments;
    }

    public static void readSummaryEntry(final Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        AsyncTaskReadSummary dataExtractionAsyncTask =
                new AsyncTaskReadSummary(
                        context,
                        interactorList,
                        idEntry
                );
        dataExtractionAsyncTask.execute();
    }

    public static void listSummaryEntries(final Context context, InterfaceListInteractorDatabase interactorList){
        AsyncTaskListSummary dataExtractionAsyncTask =
                new AsyncTaskListSummary(
                        context,
                        interactorList
                );
        dataExtractionAsyncTask.execute();
    }

    public static void clearSummaryEntries(final Context context, InterfaceListInteractorDatabase interactorList){
        AsyncTaskClearSummary dataRemovalAsyncTask =
                new AsyncTaskClearSummary(
                        context,
                        interactorList
                );
        dataRemovalAsyncTask.execute();
    }
}
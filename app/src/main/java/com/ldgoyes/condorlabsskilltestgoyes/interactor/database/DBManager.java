package com.ldgoyes.condorlabsskilltestgoyes.interactor.database;

import android.content.Context;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskClearSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskCreateDetail;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskCreateSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskDeleteDetail;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskDeleteSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskListSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskReadDetail;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskReadSummary;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks.AsyncTaskUpdateDetail;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Desde esta clase se administran los llamados a las tareas asíncronas
 * que escriben en la base de datos.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class DBManager {
    /* SUMMARY METHODS */

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

    public static void createSummarySet(final Context context, InterfaceListInteractorDatabase interactorList, JSONArray moviesJsonArray){
        AsyncTaskCreateSummary dataInsertionAsyncTask =
                new AsyncTaskCreateSummary(
                        context,
                        interactorList
                );
        dataInsertionAsyncTask.execute();
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

    public static void deleteSummaryEntry(final Context context, InterfaceListInteractorDatabase interactorList, HashMap<String, String> asyncTaskArguments, String idEntry){
        AsyncTaskDeleteSummary dataInsertionAsyncTask =
                new AsyncTaskDeleteSummary(
                        context,
                        interactorList,
                        idEntry
                );
        dataInsertionAsyncTask.execute();
    }

    /* DETAIL METHODS */

    public static boolean createDetailEntry(final Context context, InterfaceListInteractorDatabase interactorList, HashMap<String, String> asyncTaskArguments){
        AsyncTaskCreateDetail dataInsertionAsyncTask =
                new AsyncTaskCreateDetail(
                        context,
                        interactorList
                );
        boolean correctArguments = dataInsertionAsyncTask.setContentValues( asyncTaskArguments );
        if( correctArguments ) dataInsertionAsyncTask.execute();
        return correctArguments;
    }

    public static void readDetailEntry(final Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        AsyncTaskReadDetail dataExtractionAsyncTask =
                new AsyncTaskReadDetail(
                        context,
                        interactorList,
                        idEntry
                );
        dataExtractionAsyncTask.execute();
    }

    public static boolean updateDetailEntry(final Context context, InterfaceListInteractorDatabase interactorList, HashMap<String, String> asyncTaskArguments, String idEntry){
        AsyncTaskUpdateDetail dataInsertionAsyncTask =
                new AsyncTaskUpdateDetail(
                        context,
                        interactorList,
                        idEntry
                );
        boolean correctArguments = dataInsertionAsyncTask.setContentValues( asyncTaskArguments );
        if( correctArguments ) dataInsertionAsyncTask.execute();
        return correctArguments;
    }

    public static void deleteDetailEntry(final Context context, InterfaceListInteractorDatabase interactorList, HashMap<String, String> asyncTaskArguments, String idEntry){
        AsyncTaskDeleteDetail dataInsertionAsyncTask =
                new AsyncTaskDeleteDetail(
                        context,
                        interactorList,
                        idEntry
                );
        dataInsertionAsyncTask.execute();
    }
}
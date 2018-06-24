package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

/**
 * Tarea asíncrona para extraer TODAS las entradas de la tabla resumen de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskListSummary extends AsyncTask<Void, Void, String[]> {
    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    private String[] entryProperties = {
            DBConstants.DataSummary.MOVIE_NAME,
            DBConstants.DataSummary.POSTER_PICTURE_PATH,
            DBConstants.DataSummary.VOTE_AVERAGE
    };

    public AsyncTaskListSummary(Context context, InterfaceListInteractorDatabase interactorList){
        this.interactorList = interactorList;
        this.context = context;
    }

}

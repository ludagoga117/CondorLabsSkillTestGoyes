package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

/**
 * Tarea asíncrona para almacenar una entrada en la tabla resumen de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskCreateSummary extends AsyncTask<Void, Void, Boolean>  {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    public AsyncTaskCreateSummary(Context context, InterfaceListInteractorDatabase interactorList){
        this.interactorList = interactorList;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        ContentValues contentValues;


        return null;
    }
}
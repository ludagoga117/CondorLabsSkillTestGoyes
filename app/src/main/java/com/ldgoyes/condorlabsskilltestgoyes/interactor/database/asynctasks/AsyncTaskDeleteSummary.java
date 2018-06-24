package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

/**
 * Tarea asíncrona para eliminar una entrada en la tabla resumen de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskDeleteSummary extends AsyncTask<Void, Void, Boolean>  {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    private String idEntry;

    public AsyncTaskDeleteSummary(Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        this.interactorList = interactorList;
        this.context = context;
        this.idEntry = idEntry;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        long deleteResult = db.delete(
                DBConstants.DataSummary.TABLE_NAME,
                DBConstants.General.id+"=?",
                new String[]{idEntry}
        );

        db.close();

        if( deleteResult > 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if( db.isOpen() ) db.close();

        if( !DBManager.getActiveApp() ) return;

        if( result ){
            interactorList.successfulDeleteSummary();
        }else{
            interactorList.errorDeleteSummary();
        }
    }
}
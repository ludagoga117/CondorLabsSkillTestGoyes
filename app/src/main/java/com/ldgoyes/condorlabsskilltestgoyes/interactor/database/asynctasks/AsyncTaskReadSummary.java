package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

import java.util.HashMap;

/**
 * Tarea asíncrona para extraer una entrada en la tabla resumen de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskReadSummary extends AsyncTask<Void, Void, HashMap<String,String>> {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;
    private String idEntry;

    private String[] entryProperties = {
            DBConstants.DataSummary.MOVIE_NAME,
            DBConstants.DataSummary.POSTER_PICTURE_PATH,
            DBConstants.DataSummary.VOTE_AVERAGE
    };

    public AsyncTaskReadSummary(Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        this.interactorList = interactorList;
        this.context = context;
        this.idEntry = idEntry;
    }

    @Override
    protected HashMap<String,String> doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        Cursor c = db.query(
                DBConstants.DataSummary.TABLE_NAME,
                entryProperties,
                DBConstants.General.id+"=?",
                new String[]{
                        idEntry
                },
                null, null, null
        );
        if( !c.moveToFirst() ){
            c.close();
            db.close();
            return null;
        }

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        for( int i = 0; i < c.getColumnCount() ; i++  ){
            stringStringHashMap.put( entryProperties[i], c.getString( i ) );
        }

        c.close();
        db.close();

        return stringStringHashMap;
    }

    @Override
    protected void onPostExecute(HashMap<String, String> extractedData ) {
        if( db.isOpen() ) db.close();

        if( !DBManager.getActiveApp() ) return;

        if( extractedData != null ){
            interactorList.successfulReadSummary( extractedData );
        }else{
            interactorList.errorReadSummary();
        }
    }
}

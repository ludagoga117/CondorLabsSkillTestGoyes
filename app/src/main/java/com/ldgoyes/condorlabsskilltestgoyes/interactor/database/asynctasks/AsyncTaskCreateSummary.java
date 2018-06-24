package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

import java.util.HashMap;

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

    public static final String[] argumentsKeys = {
            DBConstants.DataSummary.MOVIE_NAME,
            DBConstants.DataSummary.POSTER_PICTURE_PATH,
            DBConstants.DataSummary.VOTE_AVERAGE
    };

    private ContentValues contentValues;
    private HashMap<String, String> arguments;

    public AsyncTaskCreateSummary(Context context, InterfaceListInteractorDatabase interactorList){
        this.interactorList = interactorList;
        this.context = context;
        this.contentValues = null;
    }

    public boolean setContentValues( HashMap<String, String> arguments ){
        for( String key : argumentsKeys ){
            if( !arguments.containsKey( key ) ){
                return false;
            }
        }
        contentValues = new ContentValues();
        for( String key : argumentsKeys ){
            contentValues.put(
                    key,
                    arguments.get( key )
            );
        }
        return true;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if( contentValues == null ) return false;

        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        long insertResult = db.insert(
                DBConstants.DataSummary.TABLE_NAME,
                null,
                contentValues
        );

        db.close();

        if( insertResult == DBConstants.General.INSERTION_ERROR_CODE ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if( db.isOpen() ) db.close();

        if( !DBManager.getActiveApp() ) return;

        if( result ){
            interactorList.successfulCreateSummary();
        }else{
            interactorList.errorCreateSummary();
        }
    }
}
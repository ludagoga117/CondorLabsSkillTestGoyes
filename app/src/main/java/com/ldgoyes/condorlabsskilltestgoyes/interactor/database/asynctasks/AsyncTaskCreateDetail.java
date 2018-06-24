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
 * Tarea asíncrona para almacenar una entrada en la tabla detalle de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskCreateDetail extends AsyncTask<Void, Void, Boolean>  {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    public static final String[] argumentsKeys = {
            DBConstants.DataDetail.ID_SUMMARY,
            DBConstants.DataDetail.MOVIE_OVERVIEW,
            DBConstants.DataDetail.BUDGET,
            DBConstants.DataDetail.TRAILER_LINK,
            DBConstants.DataDetail.IS_FAVORITE,
    };

    private ContentValues contentValues;

    public AsyncTaskCreateDetail(Context context, InterfaceListInteractorDatabase interactorList){
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
                DBConstants.DataDetail.TABLE_NAME,
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

        if( result ){
            interactorList.successfulCreateDetail();
        }else{
            interactorList.errorCreateDetail();
        }
    }
}
package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

import java.util.HashMap;

/**
 * Tarea asíncrona para modificar una entrada en la tabla detalle de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskUpdateDetail extends AsyncTask<Void, Void, Boolean>  {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    public static final String[] acceptedKeys = {
            DBConstants.DataDetail.ID_SUMMARY,
            DBConstants.DataDetail.MOVIE_OVERVIEW,
            DBConstants.DataDetail.BUDGET,
            DBConstants.DataDetail.TRAILER_LINK,
            DBConstants.DataDetail.IS_FAVORITE,
    };

    private ContentValues contentValues;
    private String idEntry;

    public AsyncTaskUpdateDetail(Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        this.interactorList = interactorList;
        this.context = context;
        this.contentValues = null;
        this.idEntry = idEntry;
    }

    public boolean setContentValues( HashMap<String, String> arguments ){
        for( String argumentKey : arguments.keySet() ){
            boolean isAcceptedKey = false;
            for( String acceptedKey : acceptedKeys){
                if( acceptedKey.equals( argumentKey ) ){
                    isAcceptedKey = true;
                    break;
                }
            }
            if( !isAcceptedKey ) return false;
        }
        contentValues = new ContentValues();
        for( String key : arguments.keySet() ){
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

        long insertResult = db.update(
                DBConstants.DataDetail.TABLE_NAME,
                contentValues,
                DBConstants.General.id+"=?",
                new String[]{idEntry}
        );

        db.close();

        if( insertResult > 0 ){
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
            interactorList.successfulUpdateDetail();
        }else{
            interactorList.errorUpdateDetail();
        }
    }
}
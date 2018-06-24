package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailInteractorDatabase;

/**
 * Tarea asíncrona para eliminar una entrada en la tabla detalle de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskDeleteDetail extends AsyncTask<Void, Void, Boolean>  {

    private InterfaceDetailInteractorDatabase interactorDetail;
    private Context context;
    private SQLiteDatabase db;

    private String idEntry;

    public AsyncTaskDeleteDetail(Context context, InterfaceDetailInteractorDatabase interactorDetail, String idEntry){
        this.interactorDetail = interactorDetail;
        this.context = context;
        this.idEntry = idEntry;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        long deleteResult = db.delete(
                DBConstants.DataDetail.TABLE_NAME,
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

        if( result ){
            interactorDetail.successfulDeleteDetail();
        }else{
            interactorDetail.errorDeleteDetail();
        }
    }
}
package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

/**
 * Tarea asíncrona para extraer una entrada de la tabla detalle de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskReadDetail extends AsyncTask<Void, Void, DetailHolder> {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;
    private String idEntry;

    public AsyncTaskReadDetail(Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        this.interactorList = interactorList;
        this.context = context;
        this.idEntry = idEntry;
    }

    @Override
    protected DetailHolder doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        Cursor c = db.query(
                DBConstants.DataDetail.TABLE_NAME,
                DetailHolder.entryProperties,
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

        DetailHolder extractedData = new DetailHolder(
                c.getString( 0 ),
                c.getString( 1 ),
                c.getString( 2 ),
                c.getString( 3 ),
                c.getString( 4 ),
                c.getString( 5 )
        );

        c.close();
        db.close();

        return extractedData;
    }

    @Override
    protected void onPostExecute(DetailHolder extractedData ) {
        if( db.isOpen() ) db.close();

        if( !DBManager.getActiveApp() ) return;

        if( extractedData != null ){
            interactorList.successfulReadDetail( extractedData );
        }else{
            interactorList.errorReadDetail();
        }
    }
}

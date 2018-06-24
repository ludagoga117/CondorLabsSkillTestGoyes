package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBManager;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDatabase;

/**
 * Tarea asíncrona para extraer una entrada de la tabla resumen de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskReadSummary extends AsyncTask<Void, Void, SummaryHolder> {

    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;
    private String idEntry;

    public AsyncTaskReadSummary(Context context, InterfaceListInteractorDatabase interactorList, String idEntry){
        this.interactorList = interactorList;
        this.context = context;
        this.idEntry = idEntry;
    }

    @Override
    protected SummaryHolder doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        Cursor c = db.query(
                DBConstants.DataSummary.TABLE_NAME,
                SummaryHolder.entryProperties,
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

        SummaryHolder extractedData = new SummaryHolder(
                c.getString( 0 ),
                c.getString( 1 ),
                c.getString( 2 ),
                c.getString( 3 )
        );

        c.close();
        db.close();

        return extractedData;
    }

    @Override
    protected void onPostExecute(SummaryHolder extractedData ) {
        if( db.isOpen() ) db.close();

        if( extractedData != null ){
            interactorList.successfulReadSummary( extractedData );
        }else{
            interactorList.errorReadSummary();
        }
    }
}

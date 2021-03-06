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

import java.util.ArrayList;
import java.util.List;

/**
 * Tarea asíncrona para extraer TODAS las entradas de la tabla resumen de la base de datos
 * en segundo plano.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class AsyncTaskListSummary extends AsyncTask<Void, Void, SummaryHolder[]> {
    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    public AsyncTaskListSummary(Context context, InterfaceListInteractorDatabase interactorList){
        this.interactorList = interactorList;
        this.context = context;
    }

    @Override
    protected SummaryHolder[] doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        Cursor c = db.query(
                DBConstants.DataSummary.TABLE_NAME,
                null,
                null,
                null,
                null, null, null
        );
        if( !c.moveToFirst() ){
            c.close();
            db.close();
            return null;
        }

        List<SummaryHolder> entriesList = new ArrayList<>();

        int storedMovies = c.getCount();

        for( int i = 0; i < storedMovies; i++ ){
            SummaryHolder summaryObject =
                new SummaryHolder(
                        c.getString( 0 ),
                        c.getString( 1 ),
                        c.getString( 2 ),
                        c.getString( 3 ),
                        c.getString( 4 ),
                        c.getString( 5 )
                );
            entriesList.add( summaryObject );
            c.moveToNext();
        }

        c.close();
        db.close();

        SummaryHolder[] entriesArray = new SummaryHolder[ entriesList.size() ];
        entriesArray = entriesList.toArray( entriesArray );

        return entriesArray;
    }

    @Override
    protected void onPostExecute(SummaryHolder[] extractedData) {
        if( db.isOpen() ) db.close();

        if( extractedData != null ){
            interactorList.successfulListSummary( extractedData );
        }else{
            interactorList.errorListSummary();
        }
    }
}

package com.ldgoyes.condorlabsskilltestgoyes.interactor.database.asynctasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBHelper;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.ExtendedSummaryHolder;
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
public class AsyncTaskListExtendedSummary extends AsyncTask<Void, Void, ExtendedSummaryHolder[]> {
    private InterfaceListInteractorDatabase interactorList;
    private Context context;
    private SQLiteDatabase db;

    public AsyncTaskListExtendedSummary(Context context, InterfaceListInteractorDatabase interactorList){
        this.interactorList = interactorList;
        this.context = context;
    }

    @Override
    protected ExtendedSummaryHolder[] doInBackground(Void... voids) {
        DBHelper dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();

        String innerJoinQuery = "SELECT "
                + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.General.id + ","
                + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.DataSummary.MOVIE_ID + ","
                + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.DataSummary.VOTE_COUNT + ","
                + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.DataSummary.MOVIE_NAME + ","
                + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.DataSummary.POSTER_PICTURE_PATH + ","
                + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.DataSummary.VOTE_AVERAGE + ","
                + DBConstants.DataDetail.TABLE_NAME + "." + DBConstants.DataDetail.IS_FAVORITE
                + " FROM "+ DBConstants.DataSummary.TABLE_NAME + " INNER JOIN " + DBConstants.DataDetail.TABLE_NAME
                + " ON " + DBConstants.DataSummary.TABLE_NAME + "." + DBConstants.DataSummary.MOVIE_ID
                + "=" + DBConstants.DataDetail.TABLE_NAME + "." + DBConstants.DataDetail.MOVIE_ID;

        Cursor c = db.rawQuery(innerJoinQuery, null);

        if( !c.moveToFirst() ){
            c.close();
            db.close();
            return null;
        }

        List<ExtendedSummaryHolder> entriesList = new ArrayList<>();

        int storedMovies = c.getCount();

        for( int i = 0; i < storedMovies; i++ ){
            ExtendedSummaryHolder extendedSummaryObject =
                new ExtendedSummaryHolder(
                        c.getString( 0 ),
                        c.getString( 1 ),
                        c.getString( 2 ),
                        c.getString( 3 ),
                        c.getString( 4 ),
                        c.getString( 5 ),
                        c.getString( 6 ).equals("true")
                );
            entriesList.add( extendedSummaryObject );
            c.moveToNext();
        }

        c.close();
        db.close();

        ExtendedSummaryHolder[] entriesArray = new ExtendedSummaryHolder[ entriesList.size() ];
        entriesArray = entriesList.toArray( entriesArray );

        return entriesArray;
    }

    @Override
    protected void onPostExecute(ExtendedSummaryHolder[] extractedData) {
        if( db.isOpen() ) db.close();

        if( extractedData != null ){
            interactorList.successfulListExtendedSummary( extractedData );
        }else{
            interactorList.errorListExtendedSummary();
        }
    }
}

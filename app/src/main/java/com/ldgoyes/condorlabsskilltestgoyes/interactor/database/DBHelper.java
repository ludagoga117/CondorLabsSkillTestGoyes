package com.ldgoyes.condorlabsskilltestgoyes.interactor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;

/**
 * Abre la base de datos si existe. Si no, la crea.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(
                context,
                DBConstants.General.DATABASE_NAME,
                null,
                DBConstants.General.DATABASE_VERSION
        );
    }

    /**
     * Si la base de datos no existe, la crea y añade tablas.
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( DBConstants.DataSummary.createTable );
        sqLiteDatabase.execSQL( DBConstants.DataDetail.createTable );
    }

    /**
     * En caso de que aparezca una nueva versión de la base de datos,
     * elimina la base de datos vieja, y crea una nueva.
     * @param sqLiteDatabase
     * @param olderVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        sqLiteDatabase.execSQL(
            "DROP TABLE IF EXISTS " + DBConstants.DataSummary.TABLE_NAME
        );
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + DBConstants.DataDetail.TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }

    /**
     * Trata de abrir la base de datos. Si no existe, la crea.
     * En caso de que la base de datos esté siendo ocupada por el mismo u otro hilo,
     * entonces espera hasta que sea desocupado. Por estea razón, el llamado a este
     * método NUNCA puede hacerse desde UI sino que tiene que ser desde segundo plano.
     * @return
     */
    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        final SQLiteDatabase db = super.getWritableDatabase();
        while( db.isDbLockedByCurrentThread() || db.isDbLockedByOtherThreads() ){
            SystemClock.sleep(10l);
        }
        return db;
    }
}
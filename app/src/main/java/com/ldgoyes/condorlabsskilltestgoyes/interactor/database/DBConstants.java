package com.ldgoyes.condorlabsskilltestgoyes.interactor.database;

/**
 * Constantes usadas en el manejo de la base de datos.
 *
 * @author Luis David Goyes Garcés. luis.goyes117@gmail.com
 * @version 1.0.0
 */
public class DBConstants {

    public static class General{
        public static final String DATABASE_NAME = "condor_labs_skill_test_goyes.sqlite";
        public static final int DATABASE_VERSION = 3;

        public static final String createTable = " CREATE TABLE ";
        public static final String idAutoincrement = " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,";
        public static final String TEXT = " TEXT,";
        public static final String id = "ID";
        public static final long INSERTION_ERROR_CODE = -1;
    }

    public static class DataSummary {
        public static final String TABLE_NAME = "data_summary";
        public static final String MOVIE_ID = "movie_id";
        public static final String MOVIE_NAME = "name";
        public static final String POSTER_PICTURE_PATH = "poster_picture_path";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String createTable =
            General.createTable + DataSummary.TABLE_NAME
                + General.idAutoincrement
                + DataSummary.MOVIE_ID + General.TEXT
                + DataSummary.MOVIE_NAME + General.TEXT
                + DataSummary.POSTER_PICTURE_PATH + General.TEXT
                + DataSummary.VOTE_AVERAGE + General.TEXT.replace(",",")");
    }

    public static class DataDetail {
        public static final String TABLE_NAME = "data_detail";
        public static final String MOVIE_ID = "movie_id";
        public static final String MOVIE_OVERVIEW = "movie_overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String BUDGET = "budget";
        public static final String TRAILER_LINK = "trailer_link";
        public static final String IS_FAVORITE = "is_favorite";
        public static final String createTable =
            General.createTable + DataDetail.TABLE_NAME
                + General.idAutoincrement
                + DataDetail.MOVIE_ID + General.TEXT
                + DataDetail.MOVIE_OVERVIEW + General.TEXT
                + DataDetail.RELEASE_DATE + General.TEXT
                + DataDetail.BUDGET + General.TEXT
                + DataDetail.TRAILER_LINK + General.TEXT
                + DataDetail.IS_FAVORITE + General.TEXT.replace(",",")");
    }

}
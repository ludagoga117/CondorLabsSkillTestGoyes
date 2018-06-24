package com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskDownloadJSON extends AsyncTask<Void, Void, JSONObject> {
    private AsyncTaskResponseDownloadJSON asyncTaskResponse;
    private String URLtoDownload;
    private static String ENCODING_CODE_STR = "UTF-8";

    public AsyncTaskDownloadJSON( String URLtoDownload, AsyncTaskResponseDownloadJSON asyncTaskResponse ){
        this.URLtoDownload = URLtoDownload;
        this.asyncTaskResponse = asyncTaskResponse;
    }

    @Override
    protected JSONObject doInBackground (Void... voids) {
        try
        {
            URL url = new URL ( URLtoDownload );
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection ();

            httpURLConnection.setRequestMethod ("GET");

            if ( httpURLConnection.getResponseCode () == HttpURLConnection.HTTP_OK ) {
                StringBuilder stringBuilder = new StringBuilder();
                String stringLine = null;

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                httpURLConnection.getInputStream(),
                                ENCODING_CODE_STR
                        )
                );

                while( ( stringLine = bufferedReader.readLine() ) != null ) {
                    stringBuilder.append(stringLine);
                }

                bufferedReader.close();

                return new JSONObject(stringBuilder.toString());
            }else{
                return null;
            }
        }
        catch (IOException | JSONException ioe)
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute (JSONObject result)
    {
        if ( asyncTaskResponse != null) {
            asyncTaskResponse.processResult( result );
        }
    }
}

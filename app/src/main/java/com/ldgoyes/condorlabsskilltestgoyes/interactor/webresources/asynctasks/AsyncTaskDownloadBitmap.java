package com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AsyncTaskDownloadBitmap extends AsyncTask<Void, Void, Bitmap> {
    private AsyncTaskResponseDownloadBitmap asyncTaskResponse;
    private String URLtoDownload;

    public AsyncTaskDownloadBitmap(String URLtoDownload, AsyncTaskResponseDownloadBitmap asyncTaskResponse ){
        this.URLtoDownload = URLtoDownload;
        this.asyncTaskResponse = asyncTaskResponse;
    }

    @Override
    protected Bitmap doInBackground (Void... voids) {
        try
        {
            URL url = new URL ( URLtoDownload );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(input);

            return image;
        } catch ( IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute (Bitmap image)
    {
        if ( asyncTaskResponse != null) {
            asyncTaskResponse.processResult( image );
        }
    }
}

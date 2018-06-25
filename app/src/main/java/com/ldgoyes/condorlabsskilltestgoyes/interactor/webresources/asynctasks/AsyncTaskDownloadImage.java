package com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskDownloadImage extends AsyncTask<Void, Void, Bitmap> {
    private AsyncTaskResponseDownloadImage asyncTaskResponse;
    private String URLtoDownload;

    public AsyncTaskDownloadImage(String URLtoDownload, AsyncTaskResponseDownloadImage asyncTaskResponse ){
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

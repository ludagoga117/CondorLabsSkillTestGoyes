package com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks;

import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDownload;

import java.net.URL;

public class AsyncTaskDownloadPopular extends AsyncTask<Void, Void, Boolean> {
    private InterfaceListInteractorDownload interactorList;
    private String tmdbApiKey;
    private String language;
    private String pageToQuery;

    public AsyncTaskDownloadPopular( String tmdbApiKey, String language, String pageToQuery ){
        this.tmdbApiKey = tmdbApiKey;
        this.language = language;
        this.pageToQuery = pageToQuery;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        StringBuffer chaine = new StringBuffer("");
        String URLdownloadPopularMovies =
                "https://api.themoviedb.org/3/movie/popular?api_key=" + tmdbApiKey
                        + "&language=" + language
                        + "&page=" + pageToQuery;

        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }
        }
        catch (IOException e) {
            // Writing exception to log
            e.printStackTrace();
        }
        return chaine;

        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if( result ){
            interactorList.successfulDownloadPopular();
        }else{
            interactorList.errorDownloadPopular();
        }
    }
}

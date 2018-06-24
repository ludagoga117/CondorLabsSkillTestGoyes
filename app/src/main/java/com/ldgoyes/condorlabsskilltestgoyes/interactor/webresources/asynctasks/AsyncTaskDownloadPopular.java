package com.ldgoyes.condorlabsskilltestgoyes.interactor.webresources.asynctasks;

import android.os.AsyncTask;

import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListInteractorDownload;

public class AsyncTaskDownloadPopular extends AsyncTask<Void, Void, Boolean> {
    private InterfaceListInteractorDownload interactorList;

    @Override
    protected Boolean doInBackground(Void... voids) {
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

package com.caribejobs.util.blobstorage;

import android.app.Activity;
import android.os.AsyncTask;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

public class BlobStorageTask extends AsyncTask<String, Void, Void> {
    protected Activity context;
    protected String connection;
    protected CloudStorageAccount account;
    protected CloudBlobClient blobClient;
    protected static final String users = "users";
    protected CloudBlobContainer userscontainer;

    public BlobStorageTask(Activity context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public interface BlobStorageTaskListener {
        void afterBlobExecution(String urlres);
    }
}
